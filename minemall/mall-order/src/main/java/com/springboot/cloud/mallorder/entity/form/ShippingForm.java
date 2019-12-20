package com.springboot.cloud.mallorder.entity.form;
import com.springboot.cloud.common.core.entity.form.BaseQueryForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ShippingForm extends BaseQueryForm {

    private static final long serialVersionUID = 7337074530378267740L;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 收货姓名
     */
    private String receiverName;

    /**
     * 收货固定电话
     */
    private String receiverPhoneNo;

    /**
     * 收货移动电话
     */
    private String receiverMobileNo;

    /**
     * 收货人省ID
     */
    private String provinceId;

    /**
     * 省份
     */
    private String provinceName;

    /**
     * 收货人城市ID
     */
    private String cityId;

    /**
     * 收货人城市名称
     */
    private String cityName;

    /**
     * 区/县
     */
    private String districtName;

    /**
     * 区/县 编码
     */
    private String districtId;

    /**
     * 街道ID
     */
    private String streetId;

    /**
     * 接到名称
     */
    private String streetName;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 邮编
     */
    private String receiverZipCode;

    /**
     * 邮编
     */
    private Integer defaultAddress;

}
