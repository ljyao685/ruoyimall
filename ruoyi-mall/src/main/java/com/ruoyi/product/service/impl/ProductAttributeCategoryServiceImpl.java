package com.ruoyi.product.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.product.domain.ProductAttributeCategory;
import com.ruoyi.product.mapper.ProductAttributeCategoryMapper;
import com.ruoyi.product.service.IProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性分类信息Service业务层处理
 *
 * @author lb
 * @date 2021-08-25
 */
@Service
public class ProductAttributeCategoryServiceImpl implements IProductAttributeCategoryService {
    @Autowired
    private ProductAttributeCategoryMapper productAttributeCategoryMapper;

    /**
     * 查询商品属性分类信息
     *
     * @param id 商品属性分类信息主键
     * @return 商品属性分类信息
     */
    @Override
    public ProductAttributeCategory selectProductAttributeCategoryById(Long id) {
        return productAttributeCategoryMapper.selectProductAttributeCategoryById(id);
    }

    /**
     * 查询商品属性分类信息列表
     *
     * @param productAttributeCategory 商品属性分类信息
     * @return 商品属性分类信息
     */
    @Override
    public List<ProductAttributeCategory> selectProductAttributeCategoryList(ProductAttributeCategory productAttributeCategory) {
        return productAttributeCategoryMapper.selectProductAttributeCategoryList(productAttributeCategory);
    }

    /**
     * 获取包含属性的属性分类列表
     *
     * @return
     */
    @Override
    public List<ProductAttributeCategory> selectProdAttrCateListWithAttr() {
        return productAttributeCategoryMapper.selectProdAttrCateListWithAttr();
    }

    /**
     * 新增商品属性分类信息
     *
     * @param productAttributeCategory 商品属性分类信息
     * @return 结果
     */
    @Override
    public int insertProductAttributeCategory(ProductAttributeCategory productAttributeCategory) {
        productAttributeCategory.setTenantId(SecurityUtils.getUserId());
        productAttributeCategory.setCreateBy(SecurityUtils.getUsername());
        productAttributeCategory.setCreateTime(DateUtils.getNowDate());
        return productAttributeCategoryMapper.insertProductAttributeCategory(productAttributeCategory);
    }

    /**
     * 修改商品属性分类信息
     *
     * @param productAttributeCategory 商品属性分类信息
     * @return 结果
     */
    @Override
    public int updateProductAttributeCategory(ProductAttributeCategory productAttributeCategory) {
        productAttributeCategory.setUpdateBy(SecurityUtils.getUsername());
        productAttributeCategory.setUpdateTime(DateUtils.getNowDate());
        return productAttributeCategoryMapper.updateProductAttributeCategory(productAttributeCategory);
    }

    /**
     * 批量删除商品属性分类信息
     *
     * @param ids 需要删除的商品属性分类信息主键
     * @return 结果
     */
    @Override
    public int deleteProductAttributeCategoryByIds(Long[] ids) {
        return productAttributeCategoryMapper.deleteProductAttributeCategoryByIds(ids);
    }

    /**
     * 删除商品属性分类信息信息
     *
     * @param id 商品属性分类信息主键
     * @return 结果
     */
    @Override
    public int deleteProductAttributeCategoryById(Long id) {
        return productAttributeCategoryMapper.deleteProductAttributeCategoryById(id);
    }
}
