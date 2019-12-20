package com.springboot.cloud.mallorder.service.hystrix;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductDto;
import com.springboot.cloud.mallorder.service.MallGoodsService;
import org.springframework.stereotype.Component;

@Component
public class MallGoodsServiceFallback implements MallGoodsService {
    @Override
    public ProductDto selectProductById(String productId) {
        return null;
    }
}
