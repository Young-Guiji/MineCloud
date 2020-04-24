package com.springboot.cloud.malluser.provider;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductCategoryDto;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductReqDto;
import com.springboot.cloud.common.core.entity.mallgoods.vo.ProductDetailVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "mall-goods",fallback = MallGoodsFeignServiceFallback.class)
public interface MallGoodsFeignService {
    @PostMapping(value = "/category/getProductCategoryDtoByPid")
    Result<List<ProductCategoryDto>> getProductCategoryData(@RequestParam("pid") Long pid);

    @PostMapping(value = "/product/getProductList")
    Result<Page> getProductList(@RequestBody ProductReqDto productReqDto);

    @GetMapping(value = "/product/getProductDetail")
    Result<ProductDetailVo> getProductDetail(@RequestParam("productId") String productId);
}
