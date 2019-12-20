package com.springboot.cloud.mallgoods.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.common.core.annotation.LogAnnotation;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.mallgoods.entity.dto.EditProductDto;
import com.springboot.cloud.mallgoods.entity.form.MallProductQueryForm;
import com.springboot.cloud.mallgoods.entity.vo.ProductVo;
import com.springboot.cloud.mallgoods.service.IAdminProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class Mdc dict main controller.
 *
 * @author guiji
 */
@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - AdminProductController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminProductController extends BaseController {

    @Autowired
    private IAdminProductService adminProductService;

    /**
     * 分页查询商品列表.
     *
     * @param mallProductQueryForm the mdc product
     *
     * @return the wrapper
     */
    @PostMapping(value = "/queryProductListWithPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询商品列表")
    public Result<Page<ProductVo>> queryProductListWithPage(@ApiParam(name = "mallProductQueryForm", value = "商品信息") @RequestBody MallProductQueryForm mallProductQueryForm) {
        logger.info("分页查询商品列表, mallProductQueryForm={}", mallProductQueryForm);
        mallProductQueryForm.setOrderBy("updated_time desc");
        Page<ProductVo> page = adminProductService.queryProductListWithPage(mallProductQueryForm);
        return Result.success(page);
    }

    /**
     * 商品详情.
     */
    @PostMapping(value = "/getById/{id}")
    @ApiOperation(httpMethod = "POST", value = "分页查询商品列表")
    public Result<ProductVo> getById(@PathVariable String id) {
        logger.info("查询商品详情, id={}", id);
        ProductVo productVo = adminProductService.getProductVo(id);
        return Result.success(productVo);
    }

    @PostMapping(value = "/save")
    @ApiOperation(httpMethod = "POST", value = "编辑商品")
    @LogAnnotation
    public Result saveCategory(@RequestBody EditProductDto editProductDto) {
        logger.info("编辑商品. editProductDto={}", editProductDto);
        adminProductService.saveProduct(editProductDto, getLoginUserInfo());
        return Result.success();
    }

    @PostMapping(value = "/deleteProductById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除商品信息")
    @LogAnnotation
    public Result<ProductVo> deleteProductById(@PathVariable String id) {
        logger.info("删除商品信息, id={}", id);
        adminProductService.deleteProductById(id);
        return Result.success();
    }

}
