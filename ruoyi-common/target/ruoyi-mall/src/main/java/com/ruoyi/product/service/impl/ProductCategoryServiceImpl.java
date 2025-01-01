package com.ruoyi.product.service.impl;

import com.ruoyi.common.core.domain.CascaderSelect;
import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.product.domain.Product;
import com.ruoyi.product.domain.ProductCategory;
import com.ruoyi.product.domain.ProductCategoryAttributeRelation;
import com.ruoyi.product.mapper.ProductCategoryAttributeRelationMapper;
import com.ruoyi.product.mapper.ProductCategoryMapper;
import com.ruoyi.product.mapper.ProductMapper;
import com.ruoyi.product.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品分类信息Service业务层处理
 *
 * @author lb
 * @date 2021-08-25
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询商品分类信息
     *
     * @param id 商品分类信息主键
     * @return 商品分类信息
     */
    @Override
    public ProductCategory selectProductCategoryById(Long id) {
        return productCategoryMapper.selectProductCategoryById(id);
    }

    /**
     * 查询商品分类信息列表
     *
     * @param productCategory 商品分类信息
     * @return 商品分类信息
     */
    @Override
    public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory) {
        return productCategoryMapper.selectProductCategoryList(productCategory);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param productCategory 商品分类信息
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildCategoryTreeList(ProductCategory productCategory) {
        List<ProductCategory> productCategoryList = productCategoryMapper.selectProductCategoryList(productCategory);
        List<ProductCategory> cates = buildCategory(productCategoryList);
        return cates.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要级联器结构
     *
     * @param productCategory 商品分类信息
     * @return 级联结构列表
     */
    @Override
    public List<CascaderSelect> buildCategoryCascaderSelect(ProductCategory productCategory) {
        List<ProductCategory> productCategoryList = productCategoryMapper.selectProductCategoryList(productCategory);
        List<ProductCategory> cates = buildCategory(productCategoryList);
        return cates.stream().map(CascaderSelect::new).collect(Collectors.toList());
    }

    /**
     * 新增商品分类信息
     *
     * @param productCategory 商品分类信息
     * @return 结果
     */
    @Override
    public int insertProductCategory(ProductCategory productCategory) {
        productCategory.setTenantId(SecurityUtils.getUserId());
        productCategory.setCreateBy(SecurityUtils.getUsername());
        productCategory.setCreateTime(DateUtils.getNowDate());
        setCategoryLevel(productCategory);
        int count = productCategoryMapper.insertProductCategory(productCategory);
        //创建筛选属性关联
        List<Long> productAttributeIdList = productCategory.getProductAttributeIdList();
        if (StringUtils.isNotEmpty(productAttributeIdList)) {
            insertRelationList(productCategory.getId(), productAttributeIdList);
        }
        return count;
    }

    /**
     * 修改商品分类状态
     *
     * @param productCategory 商品分类信息
     * @return 结果
     */
    @Override
    public int updateProductCategoryStatus(ProductCategory productCategory) {
        return productCategoryMapper.updateProductCategory(productCategory);
    }

    /**
     * 修改商品分类信息
     *
     * @param productCategory 商品分类信息
     * @return 结果
     */
    @Override
    public int updateProductCategory(ProductCategory productCategory) {
        //获取修改前的分类信息
        ProductCategory oldProdCate = productCategoryMapper.selectProductCategoryById(productCategory.getId());
        //修改商品分类信息
        productCategory.setUpdateBy(SecurityUtils.getUsername());
        productCategory.setUpdateTime(DateUtils.getNowDate());
        setCategoryLevel(productCategory);
        int count = productCategoryMapper.updateProductCategory(productCategory);
        //判断是否修改了分类名称
        if (!productCategory.getName().equals(oldProdCate.getName())) {
            //更新商品分类时要更新商品中的名称
            Product product = new Product();
            product.setProductCategoryId(productCategory.getId());
            product.setProductCategoryName(productCategory.getName());
            productMapper.updateProductByXXId(product);
        }
        //同时更新筛选属性的信息
        if (!StringUtils.isEmpty(productCategory.getProductAttributeIdList()) && productCategory.getProductAttributeIdList().size() > 0) {
            productCategoryAttributeRelationMapper.deleteProductCategoryAttributeRelationByProdCateId(productCategory.getId());
            insertRelationList(productCategory.getId(), productCategory.getProductAttributeIdList());
        } else {
            productCategoryAttributeRelationMapper.deleteProductCategoryAttributeRelationByProdCateId(productCategory.getId());
        }
        return count;
    }

    /**
     * 批量删除商品分类信息
     *
     * @param ids 需要删除的商品分类信息主键
     * @return 结果
     */
    @Override
    public int deleteProductCategoryByIds(Long[] ids) {
        return productCategoryMapper.deleteProductCategoryByIds(ids);
    }

    /**
     * 删除商品分类信息信息
     *
     * @param id 商品分类信息主键
     * @return 结果
     */
    @Override
    public int deleteProductCategoryById(Long id) {
        return productCategoryMapper.deleteProductCategoryById(id);
    }

    /**
     * 根据分类的parentId设置分类的level
     */
    private void setCategoryLevel(ProductCategory productCategory) {
        //没有父分类时为一级分类
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            ProductCategory parentCategory = productCategoryMapper.selectProductCategoryById(productCategory.getParentId());
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }

    /**
     * 批量插入商品分类与筛选属性关系表
     *
     * @param productCategoryId      商品分类id
     * @param productAttributeIdList 相关商品筛选属性id集合
     */
    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<ProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIdList) {
            ProductCategoryAttributeRelation relation = new ProductCategoryAttributeRelation();
            relation.setTenantId(SecurityUtils.getUserId());
            relation.setCreateBy(SecurityUtils.getUsername());
            relation.setCreateTime(DateUtils.getNowDate());
            relation.setProductAttributeId(productAttrId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        productCategoryAttributeRelationMapper.insertProductCategoryAttributeRelationList(relationList);
    }

    /**
     * 构建级联结构
     *
     * @param categorys
     * @return
     */
    public List<ProductCategory> buildCategory(List<ProductCategory> categorys) {
        List<ProductCategory> returnList = new ArrayList<>();
        for (Iterator<ProductCategory> iterator = categorys.iterator(); iterator.hasNext(); ) {
            ProductCategory cate = (ProductCategory) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (StringUtils.isNotNull(cate.getParentId()) && cate.getParentId().longValue() == 0L) {
                recursionFn(categorys, cate);
                returnList.add(cate);
            }
        }
        if (returnList.isEmpty()) {
            returnList = categorys;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<ProductCategory> list, ProductCategory t) {
        // 得到子节点列表
        List<ProductCategory> childList = getChildList(list, t);
        t.setChildren(childList);
        for (ProductCategory tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<ProductCategory> getChildList(List<ProductCategory> list, ProductCategory t) {
        List<ProductCategory> tlist = new ArrayList<ProductCategory>();
        Iterator<ProductCategory> it = list.iterator();
        while (it.hasNext()) {
            ProductCategory n = (ProductCategory) it.next();
            if (n.getParentId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<ProductCategory> list, ProductCategory t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
