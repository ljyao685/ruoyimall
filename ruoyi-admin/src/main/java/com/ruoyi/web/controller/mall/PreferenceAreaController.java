package com.ruoyi.mall.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.content.domain.PreferenceArea;
import com.ruoyi.content.service.IPreferenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优选专区信息Controller
 *
 * @author lb
 * @date 2022-01-14
 */
@RestController
@RequestMapping("/mall/preferenceArea")
public class PreferenceAreaController extends BaseController {
    @Autowired
    private IPreferenceAreaService preferenceAreaService;

    /**
     * 查询优选专区信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:preferenceArea:list')")
    @GetMapping("/list")
    public TableDataInfo list(PreferenceArea preferenceArea) {
        startPage();
        List<PreferenceArea> list = preferenceAreaService.selectPreferenceAreaList(preferenceArea);
        return getDataTable(list);
    }

    /**
     * 导出优选专区信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:preferenceArea:export')")
    @Log(title = "优选专区信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PreferenceArea preferenceArea) {
        List<PreferenceArea> list = preferenceAreaService.selectPreferenceAreaList(preferenceArea);
        ExcelUtil<PreferenceArea> util = new ExcelUtil<PreferenceArea>(PreferenceArea.class);
        return util.exportExcel(list, "优选专区信息数据");
    }

    /**
     * 获取优选专区信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:preferenceArea:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(preferenceAreaService.selectPreferenceAreaById(id));
    }

    /**
     * 新增优选专区信息
     */
    @PreAuthorize("@ss.hasPermi('mall:preferenceArea:add')")
    @Log(title = "优选专区信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PreferenceArea preferenceArea) {
        return toAjax(preferenceAreaService.insertPreferenceArea(preferenceArea));
    }

    /**
     * 修改优选专区信息
     */
    @PreAuthorize("@ss.hasPermi('mall:preferenceArea:edit')")
    @Log(title = "优选专区信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PreferenceArea preferenceArea) {
        return toAjax(preferenceAreaService.updatePreferenceArea(preferenceArea));
    }

    /**
     * 删除优选专区信息
     */
    @PreAuthorize("@ss.hasPermi('mall:preferenceArea:remove')")
    @Log(title = "优选专区信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(preferenceAreaService.deletePreferenceAreaByIds(ids));
    }
}
