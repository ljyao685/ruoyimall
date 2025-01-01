package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.ProductAttributeCategory;

import java.util.List;

/**
 * 商品属性分类信息Mapper接口
 *
 * @author lb
 * @date 2021-08-25
 */
public interface ProductAttributeCategoryMapper {
    /**
     * 查询商品属性分类信息
     *
     * @param id 商品属性分类信息主键
     * @return 商品属性分类信息
     */
    public ProductAttributeCategory selectProductAttributeCategoryById(Long id);

    /**
     * 查询商品属性分类信息列表
     *
     * @param productAttributeCategory 商品属性分类信息
     * @return 商品属性分类信息集合
     */
    public List<ProductAttributeCategory> selectProductAttributeCategoryList(ProductAttributeCategory productAttributeCategory);

    /**
     * 获取包含属性的属性分类列表
     *
     * @return
     */
    public List<ProductAttributeCategory> selectProdAttrCateListWithAttr();

    /**
     * 新增商品属性分类信息
     *
     * @param productAttributeCategory 商品属性分类信息
     * @return 结果
     */
    public int insertProductAttributeCategory(ProductAttributeCategory productAttributeCategory);

    /**
     * 修改商品属性分类信息
     *
     * @param productAttributeCategory 商品属性分类信息
     * @return 结果
     */
    public int updateProductAttributeCategory(ProductAttributeCategory productAttributeCategory);

    /**
     * 删除商品属性分类信息
     *
     * @param id 商品属性分类信息主键
     * @return 结果
     */
    public int deleteProductAttributeCategoryById(Long id);

    /**
     * 批量删除商品属性分类信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductAttributeCategoryByIds(Long[] ids);
}
