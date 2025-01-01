package com.ruoyi.product.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.product.domain.ProductPreferenceAreaRelation;
import com.ruoyi.product.mapper.ProductPreferenceAreaRelationMapper;
import com.ruoyi.product.service.IProductPreferenceAreaRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优选专区和商品的关系Service业务层处理
 *
 * @author lb
 * @date 2022-01-18
 */
@Service
public class ProductPreferenceAreaRelationServiceImpl implements IProductPreferenceAreaRelationService {
    @Autowired
    private ProductPreferenceAreaRelationMapper productPreferenceAreaRelationMapper;

    /**
     * 查询优选专区和商品的关系
     *
     * @param id 优选专区和商品的关系主键
     * @return 优选专区和商品的关系
     */
    @Override
    public ProductPreferenceAreaRelation selectProductPreferenceAreaRelationById(Long id) {
        return productPreferenceAreaRelationMapper.selectProductPreferenceAreaRelationById(id);
    }

    /**
     * 查询优选专区和商品的关系列表
     *
     * @param productPreferenceAreaRelation 优选专区和商品的关系
     * @return 优选专区和商品的关系
     */
    @Override
    public List<ProductPreferenceAreaRelation> selectProductPreferenceAreaRelationList(ProductPreferenceAreaRelation productPreferenceAreaRelation) {
        return productPreferenceAreaRelationMapper.selectProductPreferenceAreaRelationList(productPreferenceAreaRelation);
    }

    /**
     * 新增优选专区和商品的关系
     *
     * @param productPreferenceAreaRelation 优选专区和商品的关系
     * @return 结果
     */
    @Override
    public int insertProductPreferenceAreaRelation(ProductPreferenceAreaRelation productPreferenceAreaRelation) {
        productPreferenceAreaRelation.setTenantId(SecurityUtils.getUserId());
        productPreferenceAreaRelation.setCreateBy(SecurityUtils.getUsername());
        productPreferenceAreaRelation.setCreateTime(DateUtils.getNowDate());
        return productPreferenceAreaRelationMapper.insertProductPreferenceAreaRelation(productPreferenceAreaRelation);
    }

    /**
     * 修改优选专区和商品的关系
     *
     * @param productPreferenceAreaRelation 优选专区和商品的关系
     * @return 结果
     */
    @Override
    public int updateProductPreferenceAreaRelation(ProductPreferenceAreaRelation productPreferenceAreaRelation) {
        productPreferenceAreaRelation.setUpdateBy(SecurityUtils.getUsername());
        productPreferenceAreaRelation.setUpdateTime(DateUtils.getNowDate());
        return productPreferenceAreaRelationMapper.updateProductPreferenceAreaRelation(productPreferenceAreaRelation);
    }

    /**
     * 批量删除优选专区和商品的关系
     *
     * @param ids 需要删除的优选专区和商品的关系主键
     * @return 结果
     */
    @Override
    public int deleteProductPreferenceAreaRelationByIds(Long[] ids) {
        return productPreferenceAreaRelationMapper.deleteProductPreferenceAreaRelationByIds(ids);
    }

    /**
     * 删除优选专区和商品的关系信息
     *
     * @param id 优选专区和商品的关系主键
     * @return 结果
     */
    @Override
    public int deleteProductPreferenceAreaRelationById(Long id) {
        return productPreferenceAreaRelationMapper.deleteProductPreferenceAreaRelationById(id);
    }
}
