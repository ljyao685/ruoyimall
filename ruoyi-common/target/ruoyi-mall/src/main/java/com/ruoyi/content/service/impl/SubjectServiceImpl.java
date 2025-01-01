package com.ruoyi.content.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.content.domain.Subject;
import com.ruoyi.content.domain.SubjectCategory;
import com.ruoyi.content.mapper.SubjectCategoryMapper;
import com.ruoyi.content.mapper.SubjectMapper;
import com.ruoyi.content.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专题信息Service业务层处理
 *
 * @author lb
 * @date 2022-01-13
 */
@Service
public class SubjectServiceImpl implements ISubjectService {
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private SubjectCategoryMapper subjectCategoryMapper;

    /**
     * 查询专题信息
     *
     * @param id 专题信息主键
     * @return 专题信息
     */
    @Override
    public Subject selectSubjectById(Long id) {
        return subjectMapper.selectSubjectById(id);
    }

    /**
     * 查询专题信息列表
     *
     * @param subject 专题信息
     * @return 专题信息
     */
    @Override
    public List<Subject> selectSubjectList(Subject subject) {
        return subjectMapper.selectSubjectList(subject);
    }

    /**
     * 新增专题信息
     *
     * @param subject 专题信息
     * @return 结果
     */
    @Override
    public int insertSubject(Subject subject) {
        subject.setTenantId(SecurityUtils.getUserId());
        subject.setCreateBy(SecurityUtils.getUsername());
        subject.setCreateTime(DateUtils.getNowDate());
        int count = subjectMapper.insertSubject(subject);
        //修改专题分类对应的专题数量
        SubjectCategory subjectCategory = subjectCategoryMapper.selectSubjectCategoryById(subject.getSubjectCategoryId());
        if (StringUtils.isNotNull(subjectCategory)) {
            subjectCategory.setSubjectCount(subjectCategory.getSubjectCount() + 1);
            subjectCategoryMapper.updateSubjectCategory(subjectCategory);
        }
        return count;
    }

    /**
     * 修改专题状态
     *
     * @param subject 专题信息
     * @return 结果
     */
    @Override
    public int updateSubjectStatus(Subject subject) {
        return subjectMapper.updateSubject(subject);
    }

    /**
     * 修改专题信息
     *
     * @param subject 专题信息
     * @return 结果
     */
    @Override
    public int updateSubject(Subject subject) {
        //获取修改前的专题信息
        Subject oldSubject = subjectMapper.selectSubjectById(subject.getId());
        //修改专题信息
        subject.setUpdateBy(SecurityUtils.getUsername());
        subject.setUpdateTime(DateUtils.getNowDate());
        int count = subjectMapper.updateSubject(subject);
        //判断是否修改过专题分类信息
        if (!subject.getSubjectCategoryId().equals(oldSubject.getSubjectCategoryId())) {
            //原来分类专题数量减1
            SubjectCategory oldSubCate = subjectCategoryMapper.selectSubjectCategoryById(oldSubject.getSubjectCategoryId());
            if (StringUtils.isNotNull(oldSubCate)) {
                oldSubCate.setSubjectCount(oldSubCate.getSubjectCount() - 1);
                subjectCategoryMapper.updateSubjectCategory(oldSubCate);
            }
            //当前分类专题数量加1
            SubjectCategory subCate = subjectCategoryMapper.selectSubjectCategoryById(subject.getSubjectCategoryId());
            if (StringUtils.isNotNull(subCate)) {
                subCate.setSubjectCount(subCate.getSubjectCount() + 1);
                subjectCategoryMapper.updateSubjectCategory(subCate);
            }
        }
        return count;
    }

    /**
     * 批量删除专题信息
     *
     * @param ids 需要删除的专题信息主键
     * @return 结果
     */
    @Override
    public int deleteSubjectByIds(Long[] ids) {
        for (Long id : ids) {
            //获取修改前的专题信息
            Subject subject = subjectMapper.selectSubjectById(id);
            //分类专题数量减1
            SubjectCategory subCate = subjectCategoryMapper.selectSubjectCategoryById(subject.getSubjectCategoryId());
            if (StringUtils.isNotNull(subCate)) {
                subCate.setSubjectCount(subCate.getSubjectCount() - 1);
                subjectCategoryMapper.updateSubjectCategory(subCate);
            }
        }
        return subjectMapper.deleteSubjectByIds(ids);
    }

    /**
     * 删除专题信息信息
     *
     * @param id 专题信息主键
     * @return 结果
     */
    @Override
    public int deleteSubjectById(Long id) {
        return subjectMapper.deleteSubjectById(id);
    }
}
