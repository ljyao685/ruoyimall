package com.ruoyi.product.service;

import com.ruoyi.product.domain.ProductLadder;

import java.util.List;

/**
 * 阶梯价格信息Service接口
 *
 * @author lb
 * @date 2022-01-18
 */
public interface IProductLadderService {
    /**
     * 查询阶梯价格信息
     *
     * @param id 阶梯价格信息主键
     * @return 阶梯价格信息
     */
    public ProductLadder selectProductLadderById(Long id);

    /**
     * 查询阶梯价格信息列表
     *
     * @param productLadder 阶梯价格信息
     * @return 阶梯价格信息集合
     */
    public List<ProductLadder> selectProductLadderList(ProductLadder productLadder);

    /**
     * 新增阶梯价格信息
     *
     * @param productLadder 阶梯价格信息
     * @return 结果
     */
    public int insertProductLadder(ProductLadder productLadder);

    /**
     * 修改阶梯价格信息
     *
     * @param productLadder 阶梯价格信息
     * @return 结果
     */
    public int updateProductLadder(ProductLadder productLadder);

    /**
     * 批量删除阶梯价格信息
     *
     * @param ids 需要删除的阶梯价格信息主键集合
     * @return 结果
     */
    public int deleteProductLadderByIds(Long[] ids);

    /**
     * 删除阶梯价格信息信息
     *
     * @param id 阶梯价格信息主键
     * @return 结果
     */
    public int deleteProductLadderById(Long id);
}
