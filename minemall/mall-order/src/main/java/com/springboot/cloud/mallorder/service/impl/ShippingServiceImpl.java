package com.springboot.cloud.mallorder.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.mallorder.entity.form.ShippingForm;
import com.springboot.cloud.mallorder.entity.po.MallShipping;
import com.springboot.cloud.mallorder.mapper.MallShippingMapper;
import com.springboot.cloud.mallorder.service.IMallShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingServiceImpl implements IMallShippingService {

    @Autowired
    private MallShippingMapper mallShippingMapper;

    @Override
    public IPage<MallShipping> queryListWithPageByUserId(String userId, ShippingForm mallShippingForm) {
        Preconditions.checkArgument(userId != null, SystemErrorType.USER10011001);
        Page<MallShipping> page = new Page<>(mallShippingForm.getCurrent(),mallShippingForm.getSize());
        page = mallShippingMapper.selectByUserId(page,userId);
        return page;
    }

    @Override
    public MallShipping selectById(String shippingId) {
        return mallShippingMapper.selectById(shippingId);
    }


}
