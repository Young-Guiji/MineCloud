package com.springboot.cloud.mallgoods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.mallgoods.entity.dto.EditProductDto;
import com.springboot.cloud.mallgoods.entity.form.MallProductQueryForm;
import com.springboot.cloud.mallgoods.entity.vo.ProductVo;

public interface IAdminProductService {
    Page<ProductVo> queryProductListWithPage(MallProductQueryForm mallProductQueryForm);

    ProductVo getProductVo(String id);

    void saveProduct(EditProductDto editProductDto, UserInfoDto loginUserInfo);

    void deleteProductById(String id);
}
