package com.springboot.cloud.mallorder.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.google.common.collect.Maps;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.mallorder.service.IAlipayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/pay")
@Api(value = "WEB - AlipayController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AlipayController extends BaseController {


    @Autowired
    private IAlipayService alipayService;

    /**
     * 生成付款二维码.
     *
     * @param orderNo the order no
     *
     * @return the wrapper
     */
    @PostMapping("/createQrCodeImage/{orderNo}")
    @ApiOperation(httpMethod = "POST", value = "生成付款二维码")
    public Result createQrCodeImage(@PathVariable String orderNo) {
        UserInfoDto loginUserInfo = getLoginUserInfo();
        return alipayService.pay(orderNo, loginUserInfo);
    }

    /**
     * 支付宝回调信息.
     *
     * @param request the request
     *
     * @return the object
     */
    @PostMapping("/alipayCallback")
    @ApiOperation(httpMethod = "POST", value = "支付宝回调信息")
    public Object alipayCallback(HttpServletRequest request) {
        logger.info("收到支付宝回调信息");
        Map<String, String> params = Maps.newHashMap();

        Map requestParams = request.getParameterMap();
        for (Object o : requestParams.keySet()) {
            String name = (String) o;
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {

                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        logger.info("支付宝回调,sign:{},trade_status:{},参数:{}", params.get("sign"), params.get("trade_status"), params.toString());

        //非常重要,验证回调的正确性,是不是支付宝发的.并且呢还要避免重复通知.

//        params.remove("sign_type");
//        try {
//            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(), "utf-8", Configs.getSignType());
//
//            if (!alipayRSACheckedV2) {
//                return WrapMapper.error("非法请求,验证不通过,再恶意请求我就报警找网警了");
//            }
//        } catch (AlipayApiException e) {
//            logger.error("支付宝验证回调异常", e);
//        }
//
//        //todo 验证各种数据
//        Wrapper serverResponse = ptcAlipayService.aliPayCallback(params);
//        if (serverResponse.success()) {
//            return PtcApiConstant.AlipayCallback.RESPONSE_SUCCESS;
//        }
//        return PtcApiConstant.AlipayCallback.RESPONSE_FAILED;
        return null;
    }

}
