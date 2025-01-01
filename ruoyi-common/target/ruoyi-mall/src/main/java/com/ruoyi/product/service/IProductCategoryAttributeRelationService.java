package com.ruoyi.product.service;

import com.ruoyi.product.domain.ProductCategoryAttributeRelation;

import java.util.List;

/**
 * 商品分类属性关联信息Service接口
 *
 * @author lb
 * @date 2021-08-26
 */
public interface IProductCategoryAttributeRelationService {
    /**
     * 查询商品分类属性关联信息
     *
     * @param id 商品分类属性关联信息主键
     * @return 商品分类属性关联信息
     */
    public ProductCategoryAttributeRelation selectProductCategoryAttributeRelationById(Long id);

    /**
     * 查询商品分类属性关联信息列表
     *
     * @param productCategoryAttributeRelation 商品分类属性关联信息
     * @return 商品分类属性关联信息集合
     */
    public List<ProductCategoryAttributeRelation> selectProductCategoryAttributeRelationList(ProductCategoryAttributeRelation productCategoryAttributeRelation);

    /**
     * 新增商品分类属性关联信息
     *
     * @param productCategoryAttributeRelation 商品分类属性关联信息
     * @return 结果
     */
    public int insertProductCategoryAttributeRelation(ProductCategoryAttributeRelation productCategoryAttributeRelation);

    /**
     * 修改商品分类属性关联信息
     *
     * @param productCategoryAttributeRelation 商品分类属性关联信息
     * @return 结果
     */
    public int updateProductCategoryAttributeRelation(ProductCategoryAttributeRelation productCategoryAttributeRelation);

    /**
     * 批量删除商品分类属性关联信息
     *
     * @param ids 需要删除的商品分类属性关联信息主键集合
     * @return 结果
     */
    public int deleteProductCategoryAttributeRelationByIds(Long[] ids);

    /**
     * 删除商品分类属性关联信息信息
     *
     * @param id 商品分类属性关联信息主键
     * @return 结果
     */
    public int deleteProductCategoryAttributeRelationById(Long id);
}
