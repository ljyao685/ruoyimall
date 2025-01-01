package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.ProductSubjectRelation;

import java.util.List;

/**
 * 专题和商品关系Mapper接口
 *
 * @author lb
 * @date 2022-01-18
 */
public interface ProductSubjectRelationMapper {
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
     * 批量新增专题和商品关系
     *
     * @param list 专题和商品关系列表
     * @return 结果
     */
    public int insertProductSubjectRelationList(List<ProductSubjectRelation> list);

    /**
     * 修改专题和商品关系
     *
     * @param productSubjectRelation 专题和商品关系
     * @return 结果
     */
    public int updateProductSubjectRelation(ProductSubjectRelation productSubjectRelation);

    /**
     * 删除商品ID专题和商品关系
     *
     * @param productId 商品ID
     * @return 结果
     */
    public int deleteProductSubjectRelationByProductId(Long productId);

    /**
     * 删除专题和商品关系
     *
     * @param id 专题和商品关系主键
     * @return 结果
     */
    public int deleteProductSubjectRelationById(Long id);

    /**
     * 批量删除专题和商品关系
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductSubjectRelationByIds(Long[] ids);
}
