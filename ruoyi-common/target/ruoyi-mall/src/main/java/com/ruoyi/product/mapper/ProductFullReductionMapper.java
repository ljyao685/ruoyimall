package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.ProductFullReduction;

import java.util.List;

/**
 * 满减价格信息Mapper接口
 *
 * @author lb
 * @date 2022-01-18
 */
public interface ProductFullReductionMapper {
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
     * 批量新增满减价格信息
     *
     * @param list 满减价格信息列表
     * @return 结果
     */
    public int insertProductFullReductionList(List<ProductFullReduction> list);

    /**
     * 修改满减价格信息
     *
     * @param productFullReduction 满减价格信息
     * @return 结果
     */
    public int updateProductFullReduction(ProductFullReduction productFullReduction);

    /**
     * 删除商品ID对应的满减价格信息
     *
     * @param productId 商品ID
     * @return 结果
     */
    public int deleteProductFullReductionByProductId(Long productId);

    /**
     * 删除满减价格信息
     *
     * @param id 满减价格信息主键
     * @return 结果
     */
    public int deleteProductFullReductionById(Long id);

    /**
     * 批量删除满减价格信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductFullReductionByIds(Long[] ids);
}
