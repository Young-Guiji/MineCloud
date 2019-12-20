package com.springboot.cloud.mallorder.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.cloud.mallorder.entity.form.ShippingForm;
import com.springboot.cloud.mallorder.entity.po.MallShipping;

public interface IMallShippingService {
    IPage<MallShipping> queryListWithPageByUserId(String userId, ShippingForm mallShippingForm);

    MallShipping selectById(String shippingId);
}
