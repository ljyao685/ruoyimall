package com.ruoyi.product.service;

import com.ruoyi.product.domain.ProductSkuStock;

import java.util.List;

/**
 * 商品sku库存信息Service接口
 *
 * @author lb
 * @date 2021-08-26
 */
public interface IProductSkuStockService {
    /**
     * 查询商品sku库存信息
     *
     * @param id 商品sku库存信息主键
     * @return 商品sku库存信息
     */
    public ProductSkuStock selectProductSkuStockById(Long id);

    /**
     * 查询商品sku库存信息列表
     *
     * @param productSkuStock 商品sku库存信息
     * @return 商品sku库存信息集合
     */
    public List<ProductSkuStock> selectProductSkuStockList(ProductSkuStock productSkuStock);

    /**
     * 新增商品sku库存信息
     *
     * @param productSkuStock 商品sku库存信息
     * @return 结果
     */
    public int insertProductSkuStock(ProductSkuStock productSkuStock);

    /**
     * 修改商品sku库存信息
     *
     * @param productSkuStock 商品sku库存信息
     * @return 结果
     */
    public int updateProductSkuStock(ProductSkuStock productSkuStock);

    /**
     * 批量删除商品sku库存信息
     *
     * @param ids 需要删除的商品sku库存信息主键集合
     * @return 结果
     */
    public int deleteProductSkuStockByIds(Long[] ids);

    /**
     * 删除商品sku库存信息信息
     *
     * @param id 商品sku库存信息主键
     * @return 结果
     */
    public int deleteProductSkuStockById(Long id);
}
