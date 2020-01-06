package com.springboot.cloud.mallgoods.controller.mall;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductDto;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductReqDto;
import com.springboot.cloud.common.core.entity.mallgoods.vo.ProductDetailVo;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.mallgoods.entity.po.MallProduct;
import com.springboot.cloud.mallgoods.entity.po.MallProductCategory;
import com.springboot.cloud.mallgoods.service.IMallProductCategoryService;
import com.springboot.cloud.mallgoods.service.IMallProductService;
import com.springboot.cloud.util.PublicUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MallProductMainController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MallProductController extends BaseController {

    @Autowired
    private IMallProductService mallProductService;
    @Autowired
    private IMallProductCategoryService mallProductCategoryService;

    @ApiOperation(httpMethod = "GET", value = "根据商品ID查询商品详细信息")
    @GetMapping(value = "/getProductDetail")
    public Result<ProductDetailVo> getProductDetail(String productId) {
        logger.info("根据商品ID查询商品详细信息. productId={}", productId);
        ProductDetailVo productDto = mallProductService.getProductDetail(productId);
        return Result.success(productDto);
    }

    @ApiOperation(httpMethod = "POST", value = "根据商品ID查询商品信息")
    @PostMapping(value = "/getProductById")
    public ProductDto getProductById(String productId) {
        logger.info("根据商品ID查询商品信息. productId={}", productId);
        ProductDto productDto = null;
        MallProduct product = mallProductService.getProductById(productId);
        if (PublicUtil.isNotEmpty(product)) {
            productDto = new ProductDto();
            BeanUtils.copyProperties(product, productDto);
        }
        return productDto;
    }

    @ApiOperation(httpMethod = "POST", value = "获取商品列表信息")
    @PostMapping(value = "/getProductList")
    public Result<IPage> getProductList(@RequestBody ProductReqDto productReqDto) {
        logger.info("获取商品列表信息. productReqDto={}", productReqDto);
        String categoryId = productReqDto.getCategoryId();
        String keyword = productReqDto.getKeyword();
//        Integer pageNum = productReqDto.getPageNum();
//        Integer pageSize = productReqDto.getPageSize();
        String orderBy = productReqDto.getOrderBy();
        if (StringUtils.isBlank(keyword) && null == categoryId) {
            return Result.success(new Page());
        }
        List<String> categoryIdList = Lists.newArrayList();

        if (categoryId != null) {
            MallProductCategory category = mallProductCategoryService.selectByKey(categoryId);
            if (category == null && StringUtils.isBlank(keyword)) {
                // 没有该分类,并且还没有关键字,这个时候返回一个空的结果集,不报错
                return Result.success(new Page());
            }
            categoryIdList = mallProductCategoryService.selectCategoryAndChildrenById(categoryId);
        }

        //排序处理
        IPage<ProductDto> page = mallProductService.selectByNameAndCategoryIds(StringUtils.isBlank(keyword) ? null : keyword, PublicUtil.isEmpty(categoryIdList) ? null : categoryIdList, orderBy,productReqDto.getPage());

//        List<ProductDto> productListVoList = Lists.newArrayList();
//        for (MallProduct product : page.getRecords()) {
//            ProductDto productListVo = assembleProductListVo(product);
//            String url = productService.getMainImage(product.getId());
//            productListVo.setMainImage(url);
//            productListVoList.add(productListVo);
//        }
        return Result.success(page);
    }

}
