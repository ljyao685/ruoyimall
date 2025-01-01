package com.ruoyi.web.controller.mall;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.product.domain.ProductAttributeValue;
import com.ruoyi.product.service.IProductAttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性值信息Controller
 *
 * @author lb
 * @date 2021-08-25
 */
@RestController
@RequestMapping("/mall/prodAttrValue")
public class ProductAttributeValueController extends BaseController {
    @Autowired
    private IProductAttributeValueService productAttributeValueService;

    /**
     * 查询商品属性值信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttrValue:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductAttributeValue productAttributeValue) {
        startPage();
        List<ProductAttributeValue> list = productAttributeValueService.selectProductAttributeValueList(productAttributeValue);
        return getDataTable(list);
    }

    /**
     * 导出商品属性值信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttrValue:export')")
    @Log(title = "商品属性值信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProductAttributeValue productAttributeValue) {
        List<ProductAttributeValue> list = productAttributeValueService.selectProductAttributeValueList(productAttributeValue);
        ExcelUtil<ProductAttributeValue> util = new ExcelUtil<ProductAttributeValue>(ProductAttributeValue.class);
        return util.exportExcel(list, "商品属性值信息数据");
    }

    /**
     * 获取商品属性值信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttrValue:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(productAttributeValueService.selectProductAttributeValueById(id));
    }

    /**
     * 新增商品属性值信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttrValue:add')")
    @Log(title = "商品属性值信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductAttributeValue productAttributeValue) {
        return toAjax(productAttributeValueService.insertProductAttributeValue(productAttributeValue));
    }

    /**
     * 修改商品属性值信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttrValue:edit')")
    @Log(title = "商品属性值信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductAttributeValue productAttributeValue) {
        return toAjax(productAttributeValueService.updateProductAttributeValue(productAttributeValue));
    }

    /**
     * 删除商品属性值信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodAttrValue:remove')")
    @Log(title = "商品属性值信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(productAttributeValueService.deleteProductAttributeValueByIds(ids));
    }
}
