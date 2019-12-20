package com.springboot.cloud.mallgoods.service;

import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductCategoryDto;
import com.springboot.cloud.mallgoods.entity.po.MallProductCategory;

import java.util.List;

public interface IMallProductCategoryService {
    /**
     * 根据ID获取商品分类信息.
     *
     * @param pid the pid
     *
     * @return the category vo by pid
     */
    List<ProductCategoryDto> getCategoryDtoList(String pid);

    MallProductCategory selectByKey(String categoryId);

    List<String> selectCategoryAndChildrenById(String categoryId);

    List<MallProductCategory> getProductCategoryListByPid(String pid);

    MallProductCategory getByCategoryId(String categoryId);
}
