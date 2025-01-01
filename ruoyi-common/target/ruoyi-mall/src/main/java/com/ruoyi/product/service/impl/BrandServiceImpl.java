package com.ruoyi.product.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.product.domain.Brand;
import com.ruoyi.product.domain.Product;
import com.ruoyi.product.mapper.BrandMapper;
import com.ruoyi.product.mapper.ProductMapper;
import com.ruoyi.product.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 商品品牌信息Service业务层处理
 *
 * @author lb
 * @date 2021-08-25
 */
@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询商品品牌信息
     *
     * @param id 商品品牌信息主键
     * @return 商品品牌信息
     */
    @Override
    public Brand selectBrandById(Long id) {
        return brandMapper.selectBrandById(id);
    }

    /**
     * 查询商品品牌信息列表
     *
     * @param brand 商品品牌信息
     * @return 商品品牌信息
     */
    @Override
    public List<Brand> selectBrandList(Brand brand) {
        return brandMapper.selectBrandList(brand);
    }

    /**
     * 新增商品品牌信息
     *
     * @param brand 商品品牌信息
     * @return 结果
     */
    @Override
    public int insertBrand(Brand brand) {
        brand.setTenantId(SecurityUtils.getUserId());
        brand.setCreateBy(SecurityUtils.getUsername());
        brand.setCreateTime(DateUtils.getNowDate());
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(brand.getFirstLetter())) {
            brand.setFirstLetter(brand.getName().substring(0, 1));
        }
        return brandMapper.insertBrand(brand);
    }

    /**
     * 修改商品品牌状态
     *
     * @param brand 商品品牌信息
     * @return 结果
     */
    @Override
    public int updateBrandStatus(Brand brand) {
        return brandMapper.updateBrand(brand);
    }

    /**
     * 修改商品品牌信息
     *
     * @param brand 商品品牌信息
     * @return 结果
     */
    @Override
    public int updateBrand(Brand brand) {
        //获取修改前的品牌信息
        Brand oldBrand = brandMapper.selectBrandById(brand.getId());
        //修改品牌信息
        brand.setUpdateBy(SecurityUtils.getUsername());
        brand.setUpdateTime(DateUtils.getNowDate());
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(brand.getFirstLetter())) {
            brand.setFirstLetter(brand.getName().substring(0, 1));
        }
        int count = brandMapper.updateBrand(brand);
        //判断是否修改了品牌名称
        if (!brand.getName().equals(oldBrand.getName())) {
            //更新品牌时要更新商品中的品牌名称
            Product product = new Product();
            product.setProductCategoryId(brand.getId());
            product.setProductCategoryName(brand.getName());
            productMapper.updateProductByXXId(product);
        }
        return count;
    }

    /**
     * 批量删除商品品牌信息
     *
     * @param ids 需要删除的商品品牌信息主键
     * @return 结果
     */
    @Override
    public int deleteBrandByIds(Long[] ids) {
        return brandMapper.deleteBrandByIds(ids);
    }

    /**
     * 删除商品品牌信息信息
     *
     * @param id 商品品牌信息主键
     * @return 结果
     */
    @Override
    public int deleteBrandById(Long id) {
        return brandMapper.deleteBrandById(id);
    }
}
