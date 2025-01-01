package com.ruoyi.web.controller.mall;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.CascaderSelect;
import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.product.domain.ProductCategory;
import com.ruoyi.product.service.IProductAttributeService;
import com.ruoyi.product.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类信息Controller
 *
 * @author lb
 * @date 2021-08-25
 */
@RestController
@RequestMapping("/mall/prodCate")
public class ProductCategoryController extends BaseController {
    @Autowired
    private IProductCategoryService productCategoryService;
    @Autowired
    private IProductAttributeService productAttributeService;

    /**
     * 查询商品分类信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCate:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductCategory productCategory) {
//        startPage(); //查询所有，取消分页
        PageHelper.orderBy("sort desc,create_time desc");
        List<ProductCategory> list = productCategoryService.selectProductCategoryList(productCategory);
        return getDataTable(list);
    }

    /**
     * 查询商品分类下拉树列表
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCate:treeList')")
    @GetMapping("/treeList")
    public TableDataInfo treeList(ProductCategory productCategory) {
        PageHelper.orderBy("sort desc,create_time desc");
        List<TreeSelect> list = productCategoryService.buildCategoryTreeList(productCategory);
        return getDataTable(list);
    }

    /**
     * 查询商品分类级联选择列表
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCate:cascaderList')")
    @GetMapping("/cascaderList")
    public TableDataInfo cascaderList(ProductCategory productCategory) {
        PageHelper.orderBy("sort desc,create_time desc");
        List<CascaderSelect> list = productCategoryService.buildCategoryCascaderSelect(productCategory);
        return getDataTable(list);
    }

    /**
     * 导出商品分类信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCate:export')")
    @Log(title = "商品分类信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProductCategory productCategory) {
        List<ProductCategory> list = productCategoryService.selectProductCategoryList(productCategory);
        ExcelUtil<ProductCategory> util = new ExcelUtil<ProductCategory>(ProductCategory.class);
        return util.exportExcel(list, "商品分类信息数据");
    }

    /**
     * 获取商品分类信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        AjaxResult result = AjaxResult.success(productCategoryService.selectProductCategoryById(id));
        result.put("attrInfo", productAttributeService.selectProductAttrInfo(id));
        return result;
    }

    /**
     * 新增商品分类信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCate:add')")
    @Log(title = "商品分类信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductCategory productCategory) {
        return toAjax(productCategoryService.insertProductCategory(productCategory));
    }

    /**
     * 修改商品分类状态
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCate:updateStatus')")
    @Log(title = "商品分类信息", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updateStatus")
    public AjaxResult updateStatus(@RequestBody ProductCategory productCategory) {
        return toAjax(productCategoryService.updateProductCategoryStatus(productCategory));
    }

    /**
     * 修改商品分类信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCate:edit')")
    @Log(title = "商品分类信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductCategory productCategory) {
        return toAjax(productCategoryService.updateProductCategory(productCategory));
    }

    /**
     * 删除商品分类信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodCate:remove')")
    @Log(title = "商品分类信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(productCategoryService.deleteProductCategoryByIds(ids));
    }
}
