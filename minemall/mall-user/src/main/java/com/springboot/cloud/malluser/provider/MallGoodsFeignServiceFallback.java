package com.springboot.cloud.malluser.provider;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductCategoryDto;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductReqDto;
import com.springboot.cloud.common.core.entity.mallgoods.vo.ProductDetailVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MallGoodsFeignServiceFallback implements MallGoodsFeignService {
    @Override
    public Result<List<ProductCategoryDto>> getProductCategoryData(Long pid) {
        return null;
    }

    @Override
    public Result<Page> getProductList(ProductReqDto productReqDto) {
        return null;
    }

    @Override
    public Result<ProductDetailVo> getProductDetail(String productId) {
        return null;
    }
}
