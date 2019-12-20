package com.springboot.cloud.mallorder.service;


import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductDto;
import com.springboot.cloud.mallorder.service.hystrix.MallGoodsServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mall-goods",fallback = MallGoodsServiceFallback.class)
public interface MallGoodsService {
    @PostMapping(value = "/product/getProductById")
    ProductDto selectProductById(@RequestParam("productId") String productId);
}
