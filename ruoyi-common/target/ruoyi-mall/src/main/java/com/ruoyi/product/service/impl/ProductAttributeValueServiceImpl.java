package com.ruoyi.product.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.product.domain.ProductAttributeValue;
import com.ruoyi.product.mapper.ProductAttributeValueMapper;
import com.ruoyi.product.service.IProductAttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性值信息Service业务层处理
 *
 * @author lb
 * @date 2021-08-25
 */
@Service
public class ProductAttributeValueServiceImpl implements IProductAttributeValueService {
    @Autowired
    private ProductAttributeValueMapper productAttributeValueMapper;

    /**
     * 查询商品属性值信息
     *
     * @param id 商品属性值信息主键
     * @return 商品属性值信息
     */
    @Override
    public ProductAttributeValue selectProductAttributeValueById(Long id) {
        return productAttributeValueMapper.selectProductAttributeValueById(id);
    }

    /**
     * 查询商品属性值信息列表
     *
     * @param productAttributeValue 商品属性值信息
     * @return 商品属性值信息
     */
    @Override
    public List<ProductAttributeValue> selectProductAttributeValueList(ProductAttributeValue productAttributeValue) {
        return productAttributeValueMapper.selectProductAttributeValueList(productAttributeValue);
    }

    /**
     * 新增商品属性值信息
     *
     * @param productAttributeValue 商品属性值信息
     * @return 结果
     */
    @Override
    public int insertProductAttributeValue(ProductAttributeValue productAttributeValue) {
        productAttributeValue.setCreateTime(DateUtils.getNowDate());
        return productAttributeValueMapper.insertProductAttributeValue(productAttributeValue);
    }

    /**
     * 修改商品属性值信息
     *
     * @param productAttributeValue 商品属性值信息
     * @return 结果
     */
    @Override
    public int updateProductAttributeValue(ProductAttributeValue productAttributeValue) {
        productAttributeValue.setUpdateBy(SecurityUtils.getUsername());
        productAttributeValue.setUpdateTime(DateUtils.getNowDate());
        return productAttributeValueMapper.updateProductAttributeValue(productAttributeValue);
    }

    /**
     * 批量删除商品属性值信息
     *
     * @param ids 需要删除的商品属性值信息主键
     * @return 结果
     */
    @Override
    public int deleteProductAttributeValueByIds(Long[] ids) {
        return productAttributeValueMapper.deleteProductAttributeValueByIds(ids);
    }

    /**
     * 删除商品属性值信息信息
     *
     * @param id 商品属性值信息主键
     * @return 结果
     */
    @Override
    public int deleteProductAttributeValueById(Long id) {
        return productAttributeValueMapper.deleteProductAttributeValueById(id);
    }
}
