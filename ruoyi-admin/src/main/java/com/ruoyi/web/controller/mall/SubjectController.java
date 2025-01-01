package com.ruoyi.web.controller.mall;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.content.domain.Subject;
import com.ruoyi.content.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 专题信息Controller
 *
 * @author lb
 * @date 2022-01-13
 */
@RestController
@RequestMapping("/mall/subject")
public class SubjectController extends BaseController {
    @Autowired
    private ISubjectService subjectService;

    /**
     * 查询专题信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:subject:list')")
    @GetMapping("/list")
    public TableDataInfo list(Subject subject) {
        startPage();
        PageHelper.orderBy("create_time desc");
        List<Subject> list = subjectService.selectSubjectList(subject);
        return getDataTable(list);
    }

    /**
     * 导出专题信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:subject:export')")
    @Log(title = "专题信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Subject subject) {
        List<Subject> list = subjectService.selectSubjectList(subject);
        ExcelUtil<Subject> util = new ExcelUtil<Subject>(Subject.class);
        return util.exportExcel(list, "专题信息数据");
    }

    /**
     * 获取专题信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:subject:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(subjectService.selectSubjectById(id));
    }

    /**
     * 新增专题信息
     */
    @PreAuthorize("@ss.hasPermi('mall:subject:add')")
    @Log(title = "专题信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Subject subject) {
        return toAjax(subjectService.insertSubject(subject));
    }

    /**
     * 修改专题状态
     */
    @PreAuthorize("@ss.hasPermi('mall:subject:updateStatus')")
    @Log(title = "专题信息", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updateStatus")
    public AjaxResult updateStatus(@RequestBody Subject subject) {
        return toAjax(subjectService.updateSubjectStatus(subject));
    }

    /**
     * 修改专题信息
     */
    @PreAuthorize("@ss.hasPermi('mall:subject:edit')")
    @Log(title = "专题信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Subject subject) {
        return toAjax(subjectService.updateSubject(subject));
    }

    /**
     * 删除专题信息
     */
    @PreAuthorize("@ss.hasPermi('mall:subject:remove')")
    @Log(title = "专题信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(subjectService.deleteSubjectByIds(ids));
    }
}
