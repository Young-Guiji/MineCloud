package com.springboot.cloud.mallorder.provider;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import org.springframework.stereotype.Component;

@Component
public class MallGoodsFeignServiceFallback implements MallGoodsFeignService {
    @Override
    public ProductDto selectProductById(String productId) {
        return null;
    }

    @Override
    public Result<Integer> updateProductStockById(ProductDto product) {
        return null;
    }
}
