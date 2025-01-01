package com.ruoyi.product.service;

import com.ruoyi.common.core.domain.CascaderSelect;
import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.product.domain.ProductCategory;

import java.util.List;

/**
 * 商品分类信息Service接口
 *
 * @author lb
 * @date 2021-08-25
 */
public interface IProductCategoryService {
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
     * 构建前端所需要下拉树结构
     *
     * @param productCategory 商品分类信息
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildCategoryTreeList(ProductCategory productCategory);

    /**
     * 构建前端所需要级联器结构
     *
     * @param productCategory 商品分类信息
     * @return 级联结构列表
     */
    public List<CascaderSelect> buildCategoryCascaderSelect(ProductCategory productCategory);

    /**
     * 新增商品分类信息
     *
     * @param productCategory 商品分类信息
     * @return 结果
     */
    public int insertProductCategory(ProductCategory productCategory);

    /**
     * 修改商品分类状态
     *
     * @param productCategory 商品分类信息
     * @return 结果
     */
    public int updateProductCategoryStatus(ProductCategory productCategory);

    /**
     * 修改商品分类信息
     *
     * @param productCategory 商品分类信息
     * @return 结果
     */
    public int updateProductCategory(ProductCategory productCategory);

    /**
     * 批量删除商品分类信息
     *
     * @param ids 需要删除的商品分类信息主键集合
     * @return 结果
     */
    public int deleteProductCategoryByIds(Long[] ids);

    /**
     * 删除商品分类信息信息
     *
     * @param id 商品分类信息主键
     * @return 结果
     */
    public int deleteProductCategoryById(Long id);
}
