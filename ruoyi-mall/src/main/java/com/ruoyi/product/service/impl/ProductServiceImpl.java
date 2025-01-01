package com.ruoyi.product.service.impl;

import com.ruoyi.common.utils.CommonUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.content.mapper.PreferenceAreaMapper;
import com.ruoyi.content.mapper.SubjectMapper;
import com.ruoyi.product.domain.*;
import com.ruoyi.product.mapper.*;
import com.ruoyi.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品信息Service业务层处理
 *
 * @author lb
 * @date 2021-08-25
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductMemberPriceMapper productMemberPriceMapper;
    @Autowired
    private ProductLadderMapper productLadderMapper;
    @Autowired
    private ProductFullReductionMapper productFullReductionMapper;
    @Autowired
    private ProductSkuStockMapper productSkuStockMapper;
    @Autowired
    private ProductAttributeValueMapper productAttributeValueMapper;
    @Autowired
    private ProductSubjectRelationMapper productSubjectRelationMapper;
    @Autowired
    private ProductPreferenceAreaRelationMapper productPreferenceAreaRelationMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private PreferenceAreaMapper preferenceAreaMapper;

    /**
     * 查询商品信息
     *
     * @param id 商品信息主键
     * @return 商品信息
     */
    @Override
    public Product selectProductById(Long id) {
        return productMapper.selectProductById(id);
    }

    /**
     * 查询商品相关信息(包含会员价格、满减价格、优惠价格等信息)
     *
     * @param id 商品信息主键
     * @return 商品信息
     */
    @Override
    public ProductParam selectProductAllInfoById(Long id) {
        return productMapper.selectProductAllInfoById(id);
    }

    /**
     * 查询商品信息列表
     *
     * @param product 商品信息
     * @return 商品信息
     */
    @Override
    public List<Product> selectProductList(Product product) {
        return productMapper.selectProductList(product);
    }

    /**
     * 新增商品信息
     *
     * @param productParam 商品参数信息
     * @return 结果
     */
    @Override
    public int insertProduct(ProductParam productParam) {
        //新增商品
        Product product = productParam;
        product.setTenantId(SecurityUtils.getUserId());
        product.setCreateBy(SecurityUtils.getUsername());
        product.setCreateTime(DateUtils.getNowDate());
        //组合关键字(商品名称#品牌#分类#副标题)
        String keywords = product.getName() + "#" + product.getBrandName() + "#" + product.getProductCategoryName() + "#" + product.getSubTitle();
        product.setKeywords(keywords);
        int count = productMapper.insertProduct(product);
        //修改品牌对应的商品数量
        Brand brand = brandMapper.selectBrandById(product.getBrandId());
        if (StringUtils.isNotNull(brand)) {
            brand.setProductCount(brand.getProductCount() + 1);
            brandMapper.updateBrand(brand);
        }
        //修改商品分类对应的商品数量
        ProductCategory category = productCategoryMapper.selectProductCategoryById(product.getProductCategoryId());
        if (StringUtils.isNotNull(category)) {
            category.setProductCount(category.getProductCount() + 1);
            productCategoryMapper.updateProductCategory(category);
        }
        //根据促销类型设置价格：会员价格、阶梯价格、满减价格
        Long productId = product.getId();
        //会员价格
        relateAndInsertList(productMemberPriceMapper, "insertProductMemberPriceList", productParam.getMemberPriceList(), productId);
        //阶梯价格
        relateAndInsertList(productLadderMapper, "insertProductLadderList", productParam.getProductLadderList(), productId);
        //满减价格
        relateAndInsertList(productFullReductionMapper, "insertProductFullReductionList", productParam.getProductFullReductionList(), productId);
        //处理sku的编码
        handleSkuStockCode(productParam.getSkuStockList(), productId);
        //添加sku库存信息
        relateAndInsertList(productSkuStockMapper, "insertProductSkuStockList", productParam.getSkuStockList(), productId);
        //添加商品参数,添加自定义商品规格
        relateAndInsertList(productAttributeValueMapper, "insertProductAttributeValueList", productParam.getProductAttributeValueList(), productId);
        //关联专题
        relateAndInsertList(productSubjectRelationMapper, "insertProductSubjectRelationList", productParam.getSubjectProductRelationList(), productId);
        //关联优选
        relateAndInsertList(productPreferenceAreaRelationMapper, "insertProductPreferenceAreaRelationList", productParam.getPreferenceAreaProductRelationList(), productId);
        //更新专题和优选专区的商品数量
        relateAndUpdateProductCount(subjectMapper, "updateSubjectProductCountByIds", productParam.getSubjectProductRelationList(), "subjectId");
        relateAndUpdateProductCount(preferenceAreaMapper, "updatePreferenceAreaProductCountByIds", productParam.getPreferenceAreaProductRelationList(), "preferenceAreaId");
        return count;
    }

    //处理sku编码为空的信息
    private void handleSkuStockCode(List<ProductSkuStock> skuStockList, Long productId) {
        if (CollectionUtils.isEmpty(skuStockList)) return;
        for (int i = 0; i < skuStockList.size(); i++) {
            ProductSkuStock skuStock = skuStockList.get(i);
            if (StringUtils.isEmpty(skuStock.getSkuCode())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i + 1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }

    /**
     * 修改商品状态信息
     *
     * @param product 商品信息
     * @return 结果
     */
    @Override
    public int updateProductStatus(Product product) {
        return productMapper.updateProduct(product);
    }

    /**
     * 修改商品信息
     *
     * @param productParam 商品参数信息
     * @return 结果
     */
    @Override
    public int updateProduct(ProductParam productParam) {
        //获取修改前的商品信息
        Product oldProduct = productMapper.selectProductById(productParam.getId());
        //修改商品信息
        Product product = productParam;
        product.setUpdateBy(SecurityUtils.getUsername());
        product.setUpdateTime(DateUtils.getNowDate());
        //组合关键字(商品名称#品牌#分类#副标题)
        String keywords = product.getName() + "#" + product.getBrandName() + "#" + product.getProductCategoryName() + "#" + product.getSubTitle();
        product.setKeywords(keywords);
        int count = productMapper.updateProduct(product);
        //判断是否修改过品牌信息
        if (!product.getBrandId().equals(oldProduct.getBrandId())) {
            //原来品牌商品数量减1
            Brand oldBrand = brandMapper.selectBrandById(oldProduct.getBrandId());
            if (StringUtils.isNotNull(oldBrand)) {
                oldBrand.setProductCount(oldBrand.getProductCount() - 1);
                brandMapper.updateBrand(oldBrand);
            }
            //当前品牌商品数量加1
            Brand brand = brandMapper.selectBrandById(product.getBrandId());
            if (StringUtils.isNotNull(brand)) {
                brand.setProductCount(brand.getProductCount() + 1);
                brandMapper.updateBrand(brand);
            }
        }
        //判断是否修改过商品分类
        if (!product.getProductCategoryId().equals(oldProduct.getProductCategoryId())) {
            //原来分类商品数量减1
            ProductCategory oldCate = productCategoryMapper.selectProductCategoryById(oldProduct.getProductCategoryId());
            if (StringUtils.isNotNull(oldCate)) {
                oldCate.setProductCount(oldCate.getProductCount() - 1);
                productCategoryMapper.updateProductCategory(oldCate);
            }
            //当前分类商品数量加1
            ProductCategory cate = productCategoryMapper.selectProductCategoryById(product.getProductCategoryId());
            if (StringUtils.isNotNull(cate)) {
                cate.setProductCount(cate.getProductCount() + 1);
                productCategoryMapper.updateProductCategory(cate);
            }
        }
        //商品ID
        Long productId = product.getId();
        //会员价格（先删除商品对应的信息，重新插入）
        productMemberPriceMapper.deleteProductMemberPriceByProductId(productId);
        relateAndInsertList(productMemberPriceMapper, "insertProductMemberPriceList", productParam.getMemberPriceList(), productId);
        //阶梯价格
        productLadderMapper.deleteProductLadderByProductId(productId);
        relateAndInsertList(productLadderMapper, "insertProductLadderList", productParam.getProductLadderList(), productId);
        //满减价格
        productFullReductionMapper.deleteProductFullReductionByProductId(productId);
        relateAndInsertList(productFullReductionMapper, "insertProductFullReductionList", productParam.getProductFullReductionList(), productId);
        //修改sku库存信息
        handleUpdateSkuStockList(productId, productParam.getSkuStockList());
        //修改商品参数
        productAttributeValueMapper.deleteProductAttributeValueByProductId(productId);
        relateAndInsertList(productAttributeValueMapper, "insertProductAttributeValueList", productParam.getProductAttributeValueList(), productId);
        //关联专题
        productSubjectRelationMapper.deleteProductSubjectRelationByProductId(productId);
        relateAndInsertList(productSubjectRelationMapper, "insertProductSubjectRelationList", productParam.getSubjectProductRelationList(), productId);
        //关联优选
        productPreferenceAreaRelationMapper.deleteProductPreferenceAreaRelationByProductId(productId);
        relateAndInsertList(productPreferenceAreaRelationMapper, "insertProductPreferenceAreaRelationList", productParam.getPreferenceAreaProductRelationList(), productId);
        //更新专题和优选专区的商品数量
        relateAndUpdateProductCount(subjectMapper, "updateSubjectProductCountByIds", productParam.getSubjectProductRelationList(), "subjectId");
        relateAndUpdateProductCount(preferenceAreaMapper, "updatePreferenceAreaProductCountByIds", productParam.getPreferenceAreaProductRelationList(), "preferenceAreaId");
        return count;
    }

    /**
     * 修改商品sku库存信息
     *
     * @param skuStockList 商品sku库存列表
     * @return 结果
     */
    @Override
    public int updateProductSkuList(List<ProductSkuStock> skuStockList) {
        int count = 0;
        for (ProductSkuStock skuStock : skuStockList) {
            skuStock.setUpdateBy(SecurityUtils.getUsername());
            skuStock.setUpdateTime(DateUtils.getNowDate());
            count = productSkuStockMapper.updateProductSkuStock(skuStock);
        }
        return count;
    }

    //修改商品sku信息
    private void handleUpdateSkuStockList(Long productId, List<ProductSkuStock> currSkuList) {
        //当前没有sku直接删除
        if (StringUtils.isEmpty(currSkuList)) {
            productSkuStockMapper.deleteProductSkuStockByProductId(productId);
            return;
        }
        //获取初始sku信息
        List<ProductSkuStock> oriStuList = productSkuStockMapper.selectProductSkuStockList(new ProductSkuStock(productId));
        //获取新增sku信息
        List<ProductSkuStock> insertSkuList = currSkuList.stream().filter(item -> item.getId() == null).collect(Collectors.toList());
        //获取需要更新的sku信息
        List<ProductSkuStock> updateSkuList = currSkuList.stream().filter(item -> item.getId() != null).collect(Collectors.toList());
        List<Long> updateSkuIds = updateSkuList.stream().map(ProductSkuStock::getId).collect(Collectors.toList());
        //获取需要删除的sku信息
        List<ProductSkuStock> removeSkuList = oriStuList.stream().filter(item -> !updateSkuIds.contains(item.getId())).collect(Collectors.toList());
        handleSkuStockCode(insertSkuList, productId);
        handleSkuStockCode(updateSkuList, productId);
        //新增sku信息
        if (StringUtils.isNotEmpty(insertSkuList) && insertSkuList.size() > 0) {
            relateAndInsertList(productSkuStockMapper, "insertProductSkuStockList", insertSkuList, productId);
        }
        //删除sku信息
        if (StringUtils.isNotEmpty(removeSkuList) && removeSkuList.size() > 0) {
            Long[] removeSkuIds = removeSkuList.stream().map(ProductSkuStock::getId).toArray(Long[]::new);
            productSkuStockMapper.deleteProductSkuStockByIds(removeSkuIds);
        }
        //修改sku信息
        if (StringUtils.isNotEmpty(updateSkuList) && updateSkuList.size() > 0) {
            for (ProductSkuStock updateSku : updateSkuList) {
                productSkuStockMapper.updateProductSkuStock(updateSku);
            }
        }

    }

    /**
     * 建立和插入关系表操作
     *
     * @param dao        操作dao层
     * @param methodName 执行的方法名称
     * @param dataList   要插入的数据
     * @param productId  建立关系的id
     * @return 处理后的列表
     */
    private void relateAndInsertList(Object dao, String methodName, List dataList, Long productId) {
        try {
            if (StringUtils.isEmpty(dataList)) return;
            for (Object item : dataList) {
                Method setTenantId = item.getClass().getMethod("setTenantId", Long.class);
                setTenantId.invoke(item, SecurityUtils.getUserId());
                Method setCreateBy = item.getClass().getMethod("setCreateBy", String.class);
                setCreateBy.invoke(item, SecurityUtils.getUsername());
                Method setCreateTime = item.getClass().getMethod("setCreateTime", Date.class);
                setCreateTime.invoke(item, DateUtils.getNowDate());
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod(methodName, List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 关联更新对应的商品数量
     *
     * @param dao        执行dao层
     * @param methodName 方法名
     * @param dataList   数据列表
     * @param key        对象对应的key
     */
    private void relateAndUpdateProductCount(Object dao, String methodName, List dataList, String key) {
        try {
            if (StringUtils.isEmpty(dataList)) return;
            Long[] ids = new Long[dataList.size()];
            for (int i = 0; i < dataList.size(); i++) {
                Object obj = dataList.get(i);
                //获取对象中key对应的value值  如：ProductSubjectRelation中subjectId的值
                Long id = (Long) CommonUtil.getValueByKey(obj, key);
                ids[i] = id;
            }
            Method updateProductCount = dao.getClass().getMethod(methodName, new Class[]{Long[].class});
            updateProductCount.invoke(dao, new Object[]{ids});//invoke方法的第二个参数接受的是Object
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 批量删除商品信息
     *
     * @param ids 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteProductByIds(Long[] ids) {
        for (Long id : ids) {
            //获取修改前的商品信息
            Product product = productMapper.selectProductById(id);
            //品牌商品数量减1
            Brand brand = brandMapper.selectBrandById(product.getBrandId());
            if (StringUtils.isNotNull(brand)) {
                brand.setProductCount(brand.getProductCount() - 1);
                brandMapper.updateBrand(brand);
            }
            //分类商品数量减1
            ProductCategory category = productCategoryMapper.selectProductCategoryById(product.getProductCategoryId());
            if (StringUtils.isNotNull(category)) {
                category.setProductCount(category.getProductCount() - 1);
                productCategoryMapper.updateProductCategory(category);
            }
            //获取商品关联的专题和优选专区列表
            List<ProductSubjectRelation> subjectRelationList = productSubjectRelationMapper.selectProductSubjectRelationList(new ProductSubjectRelation(id));
            List<ProductPreferenceAreaRelation> productPreferenceAreaRelationList = productPreferenceAreaRelationMapper.selectProductPreferenceAreaRelationList(new ProductPreferenceAreaRelation(id));
            //更新专题和优选专区的商品数量
            relateAndUpdateProductCount(subjectMapper, "updateSubjectProductCountByIds", subjectRelationList, "subjectId");
            relateAndUpdateProductCount(preferenceAreaMapper, "updatePreferenceAreaProductCountByIds", productPreferenceAreaRelationList, "preferenceAreaId");
        }
        return productMapper.deleteProductByIds(ids);
    }

    /**
     * 删除商品信息信息
     *
     * @param id 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteProductById(Long id) {
        return productMapper.deleteProductById(id);
    }
}
