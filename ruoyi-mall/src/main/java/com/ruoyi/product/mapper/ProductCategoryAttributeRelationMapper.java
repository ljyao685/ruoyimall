package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.ProductCategoryAttributeRelation;

import java.util.List;

/**
 * 商品分类属性关联信息Mapper接口
 *
 * @author lb
 * @date 2021-08-26
 */
public interface ProductCategoryAttributeRelationMapper {
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
     * 批量新增商品分类属性关联信息
     *
     * @param list
     * @return
     */
    public int insertProductCategoryAttributeRelationList(List<ProductCategoryAttributeRelation> list);

    /**
     * 修改商品分类属性关联信息
     *
     * @param productCategoryAttributeRelation 商品分类属性关联信息
     * @return 结果
     */
    public int updateProductCategoryAttributeRelation(ProductCategoryAttributeRelation productCategoryAttributeRelation);

    /**
     * 删除商品分类对应的商品分类属性关联信息
     *
     * @param productCategoryId 商品分类ID
     * @return 结果
     */
    public int deleteProductCategoryAttributeRelationByProdCateId(Long productCategoryId);

    /**
     * 删除商品分类属性关联信息
     *
     * @param id 商品分类属性关联信息主键
     * @return 结果
     */
    public int deleteProductCategoryAttributeRelationById(Long id);

    /**
     * 批量删除商品分类属性关联信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductCategoryAttributeRelationByIds(Long[] ids);
}
