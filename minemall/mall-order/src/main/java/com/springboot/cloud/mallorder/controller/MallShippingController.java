package com.springboot.cloud.mallorder.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.mallorder.entity.form.ShippingForm;
import com.springboot.cloud.mallorder.entity.po.MallShipping;
import com.springboot.cloud.mallorder.service.IMallShippingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class Omc shipping controller.
 *
 * @author guiji
 */
@RestController
@RequestMapping(value = "/shipping", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MallShippingController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MallShippingController extends BaseController {

    @Autowired
    private IMallShippingService shippingService;

    /**
     * 分页查询当前用户收货人地址列表.
     *
     * @param mallShippingForm the mallShippingForm
     *
     * @return the wrapper
     */
    @PostMapping("/queryUserShippingListWithPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询当前用户收货人地址列表")
    public Result<Page> queryUserShippingListWithPage(@RequestBody ShippingForm mallShippingForm) {
        String userId = getLoginUserInfo().getId();
        logger.info("queryUserShippingListWithPage - 分页查询当前用户收货人地址列表.userId={} shipping={}", userId, mallShippingForm);
        IPage<MallShipping> page  = shippingService.queryListWithPageByUserId(userId, mallShippingForm);
        return Result.success(page);
    }

}
