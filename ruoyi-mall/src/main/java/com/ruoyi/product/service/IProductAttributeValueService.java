package com.ruoyi.product.service;

import com.ruoyi.product.domain.ProductAttributeValue;

import java.util.List;

/**
 * 商品属性值信息Service接口
 *
 * @author lb
 * @date 2021-08-25
 */
public interface IProductAttributeValueService {
    /**
     * 查询商品属性值信息
     *
     * @param id 商品属性值信息主键
     * @return 商品属性值信息
     */
    public ProductAttributeValue selectProductAttributeValueById(Long id);

    /**
     * 查询商品属性值信息列表
     *
     * @param productAttributeValue 商品属性值信息
     * @return 商品属性值信息集合
     */
    public List<ProductAttributeValue> selectProductAttributeValueList(ProductAttributeValue productAttributeValue);

    /**
     * 新增商品属性值信息
     *
     * @param productAttributeValue 商品属性值信息
     * @return 结果
     */
    public int insertProductAttributeValue(ProductAttributeValue productAttributeValue);

    /**
     * 修改商品属性值信息
     *
     * @param productAttributeValue 商品属性值信息
     * @return 结果
     */
    public int updateProductAttributeValue(ProductAttributeValue productAttributeValue);

    /**
     * 批量删除商品属性值信息
     *
     * @param ids 需要删除的商品属性值信息主键集合
     * @return 结果
     */
    public int deleteProductAttributeValueByIds(Long[] ids);

    /**
     * 删除商品属性值信息信息
     *
     * @param id 商品属性值信息主键
     * @return 结果
     */
    public int deleteProductAttributeValueById(Long id);
}
