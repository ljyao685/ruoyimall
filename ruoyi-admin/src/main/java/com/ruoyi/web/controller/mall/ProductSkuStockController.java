package com.ruoyi.web.controller.mall;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.product.domain.ProductSkuStock;
import com.ruoyi.product.service.IProductSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品sku库存信息Controller
 *
 * @author lb
 * @date 2021-08-26
 */
@RestController
@RequestMapping("/mall/prodSkuStock")
public class ProductSkuStockController extends BaseController {
    @Autowired
    private IProductSkuStockService productSkuStockService;

    /**
     * 查询商品sku库存信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:prodSkuStock:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductSkuStock productSkuStock) {
        startPage();
        List<ProductSkuStock> list = productSkuStockService.selectProductSkuStockList(productSkuStock);
        return getDataTable(list);
    }

    /**
     * 导出商品sku库存信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:prodSkuStock:export')")
    @Log(title = "商品sku库存信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProductSkuStock productSkuStock) {
        List<ProductSkuStock> list = productSkuStockService.selectProductSkuStockList(productSkuStock);
        ExcelUtil<ProductSkuStock> util = new ExcelUtil<ProductSkuStock>(ProductSkuStock.class);
        return util.exportExcel(list, "商品sku库存信息数据");
    }

    /**
     * 获取商品sku库存信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodSkuStock:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(productSkuStockService.selectProductSkuStockById(id));
    }

    /**
     * 新增商品sku库存信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodSkuStock:add')")
    @Log(title = "商品sku库存信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductSkuStock productSkuStock) {
        return toAjax(productSkuStockService.insertProductSkuStock(productSkuStock));
    }

    /**
     * 修改商品sku库存信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodSkuStock:edit')")
    @Log(title = "商品sku库存信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductSkuStock productSkuStock) {
        return toAjax(productSkuStockService.updateProductSkuStock(productSkuStock));
    }

    /**
     * 删除商品sku库存信息
     */
    @PreAuthorize("@ss.hasPermi('mall:prodSkuStock:remove')")
    @Log(title = "商品sku库存信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(productSkuStockService.deleteProductSkuStockByIds(ids));
    }
}
