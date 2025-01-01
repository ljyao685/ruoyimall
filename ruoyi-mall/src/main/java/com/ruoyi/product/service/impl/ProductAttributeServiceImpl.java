package com.ruoyi.product.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.product.domain.ProductAttribute;
import com.ruoyi.product.domain.ProductAttributeCategory;
import com.ruoyi.product.mapper.ProductAttributeCategoryMapper;
import com.ruoyi.product.mapper.ProductAttributeMapper;
import com.ruoyi.product.service.IProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性信息Service业务层处理
 *
 * @author lb
 * @date 2021-08-25
 */
@Service
public class ProductAttributeServiceImpl implements IProductAttributeService {
    @Autowired
    private ProductAttributeMapper productAttributeMapper;
    @Autowired
    private ProductAttributeCategoryMapper productAttributeCategoryMapper;

    /**
     * 查询商品属性信息
     *
     * @param id 商品属性信息主键
     * @return 商品属性信息
     */
    @Override
    public ProductAttribute selectProductAttributeById(Long id) {
        return productAttributeMapper.selectProductAttributeById(id);
    }

    /**
     * 查询商品属性信息列表
     *
     * @param productAttribute 商品属性信息
     * @return 商品属性信息
     */
    @Override
    public List<ProductAttribute> selectProductAttributeList(ProductAttribute productAttribute) {
        return productAttributeMapper.selectProductAttributeList(productAttribute);
    }

    /**
     * 查询商品分类对应的属性信息
     *
     * @param productCategoryId
     * @return
     */
    @Override
    public List<ProductAttribute> selectProductAttrInfo(Long productCategoryId) {
        return productAttributeMapper.selectProductAttrInfo(productCategoryId);
    }

    /**
     * 新增商品属性信息
     *
     * @param productAttribute 商品属性信息
     * @return 结果
     */
    @Override
    public int insertProductAttribute(ProductAttribute productAttribute) {
        productAttribute.setTenantId(SecurityUtils.getUserId());
        productAttribute.setCreateBy(SecurityUtils.getUsername());
        productAttribute.setCreateTime(DateUtils.getNowDate());
        int count = productAttributeMapper.insertProductAttribute(productAttribute);
        //新增商品属性以后需要更新商品属性分类数量
        ProductAttributeCategory productAttributeCategory = productAttributeCategoryMapper.selectProductAttributeCategoryById(productAttribute.getProductAttributeCategoryId());
        if (productAttribute.getType() == 0) {
            productAttributeCategory.setAttributeCount(productAttributeCategory.getAttributeCount() + 1);
        } else if (productAttribute.getType() == 1) {
            productAttributeCategory.setParamCount(productAttributeCategory.getParamCount() + 1);
        }
        productAttributeCategoryMapper.updateProductAttributeCategory(productAttributeCategory);
        return count;
    }

    /**
     * 修改商品属性信息
     *
     * @param productAttribute 商品属性信息
     * @return 结果
     */
    @Override
    public int updateProductAttribute(ProductAttribute productAttribute) {
        productAttribute.setUpdateBy(SecurityUtils.getUsername());
        productAttribute.setUpdateTime(DateUtils.getNowDate());
        return productAttributeMapper.updateProductAttribute(productAttribute);
    }

    /**
     * 批量删除商品属性信息
     *
     * @param ids 需要删除的商品属性信息主键
     * @return 结果
     */
    @Override
    public int deleteProductAttributeByIds(Long[] ids) {
        //根据第一个获取属性，然后根据属性中的分类id查询属性分类
        ProductAttribute productAttribute = productAttributeMapper.selectProductAttributeById(ids[0]);
        Integer type = productAttribute.getType();
        ProductAttributeCategory productAttributeCategory = productAttributeCategoryMapper.selectProductAttributeCategoryById(productAttribute.getProductAttributeCategoryId());
        int count = productAttributeMapper.deleteProductAttributeByIds(ids);
        //删除完成后修改数量
        if (type == 0) {// 属性||规格
            if (productAttributeCategory.getAttributeCount() >= count) {
                productAttributeCategory.setAttributeCount(productAttributeCategory.getAttributeCount() - count);
            } else {// 参数
                productAttributeCategory.setAttributeCount(0);
            }
        } else if (type == 1) {
            if (productAttributeCategory.getParamCount() >= count) {
                productAttributeCategory.setParamCount(productAttributeCategory.getParamCount() - count);
            } else {
                productAttributeCategory.setParamCount(0);
            }
        }
        productAttributeCategoryMapper.updateProductAttributeCategory(productAttributeCategory);
        return count;
    }

    /**
     * 删除商品属性信息信息
     *
     * @param id 商品属性信息主键
     * @return 结果
     */
    @Override
    public int deleteProductAttributeById(Long id) {
        return productAttributeMapper.deleteProductAttributeById(id);
    }
}
