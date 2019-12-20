package com.springboot.cloud.mallorder.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.springboot.cloud.common.core.entity.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("pc_omc_cart")
public class MallCar extends BasePo {

    private static final long serialVersionUID = 5333646386138778574L;
    private String userId;

    private String productId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 是否选择,1=已勾选,0=未勾选
     */
    private Integer checked;

}
