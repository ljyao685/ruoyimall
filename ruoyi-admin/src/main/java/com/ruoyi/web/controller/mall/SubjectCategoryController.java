package com.ruoyi.web.controller.mall;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.content.domain.SubjectCategory;
import com.ruoyi.content.service.ISubjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 专题分类信息Controller
 *
 * @author lb
 * @date 2022-01-13
 */
@RestController
@RequestMapping("/mall/subjectCategory")
public class SubjectCategoryController extends BaseController {
    @Autowired
    private ISubjectCategoryService subjectCategoryService;

    /**
     * 查询专题分类信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:subjectCategory:list')")
    @GetMapping("/list")
    public TableDataInfo list(SubjectCategory subjectCategory) {
        startPage();
        PageHelper.orderBy("sort desc,create_time desc");
        List<SubjectCategory> list = subjectCategoryService.selectSubjectCategoryList(subjectCategory);
        return getDataTable(list);
    }

    /**
     * 导出专题分类信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:subjectCategory:export')")
    @Log(title = "专题分类信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SubjectCategory subjectCategory) {
        List<SubjectCategory> list = subjectCategoryService.selectSubjectCategoryList(subjectCategory);
        ExcelUtil<SubjectCategory> util = new ExcelUtil<SubjectCategory>(SubjectCategory.class);
        return util.exportExcel(list, "专题分类信息数据");
    }

    /**
     * 获取专题分类信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:subjectCategory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(subjectCategoryService.selectSubjectCategoryById(id));
    }

    /**
     * 新增专题分类信息
     */
    @PreAuthorize("@ss.hasPermi('mall:subjectCategory:add')")
    @Log(title = "专题分类信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SubjectCategory subjectCategory) {
        return toAjax(subjectCategoryService.insertSubjectCategory(subjectCategory));
    }

    /**
     * 修改专题分类状态
     */
    @PreAuthorize("@ss.hasPermi('mall:subjectCategory:updateStatus')")
    @Log(title = "专题分类信息", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updateStatus")
    public AjaxResult updateStatus(@RequestBody SubjectCategory subjectCategory) {
        return toAjax(subjectCategoryService.updateSubjectCategoryStatus(subjectCategory));
    }

    /**
     * 修改专题分类信息
     */
    @PreAuthorize("@ss.hasPermi('mall:subjectCategory:edit')")
    @Log(title = "专题分类信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SubjectCategory subjectCategory) {
        return toAjax(subjectCategoryService.updateSubjectCategory(subjectCategory));
    }

    /**
     * 删除专题分类信息
     */
    @PreAuthorize("@ss.hasPermi('mall:subjectCategory:remove')")
    @Log(title = "专题分类信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(subjectCategoryService.deleteSubjectCategoryByIds(ids));
    }
}
