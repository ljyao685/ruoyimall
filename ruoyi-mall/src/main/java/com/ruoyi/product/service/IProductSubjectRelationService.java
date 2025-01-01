package com.ruoyi.product.service;

import com.ruoyi.product.domain.ProductSubjectRelation;

import java.util.List;

/**
 * 专题和商品关系Service接口
 *
 * @author lb
 * @date 2022-01-18
 */
public interface IProductSubjectRelationService {
    /**
     * 查询专题和商品关系
     *
     * @param id 专题和商品关系主键
     * @return 专题和商品关系
     */
    public ProductSubjectRelation selectProductSubjectRelationById(Long id);

    /**
     * 查询专题和商品关系列表
     *
     * @param productSubjectRelation 专题和商品关系
     * @return 专题和商品关系集合
     */
    public List<ProductSubjectRelation> selectProductSubjectRelationList(ProductSubjectRelation productSubjectRelation);

    /**
     * 新增专题和商品关系
     *
     * @param productSubjectRelation 专题和商品关系
     * @return 结果
     */
    public int insertProductSubjectRelation(ProductSubjectRelation productSubjectRelation);

    /**
     * 修改专题和商品关系
     *
     * @param productSubjectRelation 专题和商品关系
     * @return 结果
     */
    public int updateProductSubjectRelation(ProductSubjectRelation productSubjectRelation);

    /**
     * 批量删除专题和商品关系
     *
     * @param ids 需要删除的专题和商品关系主键集合
     * @return 结果
     */
    public int deleteProductSubjectRelationByIds(Long[] ids);

    /**
     * 删除专题和商品关系信息
     *
     * @param id 专题和商品关系主键
     * @return 结果
     */
    public int deleteProductSubjectRelationById(Long id);
}
