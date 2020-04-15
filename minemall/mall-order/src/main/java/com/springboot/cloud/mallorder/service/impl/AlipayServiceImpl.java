package com.springboot.cloud.mallorder.service.impl;

import cn.hutool.core.io.FileUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.ExtendParams;
import com.alipay.api.domain.GoodsDetail;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.google.common.collect.Lists;
import com.springboot.cloud.common.core.entity.mallorder.dto.OrderDto;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileByteInfoReqDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileReqDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileRespDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.mallorder.config.AlipayConfig;
import com.springboot.cloud.mallorder.entity.po.MallOrderDetail;
import com.springboot.cloud.mallorder.service.IAlipayService;
import com.springboot.cloud.mallorder.service.IMallOrderDetailService;
import com.springboot.cloud.mallorder.service.IMallOrderService;
import com.springboot.cloud.util.BigDecimalUtil;
import com.springboot.cloud.util.ZxingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

@Slf4j
@Service
public class AlipayServiceImpl implements IAlipayService {

    private static AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
    @Autowired
    private IMallOrderService orderService;
    @Autowired
    private IMallOrderDetailService orderDetailService;
    @Value("${mall.alipay.qrCode.pcPath}")
    private String qrCodePcPath;
    @Value("${mall.alipay.qrCode.qiniuPath}")
    private String qrCodeQiniuPath;

    @Override
    public Result pay(String orderNo, UserInfoDto loginUserInfo) {

        String userId = loginUserInfo.getId();
        OrderDto order = orderService.queryOrderDtoByUserIdAndOrderNo(userId, orderNo);
        if (order == null) {
            throw new ServiceException(SystemErrorType.ORDER10031003);
        }

        // (必填) 商户网站订单系统中唯一订单号, 64个字符以内, 只能包含字母、数字、下划线,
        // 需保证商户系统端不能重复, 建议通过数据库sequence生成,
        String outTradeNo = order.getOrderNo();

        // (必填) 订单标题, 粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = "PCMall扫码支付,订单号:" + outTradeNo;

        // (必填) 订单总金额, 单位为元, 不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = order.getPayment().toString();

        // (可选) 订单不可打折金额, 可以配合商家平台配置折扣活动, 如果酒水不参与打折, 则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";

        // 卖家支付宝账号ID, 用于支持一个签约账号下支持打款到不同的收款账号, (打款到sellerId对应的支付宝账号)
        // 如果该字段为空, 则默认为与支付宝签约的商户的PID, 也就是appid对应的PID
        String sellerId = "";

        // 订单描述, 可以对交易或商品进行一个详细地描述, 比如填写"购买商品2件共15.00元"
        String body = "订单" + outTradeNo + "购买商品共" + totalAmount + "元";

        // 商户操作员编号, 添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号, 通过门店号和商家后台可以配置精准到门店的折扣信息, 详询支付宝技术支持
        String storeId = "test_store_id";

        // 业务扩展参数, 目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法), 详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2016101900720429");

        // 支付超时, 定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表, 需填写购买商品详细信息,
        List<GoodsDetail> goodsDetailList = Lists.newArrayList();

        List<MallOrderDetail> orderDetailList = orderDetailService.getListByOrderNoUserId(orderNo, userId);
        for (MallOrderDetail orderDetail : orderDetailList) {
            GoodsDetail goodsDetail = new GoodsDetail();
            goodsDetail.setGoodsId(orderDetail.getProductId());
            goodsDetail.setGoodsName(orderDetail.getProductName());
            goodsDetail.setPrice(BigDecimalUtil.mul(orderDetail.getCurrentUnitPrice().doubleValue(), 100D).toString());
            goodsDetail.setQuantity(orderDetail.getQuantity().longValue());
            goodsDetailList.add(goodsDetail);
        }
        //设置请求参数
        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        alipayRequest.setBizContent("{\"outTradeNo\":\""+ outTradeNo +"\","
                + "\"totalAmount\":\""+ totalAmount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        try {
            AlipayTradePrecreateResponse response = alipayClient.execute(alipayRequest);
            if(response.isSuccess()){
                log.info("支付宝预下单成功: )");
                // 需要修改为运行机器上的路径
                File folder = new File(qrCodePcPath);
                if (!folder.exists()) {
                    folder.setWritable(true);
                    folder.mkdirs();
                }
                //细节细节细节
                String qrPath = String.format(qrCodePcPath + "/qr-%s.png", response.getOutTradeNo());
                String qrFileName = String.format("qr-%s.png", response.getOutTradeNo());
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, qrPath);
                File qrCodeImage = new File(qrPath);
                UploadFileReqDto uploadFileReqDto = new UploadFileReqDto();
                uploadFileReqDto.setBucketName("paascloud-oss-bucket");
                uploadFileReqDto.setFilePath(qrCodeQiniuPath);
                uploadFileReqDto.setFileType("png");
                uploadFileReqDto.setUserId(loginUserInfo.getId());
                uploadFileReqDto.setUserName(loginUserInfo.getUsername());

                UploadFileByteInfoReqDto uploadFileByteInfoReqDto = new UploadFileByteInfoReqDto();

                uploadFileByteInfoReqDto.setFileName(qrFileName);
                byte[] bytes = FileUtil.readBytes(qrCodeImage);
                uploadFileByteInfoReqDto.setFileByteArray(bytes);

                uploadFileReqDto.setUploadFileByteInfoReqDto(uploadFileByteInfoReqDto);
                UploadFileRespDto optUploadFileRespDto = null;
                try {
//                    optUploadFileRespDto = opcOssService.uploadFile(uploadFileReqDto);
                    optUploadFileRespDto.setRefNo(orderNo);
                    // 获取二维码
//                    final OptGetUrlRequest request = new OptGetUrlRequest();
//                    request.setAttachmentId(optUploadFileRespDto.getAttachmentId());
//                    request.setEncrypt(true);
//                    String fileUrl = opcOssService.getFileUrl(request);
                    optUploadFileRespDto.setAttachmentUrl(bytes);
                    return Result.success(optUploadFileRespDto);
                } catch (Exception e) {
                    log.error("上传二维码异常", e);
                }
            } else {
                log.error("支付失败，订单号={}，异常信息：{}",outTradeNo,response.getMsg());
            }
        } catch (AlipayApiException e) {
            log.error("支付失败，订单号={}，异常信息：{}",outTradeNo,e.getMessage(),e);
        }
        return Result.fail("支付失败，订单号={"+outTradeNo+"}");
    }
}
