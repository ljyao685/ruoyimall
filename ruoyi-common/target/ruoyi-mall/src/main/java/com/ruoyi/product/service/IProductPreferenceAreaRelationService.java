package com.ruoyi.product.service;

import com.ruoyi.product.domain.ProductPreferenceAreaRelation;

import java.util.List;

/**
 * 优选专区和商品的关系Service接口
 *
 * @author lb
 * @date 2022-01-18
 */
public interface IProductPreferenceAreaRelationService {
    /**
     * 查询优选专区和商品的关系
     *
     * @param id 优选专区和商品的关系主键
     * @return 优选专区和商品的关系
     */
    public ProductPreferenceAreaRelation selectProductPreferenceAreaRelationById(Long id);

    /**
     * 查询优选专区和商品的关系列表
     *
     * @param productPreferenceAreaRelation 优选专区和商品的关系
     * @return 优选专区和商品的关系集合
     */
    public List<ProductPreferenceAreaRelation> selectProductPreferenceAreaRelationList(ProductPreferenceAreaRelation productPreferenceAreaRelation);

    /**
     * 新增优选专区和商品的关系
     *
     * @param productPreferenceAreaRelation 优选专区和商品的关系
     * @return 结果
     */
    public int insertProductPreferenceAreaRelation(ProductPreferenceAreaRelation productPreferenceAreaRelation);

    /**
     * 修改优选专区和商品的关系
     *
     * @param productPreferenceAreaRelation 优选专区和商品的关系
     * @return 结果
     */
    public int updateProductPreferenceAreaRelation(ProductPreferenceAreaRelation productPreferenceAreaRelation);

    /**
     * 批量删除优选专区和商品的关系
     *
     * @param ids 需要删除的优选专区和商品的关系主键集合
     * @return 结果
     */
    public int deleteProductPreferenceAreaRelationByIds(Long[] ids);

    /**
     * 删除优选专区和商品的关系信息
     *
     * @param id 优选专区和商品的关系主键
     * @return 结果
     */
    public int deleteProductPreferenceAreaRelationById(Long id);
}
