package com.ruoyi.web.controller.mall;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.member.domain.MemberLevel;
import com.ruoyi.member.service.IMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员等级信息Controller
 *
 * @author lb
 * @date 2022-01-12
 */
@RestController
@RequestMapping("/mall/memberLevel")
public class MemberLevelController extends BaseController {
    @Autowired
    private IMemberLevelService memberLevelService;

    /**
     * 查询会员等级信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:memberLevel:list')")
    @GetMapping("/list")
    public TableDataInfo list(MemberLevel memberLevel) {
        startPage();
        List<MemberLevel> list = memberLevelService.selectMemberLevelList(memberLevel);
        return getDataTable(list);
    }

    /**
     * 导出会员等级信息列表
     */
    @PreAuthorize("@ss.hasPermi('mall:memberLevel:export')")
    @Log(title = "会员等级信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MemberLevel memberLevel) {
        List<MemberLevel> list = memberLevelService.selectMemberLevelList(memberLevel);
        ExcelUtil<MemberLevel> util = new ExcelUtil<MemberLevel>(MemberLevel.class);
        return util.exportExcel(list, "会员等级信息数据");
    }

    /**
     * 获取会员等级信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:memberLevel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(memberLevelService.selectMemberLevelById(id));
    }

    /**
     * 新增会员等级信息
     */
    @PreAuthorize("@ss.hasPermi('mall:memberLevel:add')")
    @Log(title = "会员等级信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberLevel memberLevel) {
        return toAjax(memberLevelService.insertMemberLevel(memberLevel));
    }

    /**
     * 修改会员等级信息
     */
    @PreAuthorize("@ss.hasPermi('mall:memberLevel:edit')")
    @Log(title = "会员等级信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberLevel memberLevel) {
        return toAjax(memberLevelService.updateMemberLevel(memberLevel));
    }

    /**
     * 删除会员等级信息
     */
    @PreAuthorize("@ss.hasPermi('mall:memberLevel:remove')")
    @Log(title = "会员等级信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(memberLevelService.deleteMemberLevelByIds(ids));
    }
}
