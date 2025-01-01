package com.ruoyi.web.controller.mall;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.product.domain.ProductCategoryAttributeRelation;
import com.ruoyi.product.service.IProductCategoryAttributeRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类属性关联信息Controller
 *
 * @author lb
 * @date 2021-08-26
 */
@RestController
@RequestMapping("/mall/prodCateAttrRelation")
public class ProductCategoryAttributeRelationController extends BaseController {
    @Autowired
    private IProductCategoryAttributeRelationService productCategoryAttributeRelationService;

    /**
     * 查询商品分类属性关联信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCateAttrRelation:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductCategoryAttributeRelation productCategoryAttributeRelation) {
        startPage();
        List<ProductCategoryAttributeRelation> list = productCategoryAttributeRelationService.selectProductCategoryAttributeRelationList(productCategoryAttributeRelation);
        return getDataTable(list);
    }

    /**
     * 导出商品分类属性关联信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCateAttrRelation:export')")
    @Log(title = "商品分类属性关联信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProductCategoryAttributeRelation productCategoryAttributeRelation) {
        List<ProductCategoryAttributeRelation> list = productCategoryAttributeRelationService.selectProductCategoryAttributeRelationList(productCategoryAttributeRelation);
        ExcelUtil<ProductCategoryAttributeRelation> util = new ExcelUtil<ProductCategoryAttributeRelation>(ProductCategoryAttributeRelation.class);
        return util.exportExcel(list, "商品分类属性关联信息数据");
    }

    /**
     * 获取商品分类属性关联信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCateAttrRelation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(productCategoryAttributeRelationService.selectProductCategoryAttributeRelationById(id));
    }

    /**
     * 新增商品分类属性关联信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCateAttrRelation:add')")
    @Log(title = "商品分类属性关联信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductCategoryAttributeRelation productCategoryAttributeRelation) {
        return toAjax(productCategoryAttributeRelationService.insertProductCategoryAttributeRelation(productCategoryAttributeRelation));
    }

    /**
     * 修改商品分类属性关联信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCateAttrRelation:edit')")
    @Log(title = "商品分类属性关联信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductCategoryAttributeRelation productCategoryAttributeRelation) {
        return toAjax(productCategoryAttributeRelationService.updateProductCategoryAttributeRelation(productCategoryAttributeRelation));
    }

    /**
     * 删除商品分类属性关联信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCateAttrRelation:remove')")
    @Log(title = "商品分类属性关联信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(productCategoryAttributeRelationService.deleteProductCategoryAttributeRelationByIds(ids));
    }
}
