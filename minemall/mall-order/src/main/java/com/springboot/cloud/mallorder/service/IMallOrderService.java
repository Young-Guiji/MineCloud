package com.springboot.cloud.mallorder.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.common.core.entity.form.BaseQueryForm;

public interface IMallOrderService {

    IPage queryUserOrderListWithPage(String userId, BaseQueryForm baseQueryForm);
}
