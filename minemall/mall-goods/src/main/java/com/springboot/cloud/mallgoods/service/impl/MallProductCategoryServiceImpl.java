package com.springboot.cloud.mallgoods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.springboot.cloud.common.core.entity.mallgoods.dto.ProductCategoryDto;
import com.springboot.cloud.mallgoods.entity.po.MallProductCategory;
import com.springboot.cloud.mallgoods.mapper.MallProductCategoryMapper;
import com.springboot.cloud.mallgoods.service.IMallProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MallProductCategoryServiceImpl implements IMallProductCategoryService {

    @Autowired
    private MallProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategoryDto> getCategoryDtoList(String categoryId) {
        return productCategoryMapper.selectCategoryDtoList(categoryId);
    }

    @Override
    public MallProductCategory selectByKey(String categoryId) {
        return productCategoryMapper.selectById(categoryId);
    }

    @Override
    public List<String> selectCategoryAndChildrenById(String categoryId) {
        Set<MallProductCategory> categorySet = Sets.newHashSet();
        findChildCategory(categorySet, categoryId);


        List<String> categoryIdList = Lists.newArrayList();
        if (categoryId != null) {
            for (MallProductCategory categoryItem : categorySet) {
                categoryIdList.add(categoryItem.getId());
            }
        }
        return categoryIdList;
    }

    /**
     * 递归算法,算出子节点
     */
    private Set<MallProductCategory> findChildCategory(Set<MallProductCategory> categorySet, String categoryId) {
        MallProductCategory category = productCategoryMapper.selectById(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<MallProductCategory> categoryList = this.getProductCategoryListByPid(categoryId);
        for (MallProductCategory categoryItem : categoryList) {
            findChildCategory(categorySet, categoryItem.getId());
        }
        return categorySet;
    }

    @Override
    public List<MallProductCategory> getProductCategoryListByPid(String pid) {
        Preconditions.checkArgument(pid != null, "pid is null");
        QueryWrapper<MallProductCategory> queryWrapper = new QueryWrapper();
        queryWrapper.eq("pid",pid);
        return productCategoryMapper.selectList(queryWrapper);
    }

    @Override
    public MallProductCategory getByCategoryId(String categoryId) {
        Preconditions.checkArgument(categoryId != null, "分类ID不能为空");

        QueryWrapper<MallProductCategory> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("id",categoryId);

        return productCategoryMapper.selectOne(queryWrapper);
    }
}
