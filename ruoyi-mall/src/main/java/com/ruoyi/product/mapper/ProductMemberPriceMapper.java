package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.ProductMemberPrice;

import java.util.List;

/**
 * 商品会员价格信息Mapper接口
 *
 * @author lb
 * @date 2022-01-18
 */
public interface ProductMemberPriceMapper {
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
     * 批量新增商品会员价格信息
     *
     * @param list 商品会员价格信息列表
     * @return 结果
     */
    public int insertProductMemberPriceList(List<ProductMemberPrice> list);

    /**
     * 修改商品会员价格信息
     *
     * @param productMemberPrice 商品会员价格信息
     * @return 结果
     */
    public int updateProductMemberPrice(ProductMemberPrice productMemberPrice);

    /**
     * 删除商品ID对应的会员价格信息
     *
     * @param productId 商品ID
     * @return 结果
     */
    public int deleteProductMemberPriceByProductId(Long productId);

    /**
     * 删除商品会员价格信息
     *
     * @param id 商品会员价格信息主键
     * @return 结果
     */
    public int deleteProductMemberPriceById(Long id);

    /**
     * 批量删除商品会员价格信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductMemberPriceByIds(Long[] ids);
}
