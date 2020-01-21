package com.springboot.cloud.mallgoods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.springboot.cloud.common.core.constant.GlobalConstant;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductDto;
import com.springboot.cloud.common.core.entity.mallgoods.vo.ProductDetailVo;
import com.springboot.cloud.mallgoods.constant.MallConstant;
import com.springboot.cloud.mallgoods.entity.po.MallProduct;
import com.springboot.cloud.mallgoods.entity.po.MallProductCategory;
import com.springboot.cloud.mallgoods.mapper.MallProductMapper;
import com.springboot.cloud.mallgoods.service.IMallProductCategoryService;
import com.springboot.cloud.mallgoods.service.IMallProductService;
import com.springboot.cloud.util.PubUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MallProductServiceImpl implements IMallProductService {

    @Autowired
    private MallProductMapper productMapper;
    @Autowired
    private IMallProductCategoryService IMallProductCategoryService;

    @Override
    public String getMainImage(String productId) {
        MallProduct product = productMapper.selectById(productId);
//        String url = null;
//        if (product != null) {
//            final OptGetUrlRequest request = new OptGetUrlRequest();
//            request.setEncrypt(true);
//            request.setAttachmentId(Long.valueOf(product.getMainImage()));
//            request.setExpires(3600 * 12 * 7L);
//            url = opcRpcService.getFileUrl(request);
//        }
        return product.getMainImage();
    }

    @Override
    public IPage<ProductDto> selectByNameAndCategoryIds(String productName, List<String> categoryIdList, String orderBy, IPage page) {
        QueryWrapper<MallProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(productName),"name",productName);
        queryWrapper.in(null!=categoryIdList,"category_id",categoryIdList);
        if(null != orderBy){
            String[] orderBys = orderBy.split(" ");
            String column = orderBys[0];
            String order = orderBys[1];
            if("asc".equals(order)){
                queryWrapper.orderByAsc(column);
            }else if("desc".equals(order)){
                queryWrapper.orderByDesc(column);
            }
        }
        IPage<MallProduct> iPage = productMapper.selectPage(page, queryWrapper);
        IPage<ProductDto> pages = iPage.convert(product -> assembleProductListVo(product));
        return pages;
    }

    @Override
    public ProductDetailVo getProductDetail(String productId) {
        log.info("获取商品明细信息, productId={}", productId);
        Preconditions.checkArgument(productId != null, SystemErrorType.GOOD10021021);

        MallProduct product = productMapper.selectById(productId);
        if (product == null) {
            throw new ServiceException(SystemErrorType.GOOD10021017,productId);
        }
        if (product.getStatus() != MallConstant.ProductStatusEnum.ON_SALE.getCode()) {
            throw new ServiceException(SystemErrorType.GOOD10021017, productId);
        }

        return assembleProductDetailVo(product);
    }

    @Override
    public MallProduct getProductById(String productId) {
        return productMapper.selectById(productId);
    }

    @Override
    public int updateProductStockById(ProductDto productDto) {
        Preconditions.checkArgument(!PubUtils.isNull(productDto, productDto.getId()), SystemErrorType.GOOD10021021.getMesg());
        return productMapper.updateProductStockById(productDto);
    }

    private ProductDto assembleProductListVo(MallProduct product) {
        ProductDto productListVo = new ProductDto();
        productListVo.setId(product.getId());
        productListVo.setName(product.getName());
        productListVo.setCategoryId(product.getCategoryId());
        productListVo.setImageHost("");
        productListVo.setMainImage(product.getMainImage());
        productListVo.setPrice(product.getPrice());
        productListVo.setSubtitle(product.getSubtitle());
        productListVo.setStatus(product.getStatus());
        return productListVo;
    }

    private ProductDetailVo assembleProductDetailVo(MallProduct product) {
        ProductDetailVo productDetailVo = new ProductDetailVo();
        String mainImage = product.getMainImage();
        String subImages = product.getSubImages();
        if (StringUtils.isNotEmpty(mainImage)) {
            // 图片查询
//            OptGetUrlRequest request = new OptGetUrlRequest();
//            request.setAttachmentId(Long.valueOf(mainImage));
//            request.setEncrypt(true);
//            String url = opcRpcService.getFileUrl(request);
            productDetailVo.setMainImage(mainImage);
        }
        if (StringUtils.isNotEmpty(subImages)) {
            List<String> urlList = Lists.newArrayList();
            List<String> subImageList = Splitter.on(GlobalConstant.Symbol.COMMA).trimResults().splitToList(subImages);
            for (final String subImage : subImageList) {
//                OptGetUrlRequest request = new OptGetUrlRequest();
//                request.setAttachmentId(Long.valueOf(subImage));
//                request.setEncrypt(true);
//                String url = opcRpcService.getFileUrl(request);
//                urlList.add(url);
                urlList.add(subImage);
            }
            productDetailVo.setSubImages(Joiner.on(GlobalConstant.Symbol.COMMA).join(urlList));
        }

        productDetailVo.setId(product.getId());
        productDetailVo.setSubtitle(product.getSubtitle());
        productDetailVo.setPrice(product.getPrice());

        productDetailVo.setDetail(product.getDetail());
        productDetailVo.setName(product.getName());
        productDetailVo.setStatus(product.getStatus());
        productDetailVo.setStock(product.getStock());

        productDetailVo.setImageHost("");

        MallProductCategory category = IMallProductCategoryService.getByCategoryId(product.getId());
        if (category == null) {
            //默认根节点
            productDetailVo.setPid("0");
        } else {
            productDetailVo.setPid(category.getPid());
        }

        return productDetailVo;
    }
}
