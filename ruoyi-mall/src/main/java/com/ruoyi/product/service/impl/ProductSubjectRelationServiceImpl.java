package com.ruoyi.product.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.product.domain.ProductSubjectRelation;
import com.ruoyi.product.mapper.ProductSubjectRelationMapper;
import com.ruoyi.product.service.IProductSubjectRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专题和商品关系Service业务层处理
 *
 * @author lb
 * @date 2022-01-18
 */
@Service
public class ProductSubjectRelationServiceImpl implements IProductSubjectRelationService {
    @Autowired
    private ProductSubjectRelationMapper productSubjectRelationMapper;

    /**
     * 查询专题和商品关系
     *
     * @param id 专题和商品关系主键
     * @return 专题和商品关系
     */
    @Override
    public ProductSubjectRelation selectProductSubjectRelationById(Long id) {
        return productSubjectRelationMapper.selectProductSubjectRelationById(id);
    }

    /**
     * 查询专题和商品关系列表
     *
     * @param productSubjectRelation 专题和商品关系
     * @return 专题和商品关系
     */
    @Override
    public List<ProductSubjectRelation> selectProductSubjectRelationList(ProductSubjectRelation productSubjectRelation) {
        return productSubjectRelationMapper.selectProductSubjectRelationList(productSubjectRelation);
    }

    /**
     * 新增专题和商品关系
     *
     * @param productSubjectRelation 专题和商品关系
     * @return 结果
     */
    @Override
    public int insertProductSubjectRelation(ProductSubjectRelation productSubjectRelation) {
        productSubjectRelation.setTenantId(SecurityUtils.getUserId());
        productSubjectRelation.setCreateBy(SecurityUtils.getUsername());
        productSubjectRelation.setCreateTime(DateUtils.getNowDate());
        return productSubjectRelationMapper.insertProductSubjectRelation(productSubjectRelation);
    }

    /**
     * 修改专题和商品关系
     *
     * @param productSubjectRelation 专题和商品关系
     * @return 结果
     */
    @Override
    public int updateProductSubjectRelation(ProductSubjectRelation productSubjectRelation) {
        productSubjectRelation.setUpdateBy(SecurityUtils.getUsername());
        productSubjectRelation.setUpdateTime(DateUtils.getNowDate());
        return productSubjectRelationMapper.updateProductSubjectRelation(productSubjectRelation);
    }

    /**
     * 批量删除专题和商品关系
     *
     * @param ids 需要删除的专题和商品关系主键
     * @return 结果
     */
    @Override
    public int deleteProductSubjectRelationByIds(Long[] ids) {
        return productSubjectRelationMapper.deleteProductSubjectRelationByIds(ids);
    }

    /**
     * 删除专题和商品关系信息
     *
     * @param id 专题和商品关系主键
     * @return 结果
     */
    @Override
    public int deleteProductSubjectRelationById(Long id) {
        return productSubjectRelationMapper.deleteProductSubjectRelationById(id);
    }
}
