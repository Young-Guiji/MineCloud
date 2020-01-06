package com.springboot.cloud.mallgoods.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.springboot.cloud.common.core.constant.GlobalConstant;
import com.springboot.cloud.common.core.constant.MessageGroupConstants;
import com.springboot.cloud.common.core.constant.MqTopicConstants;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UpdateAttachmentDto;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.common.core.util.SnowflakeIdWorker;
import com.springboot.cloud.mallgoods.entity.dto.EditProductDto;
import com.springboot.cloud.mallgoods.entity.form.MallProductQueryForm;
import com.springboot.cloud.mallgoods.entity.po.MallProduct;
import com.springboot.cloud.mallgoods.entity.po.MallProductCategory;
import com.springboot.cloud.common.core.message.entity.po.MqMessageData;
import com.springboot.cloud.mallgoods.entity.vo.ProductVo;
import com.springboot.cloud.mallgoods.manager.ProductManager;
import com.springboot.cloud.mallgoods.mapper.MallProductMapper;
import com.springboot.cloud.mallgoods.service.IAdminProductService;
import com.springboot.cloud.mallgoods.service.IMallProductCategoryService;
import com.springboot.cloud.util.PublicUtil;
import com.springboot.cloud.util.RedisKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class AdminProductServiceImpl implements IAdminProductService {

    @Autowired
    private MallProductMapper mallProductMapper;
    @Autowired
    private IMallProductCategoryService mallProductCategoryService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductManager productManager;

    @Override
    public Page<ProductVo> queryProductListWithPage(MallProductQueryForm mallProductQueryForm) {

        Page<ProductVo> page = mallProductMapper.queryProductListWithPage(mallProductQueryForm.getPage(),mallProductQueryForm);

        return page;
    }

    @Override
    public ProductVo getProductVo(String id) {
        MallProduct mallProduct = mallProductMapper.selectById(id);
        ProductVo productVo = new ProductVo();
        BeanUtils.copyProperties(mallProduct,productVo);
        List<String> categoryIdList = Lists.newArrayList();
        buildCategoryIdList(categoryIdList, mallProduct.getCategoryId());
        // 获取分类节点集合
        Collections.reverse(categoryIdList);
        productVo.setCategoryIdList(categoryIdList);
        // 获取图片集合
//        final OptBatchGetUrlRequest request = new OptBatchGetUrlRequest(mallProduct.getProductCode());
//        request.setEncrypt(true);
//        List<ElementImgUrlDto> imgUrlList = opcRpcService.listFileUrl(request);
//        productVo.setImgUrlList(imgUrlList);
        return productVo;
    }

    private List<String> buildCategoryIdList(List<String> categoryIdList, String categoryId) {
        MallProductCategory category = mallProductCategoryService.getByCategoryId(categoryId);
        if (category != null) {
            categoryIdList.add(categoryId);
            buildCategoryIdList(categoryIdList, category.getPid());
        }
        return categoryIdList;
    }

    @Override
    public void saveProduct(EditProductDto editProductDto, UserInfoDto loginUserInfo) {
        String productCode = editProductDto.getProductCode();
        MallProduct product = new MallProduct();
        BeanUtils.copyProperties(editProductDto, product);
        List<String> categoryIdList = editProductDto.getCategoryIdList();
        String categoryId = categoryIdList.get(categoryIdList.size() - 1);
        product.setCategoryId(categoryId);
        List<String> attachmentIdList = editProductDto.getAttachmentIdList();
        if (PublicUtil.isNotEmpty(attachmentIdList)) {
            product.setMainImage(String.valueOf(attachmentIdList.get(0)));
            product.setSubImages(Joiner.on(GlobalConstant.Symbol.COMMA).join(attachmentIdList));
        }
        MqMessageData mqMessageData;
        if (null==product.getId()) {
            productCode = String.valueOf(SnowflakeIdWorker.generateId());
        } else {
            Preconditions.checkArgument(StringUtils.isNotEmpty(productCode), SystemErrorType.GOOD10021024);
        }
        product.setProductCode(productCode);
        UpdateAttachmentDto updateAttachmentDto = new UpdateAttachmentDto(productCode, attachmentIdList);

        String body = null;
        try {
            body = objectMapper.writeValueAsString(updateAttachmentDto);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            new ServiceException(SystemErrorType.SYSTEM_ERROR);
        }
        String queue = MqTopicConstants.MqTagEnum.UPDATE_ATTACHMENT.getQueue();
        String tag = MqTopicConstants.MqTagEnum.UPDATE_ATTACHMENT.getTag();
        String key = RedisKeyUtil.createMqKey(queue, tag, product.getProductCode(), body);

        if (null==product.getId() && PublicUtil.isNotEmpty(attachmentIdList)) {
            mqMessageData = new MqMessageData(body, queue, tag, key, MessageGroupConstants.GOODS_GROUP);
            productManager.saveProduct(mqMessageData, product, true);
        } else if (null==product.getId() && PublicUtil.isEmpty(attachmentIdList)) {
            mallProductMapper.insert(product);
        } else {
            mqMessageData = new MqMessageData(body, queue, tag, key, MessageGroupConstants.GOODS_GROUP);
            productManager.saveProduct(mqMessageData, product, false);
        }
    }

    @Override
    public void deleteProductById(String id) {

    }
}
