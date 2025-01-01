package com.ruoyi.product.service;

import com.ruoyi.product.domain.ProductMemberPrice;

import java.util.List;

/**
 * 商品会员价格信息Service接口
 *
 * @author lb
 * @date 2022-01-18
 */
public interface IProductMemberPriceService {
    /**
     * 查询商品会员价格信息
     *
     * @param id 商品会员价格信息主键
     * @return 商品会员价格信息
     */
    public ProductMemberPrice selectProductMemberPriceById(Long id);

    /**
     * 查询商品会员价格信息列表
     *
     * @param productMemberPrice 商品会员价格信息
     * @return 商品会员价格信息集合
     */
    public List<ProductMemberPrice> selectProductMemberPriceList(ProductMemberPrice productMemberPrice);

    /**
     * 新增商品会员价格信息
     *
     * @param productMemberPrice 商品会员价格信息
     * @return 结果
     */
    public int insertProductMemberPrice(ProductMemberPrice productMemberPrice);

    /**
     * 修改商品会员价格信息
     *
     * @param productMemberPrice 商品会员价格信息
     * @return 结果
     */
    public int updateProductMemberPrice(ProductMemberPrice productMemberPrice);

    /**
     * 批量删除商品会员价格信息
     *
     * @param ids 需要删除的商品会员价格信息主键集合
     * @return 结果
     */
    public int deleteProductMemberPriceByIds(Long[] ids);

    /**
     * 删除商品会员价格信息信息
     *
     * @param id 商品会员价格信息主键
     * @return 结果
     */
    public int deleteProductMemberPriceById(Long id);
}
