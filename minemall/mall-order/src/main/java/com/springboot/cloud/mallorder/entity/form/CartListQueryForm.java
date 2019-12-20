package com.springboot.cloud.mallorder.entity.form;

import com.springboot.cloud.common.core.entity.mallorder.vo.CartProductVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CartListQueryForm implements Serializable {
    private static final long serialVersionUID = -5968284112162772265L;
    private List<CartProductVo> cartProductVoList;
}
