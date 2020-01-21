package com.springboot.cloud.mallorder.provider;


import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mall-goods",fallback = MallGoodsFeignServiceFallback.class)
public interface MallGoodsFeignService {
    @PostMapping(value = "/product/getProductById")
    ProductDto selectProductById(@RequestParam("productId") String productId);
    @PostMapping(value = "/product/updateProductStockById")
    Result<Integer> updateProductStockById(ProductDto product);
}
