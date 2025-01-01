package com.ruoyi.product.service;

import com.ruoyi.product.domain.Product;
import com.ruoyi.product.domain.ProductParam;
import com.ruoyi.product.domain.ProductSkuStock;

import java.util.List;

/**
 * 商品信息Service接口
 *
 * @author lb
 * @date 2021-08-25
 */
public interface IProductService {
    /**
     * 查询商品信息
     *
     * @param id 商品信息主键
     * @return 商品信息
     */
    public Product selectProductById(Long id);

    /**
     * 查询商品相关信息
     *
     * @param id 商品信息主键
     * @return 商品信息
     */
    public ProductParam selectProductAllInfoById(Long id);

    /**
     * 查询商品信息列表
     *
     * @param product 商品信息
     * @return 商品信息集合
     */
    public List<Product> selectProductList(Product product);

    /**
     * 新增商品信息
     *
     * @param productParam 商品参数信息
     * @return 结果
     */
    public int insertProduct(ProductParam productParam);

    /**
     * 修改商品状态信息
     *
     * @param product 商品信息
     * @return 结果
     */
    public int updateProductStatus(Product product);

    /**
     * 修改商品信息
     *
     * @param productParam 商品参数信息
     * @return 结果
     */
    public int updateProduct(ProductParam productParam);

    /**
     * 修改商品sku库存信息
     *
     * @param skuStockList 商品sku库存列表
     * @return 结果
     */
    public int updateProductSkuList(List<ProductSkuStock> skuStockList);

    /**
     * 批量删除商品信息
     *
     * @param ids 需要删除的商品信息主键集合
     * @return 结果
     */
    public int deleteProductByIds(Long[] ids);

    /**
     * 删除商品信息信息
     *
     * @param id 商品信息主键
     * @return 结果
     */
    public int deleteProductById(Long id);
}
