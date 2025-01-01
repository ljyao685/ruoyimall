package com.ruoyi.product.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.product.domain.ProductCategoryAttributeRelation;
import com.ruoyi.product.mapper.ProductCategoryAttributeRelationMapper;
import com.ruoyi.product.service.IProductCategoryAttributeRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类属性关联信息Service业务层处理
 *
 * @author lb
 * @date 2021-08-26
 */
@Service
public class ProductCategoryAttributeRelationServiceImpl implements IProductCategoryAttributeRelationService {
    @Autowired
    private ProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;

    /**
     * 查询商品分类属性关联信息
     *
     * @param id 商品分类属性关联信息主键
     * @return 商品分类属性关联信息
     */
    @Override
    public ProductCategoryAttributeRelation selectProductCategoryAttributeRelationById(Long id) {
        return productCategoryAttributeRelationMapper.selectProductCategoryAttributeRelationById(id);
    }

    /**
     * 查询商品分类属性关联信息列表
     *
     * @param productCategoryAttributeRelation 商品分类属性关联信息
     * @return 商品分类属性关联信息
     */
    @Override
    public List<ProductCategoryAttributeRelation> selectProductCategoryAttributeRelationList(ProductCategoryAttributeRelation productCategoryAttributeRelation) {
        return productCategoryAttributeRelationMapper.selectProductCategoryAttributeRelationList(productCategoryAttributeRelation);
    }

    /**
     * 新增商品分类属性关联信息
     *
     * @param productCategoryAttributeRelation 商品分类属性关联信息
     * @return 结果
     */
    @Override
    public int insertProductCategoryAttributeRelation(ProductCategoryAttributeRelation productCategoryAttributeRelation) {
        productCategoryAttributeRelation.setCreateTime(DateUtils.getNowDate());
        return productCategoryAttributeRelationMapper.insertProductCategoryAttributeRelation(productCategoryAttributeRelation);
    }

    /**
     * 修改商品分类属性关联信息
     *
     * @param productCategoryAttributeRelation 商品分类属性关联信息
     * @return 结果
     */
    @Override
    public int updateProductCategoryAttributeRelation(ProductCategoryAttributeRelation productCategoryAttributeRelation) {
        productCategoryAttributeRelation.setUpdateBy(SecurityUtils.getUsername());
        productCategoryAttributeRelation.setUpdateTime(DateUtils.getNowDate());
        return productCategoryAttributeRelationMapper.updateProductCategoryAttributeRelation(productCategoryAttributeRelation);
    }

    /**
     * 批量删除商品分类属性关联信息
     *
     * @param ids 需要删除的商品分类属性关联信息主键
     * @return 结果
     */
    @Override
    public int deleteProductCategoryAttributeRelationByIds(Long[] ids) {
        return productCategoryAttributeRelationMapper.deleteProductCategoryAttributeRelationByIds(ids);
    }

    /**
     * 删除商品分类属性关联信息信息
     *
     * @param id 商品分类属性关联信息主键
     * @return 结果
     */
    @Override
    public int deleteProductCategoryAttributeRelationById(Long id) {
        return productCategoryAttributeRelationMapper.deleteProductCategoryAttributeRelationById(id);
    }
}
