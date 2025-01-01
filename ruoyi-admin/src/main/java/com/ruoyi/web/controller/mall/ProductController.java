package com.ruoyi.web.controller.mall;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.product.domain.Product;
import com.ruoyi.product.domain.ProductParam;
import com.ruoyi.product.domain.ProductSkuStock;
import com.ruoyi.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品信息Controller
 *
 * @author lb
 * @date 2021-08-25
 */
@RestController
@RequestMapping("/mall/product")
public class ProductController extends BaseController {
    @Autowired
    private IProductService productService;

    /**
     * 查询商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(Product product) {
        startPage();
        PageHelper.orderBy("sort desc,create_time desc");
        List<Product> list = productService.selectProductList(product);
        return getDataTable(list);
    }

    /**
     * 导出商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:product:export')")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Product product) {
        List<Product> list = productService.selectProductList(product);
        ExcelUtil<Product> util = new ExcelUtil<Product>(Product.class);
        return util.exportExcel(list, "商品信息数据");
    }

    /**
     * 获取商品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:product:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(productService.selectProductAllInfoById(id));
    }

    /**
     * 新增商品信息
     */
    @PreAuthorize("@ss.hasPermi('mall:product:add')")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductParam productParam) {
        return toAjax(productService.insertProduct(productParam));
    }

    /**
     * 修改商品状态信息
     */
    @PreAuthorize("@ss.hasPermi('mall:product:updateStatus')")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateStatus")
    public AjaxResult updateStatus(@RequestBody Product product) {
        return toAjax(productService.updateProductStatus(product));
    }

    /**
     * 修改商品信息
     */
    @PreAuthorize("@ss.hasPermi('mall:product:edit')")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductParam productParam) {
        return toAjax(productService.updateProduct(productParam));
    }

    /**
     * 修改商品sku库存信息
     */
    @PreAuthorize("@ss.hasPermi('mall:product:updateSku')")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateSku")
    public AjaxResult updateProductSku(@RequestBody List<ProductSkuStock> skuStockList) {
        return toAjax(productService.updateProductSkuList(skuStockList));
    }

    /**
     * 删除商品信息
     */
    @PreAuthorize("@ss.hasPermi('mall:product:remove')")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(productService.deleteProductByIds(ids));
    }
}
