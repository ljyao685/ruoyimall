package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.Product;
import com.ruoyi.product.domain.ProductParam;

import java.util.List;

/**
 * 商品信息Mapper接口
 *
 * @author lb
 * @date 2021-08-25
 */
public interface ProductMapper {
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
     * @param product 商品信息
     * @return 结果
     */
    public int insertProduct(Product product);

    /**
     * 修改商品信息
     *
     * @param product 商品信息
     * @return 结果
     */
    public int updateProduct(Product product);

    /**
     * 修改品牌ID/商品分类ID对应的相关名称
     *
     * @param product 商品信息
     * @return 结果
     */
    public int updateProductByXXId(Product product);

    /**
     * 删除商品信息
     *
     * @param id 商品信息主键
     * @return 结果
     */
    public int deleteProductById(Long id);

    /**
     * 批量删除商品信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductByIds(Long[] ids);
}
