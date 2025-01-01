package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.ProductCategory;

import java.util.List;

/**
 * 商品分类信息Mapper接口
 *
 * @author lb
 * @date 2021-08-25
 */
public interface ProductCategoryMapper {
    /**
     * 查询商品分类信息
     *
     * @param id 商品分类信息主键
     * @return 商品分类信息
     */
    public ProductCategory selectProductCategoryById(Long id);

    /**
     * 查询商品分类信息列表
     *
     * @param productCategory 商品分类信息
     * @return 商品分类信息集合
     */
    public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory);

    /**
     * 新增商品分类信息
     *
     * @param productCategory 商品分类信息
     * @return 结果
     */
    public int insertProductCategory(ProductCategory productCategory);

    /**
     * 修改商品分类信息
     *
     * @param productCategory 商品分类信息
     * @return 结果
     */
    public int updateProductCategory(ProductCategory productCategory);

    /**
     * 删除商品分类信息
     *
     * @param id 商品分类信息主键
     * @return 结果
     */
    public int deleteProductCategoryById(Long id);

    /**
     * 批量删除商品分类信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductCategoryByIds(Long[] ids);
}
