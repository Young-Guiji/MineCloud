package com.springboot.cloud.malluser.service.hystrix;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductCategoryDto;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductReqDto;
import com.springboot.cloud.common.core.entity.mallgoods.vo.ProductDetailVo;
import com.springboot.cloud.malluser.service.MallGoodsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MallGoodsServiceFallback implements MallGoodsService {
    @Override
    public Result<List<ProductCategoryDto>> getProductCategoryData(Long pid) {
        return null;
    }

    @Override
    public Result<Page> getProductList(ProductReqDto productReqDto) {
        return null;
    }

    @Override
    public Result<ProductDetailVo> getProductDetail(Long productId) {
        return null;
    }
}
