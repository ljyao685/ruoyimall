package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.ProductSkuStock;

import java.util.List;

/**
 * 商品sku库存信息Mapper接口
 *
 * @author lb
 * @date 2021-08-26
 */
public interface ProductSkuStockMapper {
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
     * 批量新增商品sku库存信息
     *
     * @param list 商品sku库存信息列表
     * @return 结果
     */
    public int insertProductSkuStockList(List<ProductSkuStock> list);

    /**
     * 修改商品sku库存信息
     *
     * @param productSkuStock 商品sku库存信息
     * @return 结果
     */
    public int updateProductSkuStock(ProductSkuStock productSkuStock);

    /**
     * 删除商品ID对应的sku库存信息
     *
     * @param productId 商品ID
     * @return 结果
     */
    public int deleteProductSkuStockByProductId(Long productId);

    /**
     * 删除商品sku库存信息
     *
     * @param id 商品sku库存信息主键
     * @return 结果
     */
    public int deleteProductSkuStockById(Long id);

    /**
     * 批量删除商品sku库存信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductSkuStockByIds(Long[] ids);
}
