package com.ruoyi.product.service;

import com.ruoyi.product.domain.ProductFullReduction;

import java.util.List;

/**
 * 满减价格信息Service接口
 *
 * @author lb
 * @date 2022-01-18
 */
public interface IProductFullReductionService {
    /**
     * 查询满减价格信息
     *
     * @param id 满减价格信息主键
     * @return 满减价格信息
     */
    public ProductFullReduction selectProductFullReductionById(Long id);

    /**
     * 查询满减价格信息列表
     *
     * @param productFullReduction 满减价格信息
     * @return 满减价格信息集合
     */
    public List<ProductFullReduction> selectProductFullReductionList(ProductFullReduction productFullReduction);

    /**
     * 新增满减价格信息
     *
     * @param productFullReduction 满减价格信息
     * @return 结果
     */
    public int insertProductFullReduction(ProductFullReduction productFullReduction);

    /**
     * 修改满减价格信息
     *
     * @param productFullReduction 满减价格信息
     * @return 结果
     */
    public int updateProductFullReduction(ProductFullReduction productFullReduction);

    /**
     * 批量删除满减价格信息
     *
     * @param ids 需要删除的满减价格信息主键集合
     * @return 结果
     */
    public int deleteProductFullReductionByIds(Long[] ids);

    /**
     * 删除满减价格信息信息
     *
     * @param id 满减价格信息主键
     * @return 结果
     */
    public int deleteProductFullReductionById(Long id);
}
