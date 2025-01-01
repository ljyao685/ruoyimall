package com.ruoyi.web.controller.mall;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.product.domain.ProductAttribute;
import com.ruoyi.product.service.IProductAttributeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性信息Controller
 *
 * @author lb
 * @date 2021-08-25
 */
@RestController
@RequestMapping("/mall/prodAttr")
public class ProductAttributeController extends BaseController {
    @Autowired
    private IProductAttributeService productAttributeService;

    /**
     * 查询商品属性信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttr:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductAttribute productAttribute) {
        startPage();
        List<ProductAttribute> list = productAttributeService.selectProductAttributeList(productAttribute);
        return getDataTable(list);
    }

    /**
     * 根据商品分类的id获取商品属性及属性分类
     *
     * @param productCategoryId
     * @return
     */
    @ApiOperation("根据商品分类的id获取商品属性及属性分类")
    @GetMapping("/attrInfo/{productCategoryId}")
    public TableDataInfo getAttrInfo(@PathVariable Long productCategoryId) {
        List<ProductAttribute> productAttrInfoList = productAttributeService.selectProductAttrInfo(productCategoryId);
        return getDataTable(productAttrInfoList);
    }

    /**
     * 导出商品属性信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttr:export')")
    @Log(title = "商品属性信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProductAttribute productAttribute) {
        List<ProductAttribute> list = productAttributeService.selectProductAttributeList(productAttribute);
        ExcelUtil<ProductAttribute> util = new ExcelUtil<ProductAttribute>(ProductAttribute.class);
        return util.exportExcel(list, "商品属性信息数据");
    }

    /**
     * 获取商品属性信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttr:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(productAttributeService.selectProductAttributeById(id));
    }

    /**
     * 新增商品属性信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttr:add')")
    @Log(title = "商品属性信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductAttribute productAttribute) {
        return toAjax(productAttributeService.insertProductAttribute(productAttribute));
    }

    /**
     * 修改商品属性信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttr:edit')")
    @Log(title = "商品属性信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductAttribute productAttribute) {
        return toAjax(productAttributeService.updateProductAttribute(productAttribute));
    }

    /**
     * 删除商品属性信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttr:remove')")
    @Log(title = "商品属性信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(productAttributeService.deleteProductAttributeByIds(ids));
    }
}
