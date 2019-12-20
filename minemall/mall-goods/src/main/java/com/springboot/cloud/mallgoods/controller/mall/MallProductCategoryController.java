package com.springboot.cloud.mallgoods.controller.mall;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductCategoryDto;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.mallgoods.service.IMallProductCategoryService;
import com.springboot.cloud.util.PublicUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/category", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MallProductCategoryMainController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MallProductCategoryController extends BaseController {

    @Autowired
    private IMallProductCategoryService IMallProductCategoryService;

    @ApiOperation(httpMethod = "POST", value = "获取商品品类信息")
    @PostMapping(value = "/getProductCategoryDtoByPid")
    public Result<List<ProductCategoryDto>> getProductCategoryData(String pid) {
        logger.info("获取商品品类信息. pid={}", pid);
        List<ProductCategoryDto> list;
        if ("0".equals(pid)) {
            // 查询所有一级分类

            list = IMallProductCategoryService.getCategoryDtoList(pid);
            for (ProductCategoryDto productCategoryDto : list) {
                String categoryPid = productCategoryDto.getCategoryId();
                if (PublicUtil.isEmpty(categoryPid)) {
                    continue;
                }
                List<ProductCategoryDto> product2CategoryDtoList = IMallProductCategoryService.getCategoryDtoList(categoryPid);
                if (product2CategoryDtoList.size() > 5) {
                    product2CategoryDtoList = product2CategoryDtoList.subList(0, 4);
                }
                productCategoryDto.setCategoryList(product2CategoryDtoList);
            }

        } else {
            // 根据分类ID 查询分类下的所有二级分类
            list = IMallProductCategoryService.getCategoryDtoList(pid);
        }

        return Result.success(list);
    }


}
