package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.Brand;

import java.util.List;

/**
 * 商品品牌信息Mapper接口
 *
 * @author lb
 * @date 2021-08-25
 */
public interface BrandMapper {
    /**
     * 查询商品品牌信息
     *
     * @param id 商品品牌信息主键
     * @return 商品品牌信息
     */
    public Brand selectBrandById(Long id);

    /**
     * 查询商品品牌信息列表
     *
     * @param brand 商品品牌信息
     * @return 商品品牌信息集合
     */
    public List<Brand> selectBrandList(Brand brand);

    /**
     * 新增商品品牌信息
     *
     * @param brand 商品品牌信息
     * @return 结果
     */
    public int insertBrand(Brand brand);

    /**
     * 修改商品品牌信息
     *
     * @param brand 商品品牌信息
     * @return 结果
     */
    public int updateBrand(Brand brand);

    /**
     * 删除商品品牌信息
     *
     * @param id 商品品牌信息主键
     * @return 结果
     */
    public int deleteBrandById(Long id);

    /**
     * 批量删除商品品牌信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrandByIds(Long[] ids);
}
