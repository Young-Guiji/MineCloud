package com.springboot.cloud.mallgoods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductDto;
import com.springboot.cloud.common.core.entity.mallgoods.vo.ProductDetailVo;
import com.springboot.cloud.mallgoods.entity.po.MallProduct;

import java.util.List;

public interface IMallProductService {


    String getMainImage(String productId);

    IPage<ProductDto> selectByNameAndCategoryIds(String productName, List<String> categoryIdList, String orderBy, IPage page);

    ProductDetailVo getProductDetail(String productId);

    MallProduct getProductById(String productId);
}
