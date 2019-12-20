package com.springboot.cloud.mallorder.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.form.BaseQueryForm;
import com.springboot.cloud.common.core.entity.mallorder.vo.OrderProductVo;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.mallorder.service.IMallCarService;
import com.springboot.cloud.mallorder.service.IMallOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The class mall order controller.
 *
 * @author guiji
 */
@RestController
@RequestMapping(value = "/order")
@Api(value = "WEB - MallOrderController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MallOrderController extends BaseController {

    @Autowired
    private IMallCarService carService;
    @Autowired
    private IMallOrderService orderService;

    /**
     * 获取购物车商品数量.
     *
     * @return the order cart product
     */
    @PostMapping("/getOrderCartProduct")
    @ApiOperation(httpMethod = "POST", value = "获取购物车商品数量")
    public Result getOrderCartProduct() {
        logger.info("getOrderCartProduct - 获取购物车商品数量");
        OrderProductVo orderCartProduct = carService.getOrderCartProduct(getLoginUserInfo().getId());
        return Result.success(orderCartProduct);
    }

    /**
     * Query user order list with page Result.
     *
     * @param baseQueryForm the base query
     *
     * @return the Result
     */
    @PostMapping("/queryUserOrderListWithPage")
    @ApiOperation(httpMethod = "POST", value = "查询用户订单列表")
    public Result queryUserOrderListWithPage(@RequestBody BaseQueryForm baseQueryForm) {
        logger.info("queryUserOrderListWithPage - 查询用户订单集合. baseQuery={}", baseQueryForm);

        String userId = getLoginUserInfo().getId();
        logger.info("操作人信息. userId={}", userId);

        IPage pageInfo = orderService.queryUserOrderListWithPage(userId, baseQueryForm);

        return Result.success(pageInfo);
    }

}
