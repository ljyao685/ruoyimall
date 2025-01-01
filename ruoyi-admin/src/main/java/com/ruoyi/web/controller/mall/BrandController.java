package com.ruoyi.web.controller.mall;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.product.domain.Brand;
import com.ruoyi.product.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品品牌信息Controller
 *
 * @author lb
 * @date 2021-08-25
 */
@RestController
@RequestMapping("/mall/brand")
public class BrandController extends BaseController {
    @Autowired
    private IBrandService brandService;

    /**
     * 查询商品品牌信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:brand:list')")
    @GetMapping("/list")
    public TableDataInfo list(Brand brand) {
        startPage();
        PageHelper.orderBy("sort desc,create_time desc");
        List<Brand> list = brandService.selectBrandList(brand);
        return getDataTable(list);
    }

    /**
     * 导出商品品牌信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:brand:export')")
    @Log(title = "商品品牌信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Brand brand) {
        List<Brand> list = brandService.selectBrandList(brand);
        ExcelUtil<Brand> util = new ExcelUtil<Brand>(Brand.class);
        return util.exportExcel(list, "商品品牌信息数据");
    }

    /**
     * 获取商品品牌信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:brand:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(brandService.selectBrandById(id));
    }

    /**
     * 新增商品品牌信息
     */
    @PreAuthorize("@ss.hasPermi('mall:brand:add')")
    @Log(title = "商品品牌信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Brand brand) {
        return toAjax(brandService.insertBrand(brand));
    }

    /**
     * 修改商品品牌状态
     */
    @PreAuthorize("@ss.hasPermi('mall:brand:updateStatus')")
    @Log(title = "商品品牌信息", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updateStatus")
    public AjaxResult updateStatus(@RequestBody Brand brand) {
        return toAjax(brandService.updateBrandStatus(brand));
    }
    
    /**
     * 修改商品品牌信息
     */
    @PreAuthorize("@ss.hasPermi('mall:brand:edit')")
    @Log(title = "商品品牌信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Brand brand) {
        return toAjax(brandService.updateBrand(brand));
    }

    /**
     * 删除商品品牌信息
     */
    @PreAuthorize("@ss.hasPermi('mall:brand:remove')")
    @Log(title = "商品品牌信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(brandService.deleteBrandByIds(ids));
    }
}
