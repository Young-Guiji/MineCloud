package com.springboot.cloud.mallorder.controller;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.mallorder.constant.OrderConstant;
import com.springboot.cloud.common.core.entity.mallorder.vo.CartProductVo;
import com.springboot.cloud.common.core.entity.mallorder.vo.CartVo;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.mallorder.entity.form.CartListQueryForm;
import com.springboot.cloud.mallorder.service.IMallCarService;
import com.springboot.cloud.util.PublicUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 购物车管理.
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
@RestController
@RequestMapping(value = "/cart", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MallCartController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MallCarController extends BaseController {

    @Autowired
    private IMallCarService mallCarService;

    /**
     * 登录成功合并购物车.
     *
     * @param cartListQueryForm the cart list query
     *
     * @return the wrapper
     */
    @PostMapping(value = "/mergeUserCart")
    @ApiOperation(httpMethod = "POST", value = "登录成功合并购物车")
    public Result<CartVo> mergeUserCart(@RequestBody CartListQueryForm cartListQueryForm) {
        List<CartProductVo> cartProductVoList = cartListQueryForm.getCartProductVoList();
        UserInfoDto userInfo = getLoginUserInfo();
        // 1.更新购物车数据
        if (PublicUtil.isNotEmpty(cartProductVoList)) {
            mallCarService.updateCartList(cartProductVoList,userInfo);
        }
        CartVo cartVo = mallCarService.getCartVo(userInfo.getId());
        return Result.success(cartVo);
    }

    /**
     * 购物车添加商品.
     *
     * @param productId the product id
     * @param count     the count
     *
     * @return the wrapper
     */
    @PostMapping("/addProduct/{productId}/{count}")
    @ApiOperation(httpMethod = "POST", value = "购物车添加商品")
    public Result addProduct(@PathVariable String productId, @PathVariable Integer count) {
        UserInfoDto userInfo = getLoginUserInfo();
        String userId = userInfo.getId();
        int result = mallCarService.addProduct(userId, productId, count);
        return handleResult(result);
    }


    /**
     * 购物车更新商品.
     *
     * @param productId the product id
     * @param count     the count
     *
     * @return the wrapper
     */
    @ApiOperation(httpMethod = "POST", value = "购物车更新商品")
    @PostMapping("/updateProduct/{productId}/{count}")
    public Result updateProduct(@PathVariable String productId, @PathVariable Integer count) {
        UserInfoDto userInfo = getLoginUserInfo();
        String userId = userInfo.getId();
        int result = mallCarService.updateProduct(userId, productId, count);
        return handleResult(result);
    }

    /**
     * 购物车删除商品.
     *
     * @param productIds the product ids
     *
     * @return the wrapper
     */
    @ApiOperation(httpMethod = "POST", value = "购物车删除商品")
    @PostMapping("/deleteProduct/{productIds}")
    public Result deleteProduct(@PathVariable String productIds) {
        UserInfoDto userInfo = getLoginUserInfo();
        String userId = userInfo.getId();
        int result = mallCarService.deleteProduct(userId, productIds);
        return handleResult(result);
    }


    /**
     * 购物车全选商品.
     *
     * @return the wrapper
     */
    @PostMapping("/selectAllProduct")
    @ApiOperation(httpMethod = "POST", value = "购物车全选商品")
    public Result selectAll() {
        UserInfoDto userInfo = getLoginUserInfo();
        String userId = userInfo.getId();
        int result = mallCarService.selectOrUnSelect(userId, null, OrderConstant.Cart.CHECKED);
        return handleResult(result);
    }

    /**
     * 购物车反选全部商品.
     *
     * @return the wrapper
     */
    @PostMapping("/unSelectAllProduct")
    @ApiOperation(httpMethod = "POST", value = "购物车反选全部商品")
    public Result unSelectAll() {
        UserInfoDto userInfo = getLoginUserInfo();
        String userId = userInfo.getId();
        int result = mallCarService.selectOrUnSelect(userId, null, OrderConstant.Cart.UN_CHECKED);
        return handleResult(result);
    }


    /**
     * 选中商品.
     *
     * @param productId the product id
     *
     * @return the wrapper
     */
    @PostMapping("/selectProduct/{productId}")
    @ApiOperation(httpMethod = "POST", value = "选中商品")
    public Result select(@PathVariable String productId) {
        UserInfoDto userInfo = getLoginUserInfo();
        String userId = userInfo.getId();
        int result = mallCarService.selectOrUnSelect(userId, productId, OrderConstant.Cart.CHECKED);
        return handleResult(result);
    }

    /**
     * 反选商品.
     *
     * @param productId the product id
     *
     * @return the wrapper
     */
    @PostMapping("/unSelectProduct/{productId}")
    @ApiOperation(httpMethod = "POST", value = "反选商品")
    public Result unSelect(@PathVariable String productId) {
        UserInfoDto userInfo = getLoginUserInfo();
        String userId = userInfo.getId();
        int result = mallCarService.selectOrUnSelect(userId, productId, OrderConstant.Cart.UN_CHECKED);
        return handleResult(result);
    }


}
