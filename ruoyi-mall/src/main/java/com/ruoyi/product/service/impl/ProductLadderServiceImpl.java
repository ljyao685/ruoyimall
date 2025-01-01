package com.ruoyi.product.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.product.domain.ProductLadder;
import com.ruoyi.product.mapper.ProductLadderMapper;
import com.ruoyi.product.service.IProductLadderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 阶梯价格信息Service业务层处理
 *
 * @author lb
 * @date 2022-01-18
 */
@Service
public class ProductLadderServiceImpl implements IProductLadderService {
    @Autowired
    private ProductLadderMapper productLadderMapper;

    /**
     * 查询阶梯价格信息
     *
     * @param id 阶梯价格信息主键
     * @return 阶梯价格信息
     */
    @Override
    public ProductLadder selectProductLadderById(Long id) {
        return productLadderMapper.selectProductLadderById(id);
    }

    /**
     * 查询阶梯价格信息列表
     *
     * @param productLadder 阶梯价格信息
     * @return 阶梯价格信息
     */
    @Override
    public List<ProductLadder> selectProductLadderList(ProductLadder productLadder) {
        return productLadderMapper.selectProductLadderList(productLadder);
    }

    /**
     * 新增阶梯价格信息
     *
     * @param productLadder 阶梯价格信息
     * @return 结果
     */
    @Override
    public int insertProductLadder(ProductLadder productLadder) {
        productLadder.setTenantId(SecurityUtils.getUserId());
        productLadder.setCreateBy(SecurityUtils.getUsername());
        productLadder.setCreateTime(DateUtils.getNowDate());
        return productLadderMapper.insertProductLadder(productLadder);
    }

    /**
     * 修改阶梯价格信息
     *
     * @param productLadder 阶梯价格信息
     * @return 结果
     */
    @Override
    public int updateProductLadder(ProductLadder productLadder) {
        productLadder.setUpdateBy(SecurityUtils.getUsername());
        productLadder.setUpdateTime(DateUtils.getNowDate());
        return productLadderMapper.updateProductLadder(productLadder);
    }

    /**
     * 批量删除阶梯价格信息
     *
     * @param ids 需要删除的阶梯价格信息主键
     * @return 结果
     */
    @Override
    public int deleteProductLadderByIds(Long[] ids) {
        return productLadderMapper.deleteProductLadderByIds(ids);
    }

    /**
     * 删除阶梯价格信息信息
     *
     * @param id 阶梯价格信息主键
     * @return 结果
     */
    @Override
    public int deleteProductLadderById(Long id) {
        return productLadderMapper.deleteProductLadderById(id);
    }
}
