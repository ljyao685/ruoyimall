package com.ruoyi.product.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.product.service.IProductMemberPriceService;
import com.ruoyi.product.domain.ProductMemberPrice;
import com.ruoyi.product.mapper.ProductMemberPriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品会员价格信息Service业务层处理
 *
 * @author lb
 * @date 2022-01-18
 */
@Service
public class ProductMemberPriceServiceImpl implements IProductMemberPriceService {
    @Autowired
    private ProductMemberPriceMapper productMemberPriceMapper;

    /**
     * 查询商品会员价格信息
     *
     * @param id 商品会员价格信息主键
     * @return 商品会员价格信息
     */
    @Override
    public ProductMemberPrice selectProductMemberPriceById(Long id) {
        return productMemberPriceMapper.selectProductMemberPriceById(id);
    }

    /**
     * 查询商品会员价格信息列表
     *
     * @param productMemberPrice 商品会员价格信息
     * @return 商品会员价格信息
     */
    @Override
    public List<ProductMemberPrice> selectProductMemberPriceList(ProductMemberPrice productMemberPrice) {
        return productMemberPriceMapper.selectProductMemberPriceList(productMemberPrice);
    }

    /**
     * 新增商品会员价格信息
     *
     * @param productMemberPrice 商品会员价格信息
     * @return 结果
     */
    @Override
    public int insertProductMemberPrice(ProductMemberPrice productMemberPrice) {
        productMemberPrice.setTenantId(SecurityUtils.getUserId());
        productMemberPrice.setCreateBy(SecurityUtils.getUsername());
        productMemberPrice.setCreateTime(DateUtils.getNowDate());
        return productMemberPriceMapper.insertProductMemberPrice(productMemberPrice);
    }

    /**
     * 修改商品会员价格信息
     *
     * @param productMemberPrice 商品会员价格信息
     * @return 结果
     */
    @Override
    public int updateProductMemberPrice(ProductMemberPrice productMemberPrice) {
        productMemberPrice.setUpdateBy(SecurityUtils.getUsername());
        productMemberPrice.setUpdateTime(DateUtils.getNowDate());
        return productMemberPriceMapper.updateProductMemberPrice(productMemberPrice);
    }

    /**
     * 批量删除商品会员价格信息
     *
     * @param ids 需要删除的商品会员价格信息主键
     * @return 结果
     */
    @Override
    public int deleteProductMemberPriceByIds(Long[] ids) {
        return productMemberPriceMapper.deleteProductMemberPriceByIds(ids);
    }

    /**
     * 删除商品会员价格信息信息
     *
     * @param id 商品会员价格信息主键
     * @return 结果
     */
    @Override
    public int deleteProductMemberPriceById(Long id) {
        return productMemberPriceMapper.deleteProductMemberPriceById(id);
    }
}
