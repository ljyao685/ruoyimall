package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.SubjectCategory;

import java.util.List;

/**
 * 专题分类信息Mapper接口
 *
 * @author lb
 * @date 2022-01-13
 */
public interface SubjectCategoryMapper {
    /**
     * 查询专题分类信息
     *
     * @param id 专题分类信息主键
     * @return 专题分类信息
     */
    public SubjectCategory selectSubjectCategoryById(Long id);

    /**
     * 查询专题分类信息列表
     *
     * @param subjectCategory 专题分类信息
     * @return 专题分类信息集合
     */
    public List<SubjectCategory> selectSubjectCategoryList(SubjectCategory subjectCategory);

    /**
     * 新增专题分类信息
     *
     * @param subjectCategory 专题分类信息
     * @return 结果
     */
    public int insertSubjectCategory(SubjectCategory subjectCategory);

    /**
     * 修改专题分类信息
     *
     * @param subjectCategory 专题分类信息
     * @return 结果
     */
    public int updateSubjectCategory(SubjectCategory subjectCategory);

    /**
     * 删除专题分类信息
     *
     * @param id 专题分类信息主键
     * @return 结果
     */
    public int deleteSubjectCategoryById(Long id);

    /**
     * 批量删除专题分类信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSubjectCategoryByIds(Long[] ids);
}
