package com.ruoyi.product.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.product.domain.ProductSkuStock;
import com.ruoyi.product.mapper.ProductSkuStockMapper;
import com.ruoyi.product.service.IProductSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品sku库存信息Service业务层处理
 *
 * @author lb
 * @date 2021-08-26
 */
@Service
public class ProductSkuStockServiceImpl implements IProductSkuStockService {
    @Autowired
    private ProductSkuStockMapper productSkuStockMapper;

    /**
     * 查询商品sku库存信息
     *
     * @param id 商品sku库存信息主键
     * @return 商品sku库存信息
     */
    @Override
    public ProductSkuStock selectProductSkuStockById(Long id) {
        return productSkuStockMapper.selectProductSkuStockById(id);
    }

    /**
     * 查询商品sku库存信息列表
     *
     * @param productSkuStock 商品sku库存信息
     * @return 商品sku库存信息
     */
    @Override
    public List<ProductSkuStock> selectProductSkuStockList(ProductSkuStock productSkuStock) {
        return productSkuStockMapper.selectProductSkuStockList(productSkuStock);
    }

    /**
     * 新增商品sku库存信息
     *
     * @param productSkuStock 商品sku库存信息
     * @return 结果
     */
    @Override
    public int insertProductSkuStock(ProductSkuStock productSkuStock) {
        productSkuStock.setTenantId(SecurityUtils.getUserId());
        productSkuStock.setCreateBy(SecurityUtils.getUsername());
        productSkuStock.setCreateTime(DateUtils.getNowDate());
        return productSkuStockMapper.insertProductSkuStock(productSkuStock);
    }

    /**
     * 修改商品sku库存信息
     *
     * @param productSkuStock 商品sku库存信息
     * @return 结果
     */
    @Override
    public int updateProductSkuStock(ProductSkuStock productSkuStock) {
        productSkuStock.setUpdateBy(SecurityUtils.getUsername());
        productSkuStock.setUpdateTime(DateUtils.getNowDate());
        return productSkuStockMapper.updateProductSkuStock(productSkuStock);
    }

    /**
     * 批量删除商品sku库存信息
     *
     * @param ids 需要删除的商品sku库存信息主键
     * @return 结果
     */
    @Override
    public int deleteProductSkuStockByIds(Long[] ids) {
        return productSkuStockMapper.deleteProductSkuStockByIds(ids);
    }

    /**
     * 删除商品sku库存信息信息
     *
     * @param id 商品sku库存信息主键
     * @return 结果
     */
    @Override
    public int deleteProductSkuStockById(Long id) {
        return productSkuStockMapper.deleteProductSkuStockById(id);
    }
}
