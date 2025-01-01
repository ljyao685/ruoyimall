package com.ruoyi.product.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.product.domain.ProductFullReduction;
import com.ruoyi.product.mapper.ProductFullReductionMapper;
import com.ruoyi.product.service.IProductFullReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 满减价格信息Service业务层处理
 *
 * @author lb
 * @date 2022-01-18
 */
@Service
public class ProductFullReductionServiceImpl implements IProductFullReductionService {
    @Autowired
    private ProductFullReductionMapper productFullReductionMapper;

    /**
     * 查询满减价格信息
     *
     * @param id 满减价格信息主键
     * @return 满减价格信息
     */
    @Override
    public ProductFullReduction selectProductFullReductionById(Long id) {
        return productFullReductionMapper.selectProductFullReductionById(id);
    }

    /**
     * 查询满减价格信息列表
     *
     * @param productFullReduction 满减价格信息
     * @return 满减价格信息
     */
    @Override
    public List<ProductFullReduction> selectProductFullReductionList(ProductFullReduction productFullReduction) {
        return productFullReductionMapper.selectProductFullReductionList(productFullReduction);
    }

    /**
     * 新增满减价格信息
     *
     * @param productFullReduction 满减价格信息
     * @return 结果
     */
    @Override
    public int insertProductFullReduction(ProductFullReduction productFullReduction) {
        productFullReduction.setTenantId(SecurityUtils.getUserId());
        productFullReduction.setCreateBy(SecurityUtils.getUsername());
        productFullReduction.setCreateTime(DateUtils.getNowDate());
        return productFullReductionMapper.insertProductFullReduction(productFullReduction);
    }

    /**
     * 修改满减价格信息
     *
     * @param productFullReduction 满减价格信息
     * @return 结果
     */
    @Override
    public int updateProductFullReduction(ProductFullReduction productFullReduction) {
        productFullReduction.setUpdateBy(SecurityUtils.getUsername());
        productFullReduction.setUpdateTime(DateUtils.getNowDate());
        return productFullReductionMapper.updateProductFullReduction(productFullReduction);
    }

    /**
     * 批量删除满减价格信息
     *
     * @param ids 需要删除的满减价格信息主键
     * @return 结果
     */
    @Override
    public int deleteProductFullReductionByIds(Long[] ids) {
        return productFullReductionMapper.deleteProductFullReductionByIds(ids);
    }

    /**
     * 删除满减价格信息信息
     *
     * @param id 满减价格信息主键
     * @return 结果
     */
    @Override
    public int deleteProductFullReductionById(Long id) {
        return productFullReductionMapper.deleteProductFullReductionById(id);
    }
}
