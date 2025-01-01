package com.ruoyi.web.controller.mall;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.product.domain.ProductAttributeCategory;
import com.ruoyi.product.service.IProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性分类信息Controller
 *
 * @author lb
 * @date 2021-08-25
 */
@RestController
@RequestMapping("/mall/prodAttrCate")
public class ProductAttributeCategoryController extends BaseController {
    @Autowired
    private IProductAttributeCategoryService productAttributeCategoryService;

    /**
     * 查询商品属性分类信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttrCate:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductAttributeCategory productAttributeCategory) {
        startPage();
        List<ProductAttributeCategory> list = productAttributeCategoryService.selectProductAttributeCategoryList(productAttributeCategory);
        return getDataTable(list);
    }

    /**
     * 获取所有商品属性分类及其下属性
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttrCate:withAttr')")
    @GetMapping("/withAttr")
    public TableDataInfo getListWithAttr() {
        List<ProductAttributeCategory> list = productAttributeCategoryService.selectProdAttrCateListWithAttr();
        return getDataTable(list);
    }

    /**
     * 导出商品属性分类信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttrCate:export')")
    @Log(title = "商品属性分类信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProductAttributeCategory productAttributeCategory) {
        List<ProductAttributeCategory> list = productAttributeCategoryService.selectProductAttributeCategoryList(productAttributeCategory);
        ExcelUtil<ProductAttributeCategory> util = new ExcelUtil<ProductAttributeCategory>(ProductAttributeCategory.class);
        return util.exportExcel(list, "商品属性分类信息数据");
    }

    /**
     * 获取商品属性分类信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttrCate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(productAttributeCategoryService.selectProductAttributeCategoryById(id));
    }

    /**
     * 新增商品属性分类信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttrCate:add')")
    @Log(title = "商品属性分类信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductAttributeCategory productAttributeCategory) {
        return toAjax(productAttributeCategoryService.insertProductAttributeCategory(productAttributeCategory));
    }

    /**
     * 修改商品属性分类信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttrCate:edit')")
    @Log(title = "商品属性分类信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductAttributeCategory productAttributeCategory) {
        return toAjax(productAttributeCategoryService.updateProductAttributeCategory(productAttributeCategory));
    }

    /**
     * 删除商品属性分类信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttrCate:remove')")
    @Log(title = "商品属性分类信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(productAttributeCategoryService.deleteProductAttributeCategoryByIds(ids));
    }
}
