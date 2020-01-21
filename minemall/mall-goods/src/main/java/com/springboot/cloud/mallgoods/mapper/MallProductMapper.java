package com.springboot.cloud.mallgoods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductDto;
import com.springboot.cloud.mallgoods.entity.form.MallProductQueryForm;
import com.springboot.cloud.mallgoods.entity.po.MallProduct;
import com.springboot.cloud.mallgoods.entity.vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MallProductMapper extends BaseMapper<MallProduct> {

    Page<ProductVo> queryProductListWithPage(Page page, @Param("mallProductQueryForm") MallProductQueryForm mallProductQueryForm);

    int updateProductStockById(ProductDto productDto);
}
