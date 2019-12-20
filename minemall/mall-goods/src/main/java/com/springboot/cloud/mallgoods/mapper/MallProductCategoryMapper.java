package com.springboot.cloud.mallgoods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductCategoryDto;
import com.springboot.cloud.mallgoods.entity.po.MallProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MallProductCategoryMapper extends BaseMapper<MallProductCategory> {

    List<ProductCategoryDto> selectCategoryDtoList(String categoryId);
}
