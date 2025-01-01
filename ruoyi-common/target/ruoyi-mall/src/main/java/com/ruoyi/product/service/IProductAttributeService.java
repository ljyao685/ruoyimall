package com.ruoyi.product.service;

import com.ruoyi.product.domain.ProductAttribute;

import java.util.List;

/**
 * 商品属性信息Service接口
 *
 * @author lb
 * @date 2021-08-25
 */
public interface IProductAttributeService {
    /**
     * 查询商品属性信息
     *
     * @param id 商品属性信息主键
     * @return 商品属性信息
     */
    public ProductAttribute selectProductAttributeById(Long id);

    /**
     * 查询商品属性信息列表
     *
     * @param productAttribute 商品属性信息
     * @return 商品属性信息集合
     */
    public List<ProductAttribute> selectProductAttributeList(ProductAttribute productAttribute);

    /**
     * 查询商品分类对应的属性信息
     *
     * @param productCategoryId
     * @return
     */
    public List<ProductAttribute> selectProductAttrInfo(Long productCategoryId);

    /**
     * 新增商品属性信息
     *
     * @param productAttribute 商品属性信息
     * @return 结果
     */
    public int insertProductAttribute(ProductAttribute productAttribute);

    /**
     * 修改商品属性信息
     *
     * @param productAttribute 商品属性信息
     * @return 结果
     */
    public int updateProductAttribute(ProductAttribute productAttribute);

    /**
     * 批量删除商品属性信息
     *
     * @param ids 需要删除的商品属性信息主键集合
     * @return 结果
     */
    public int deleteProductAttributeByIds(Long[] ids);

    /**
     * 删除商品属性信息信息
     *
     * @param id 商品属性信息主键
     * @return 结果
     */
    public int deleteProductAttributeById(Long id);
}
