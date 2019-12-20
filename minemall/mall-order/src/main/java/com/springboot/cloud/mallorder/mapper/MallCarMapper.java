package com.springboot.cloud.mallorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.cloud.mallorder.entity.po.MallCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MallCarMapper extends BaseMapper<MallCar> {
    MallCar selectByProductIdAndUserId(@Param("productId") String productId, @Param("userId") String userId);

    int selectUnCheckedCartProductCountByUserId(String userId);

    List<MallCar> selectCheckedCartByUserId(String userId);

    int deleteByUserIdProductIds(@Param("userId") String userId, @Param("productIdList") List<String> productList);

    int checkedOrUncheckedProduct(@Param("userId") String userId, @Param("productId") String productId, @Param("checked") int checked);
}
