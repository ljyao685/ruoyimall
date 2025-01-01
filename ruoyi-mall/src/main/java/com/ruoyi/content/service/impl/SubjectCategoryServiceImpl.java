package com.ruoyi.content.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.content.domain.Subject;
import com.ruoyi.content.domain.SubjectCategory;
import com.ruoyi.content.mapper.SubjectCategoryMapper;
import com.ruoyi.content.mapper.SubjectMapper;
import com.ruoyi.content.service.ISubjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专题分类信息Service业务层处理
 *
 * @author lb
 * @date 2022-01-13
 */
@Service
public class SubjectCategoryServiceImpl implements ISubjectCategoryService {
    @Autowired
    private SubjectCategoryMapper subjectCategoryMapper;
    @Autowired
    private SubjectMapper subjectMapper;

    /**
     * 查询专题分类信息
     *
     * @param id 专题分类信息主键
     * @return 专题分类信息
     */
    @Override
    public SubjectCategory selectSubjectCategoryById(Long id) {
        return subjectCategoryMapper.selectSubjectCategoryById(id);
    }

    /**
     * 查询专题分类信息列表
     *
     * @param subjectCategory 专题分类信息
     * @return 专题分类信息
     */
    @Override
    public List<SubjectCategory> selectSubjectCategoryList(SubjectCategory subjectCategory) {
        return subjectCategoryMapper.selectSubjectCategoryList(subjectCategory);
    }

    /**
     * 新增专题分类信息
     *
     * @param subjectCategory 专题分类信息
     * @return 结果
     */
    @Override
    public int insertSubjectCategory(SubjectCategory subjectCategory) {
        subjectCategory.setTenantId(SecurityUtils.getUserId());
        subjectCategory.setCreateBy(SecurityUtils.getUsername());
        subjectCategory.setCreateTime(DateUtils.getNowDate());
        return subjectCategoryMapper.insertSubjectCategory(subjectCategory);
    }

    /**
     * 修改专题分类状态
     *
     * @param subjectCategory 专题分类信息
     * @return 结果
     */
    @Override
    public int updateSubjectCategoryStatus(SubjectCategory subjectCategory) {
        return subjectCategoryMapper.updateSubjectCategory(subjectCategory);
    }

    /**
     * 修改专题分类信息
     *
     * @param subjectCategory 专题分类信息
     * @return 结果
     */
    @Override
    public int updateSubjectCategory(SubjectCategory subjectCategory) {
        //获取修改前的专题分类信息
        SubjectCategory oldSubCate = subjectCategoryMapper.selectSubjectCategoryById(subjectCategory.getId());
        //修改专题分类信息
        subjectCategory.setUpdateBy(SecurityUtils.getUsername());
        subjectCategory.setUpdateTime(DateUtils.getNowDate());
        int count = subjectCategoryMapper.updateSubjectCategory(subjectCategory);
        //判断是否修改了分类名称
        if (!subjectCategory.getName().equals(oldSubCate.getName())) {
            //更新专题分类时要更新商品中的名称
            Subject subject = new Subject();
            subject.setSubjectCategoryId(subjectCategory.getId());
            subject.setSubjectCategoryName(subjectCategory.getName());
            subjectMapper.updateSubjectByXXId(subject);
        }
        return count;
    }

    /**
     * 批量删除专题分类信息
     *
     * @param ids 需要删除的专题分类信息主键
     * @return 结果
     */
    @Override
    public int deleteSubjectCategoryByIds(Long[] ids) {
        return subjectCategoryMapper.deleteSubjectCategoryByIds(ids);
    }

    /**
     * 删除专题分类信息信息
     *
     * @param id 专题分类信息主键
     * @return 结果
     */
    @Override
    public int deleteSubjectCategoryById(Long id) {
        return subjectCategoryMapper.deleteSubjectCategoryById(id);
    }
}
