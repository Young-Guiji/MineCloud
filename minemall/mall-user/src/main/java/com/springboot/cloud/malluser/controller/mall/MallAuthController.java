package com.springboot.cloud.malluser.controller.mall;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductCategoryDto;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductReqDto;
import com.springboot.cloud.common.core.entity.mallgoods.vo.ProductDetailVo;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.malluser.provider.MallGoodsFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MallAuthRestController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MallAuthController extends BaseController {

    @Autowired
    private MallGoodsFeignService mallGoodsService;

    /**
     * 查询商品列表.
     *
     * @param productReqDto the product req dto
     *
     * @return the product category dto by pid
     */
    @PostMapping(value = "/product/queryProductList")
    @ApiOperation(httpMethod = "POST", value = "查询商品列表")
    public Result<Page> queryProductList(@RequestBody ProductReqDto productReqDto) {
        logger.info("getProductCategoryDtoByPid - 查询分类信息 productReqDto={}", productReqDto);
        Result<Page> productList = mallGoodsService.getProductList(productReqDto);
        return productList;
    }

    /**
     * 查询商品详情信息.
     *
     * @param productId the product id
     *
     * @return the wrapper
     */
    @GetMapping(value = "/product/queryProductDetail/{productId}")
    @ApiOperation(httpMethod = "GET", value = "查询商品详情信息")
    public Result<ProductDetailVo> queryProductDetail(@PathVariable Long productId) {
        logger.info("getProductCategoryDtoByPid - 查询商品详情信息 productId={}", productId);
        return mallGoodsService.getProductDetail(productId);
    }

    /**
     * 查询分类信息.
     *
     * @param pid the pid
     *
     * @return the product category dto by pid
     */
    @GetMapping(value = "/category/getProductCategoryDtoByPid/{pid}")
    public Result<List<ProductCategoryDto>> getProductCategoryDtoByPid(@PathVariable Long pid) {
        logger.info("getProductCategoryDtoByPid - 查询分类信息 pid={}", pid);
        Result<List<ProductCategoryDto>> productCategoryData = mallGoodsService.getProductCategoryData(pid);
        logger.info("productCategoryData={}", productCategoryData);
        return productCategoryData;
    }

}
