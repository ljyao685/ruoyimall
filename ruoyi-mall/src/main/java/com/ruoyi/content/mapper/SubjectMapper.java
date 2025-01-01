package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.Subject;

import java.util.List;

/**
 * 专题信息Mapper接口
 *
 * @author lb
 * @date 2022-01-13
 */
public interface SubjectMapper {
    /**
     * 查询专题信息
     *
     * @param id 专题信息主键
     * @return 专题信息
     */
    public Subject selectSubjectById(Long id);

    /**
     * 查询专题信息列表
     *
     * @param subject 专题信息
     * @return 专题信息集合
     */
    public List<Subject> selectSubjectList(Subject subject);

    /**
     * 新增专题信息
     *
     * @param subject 专题信息
     * @return 结果
     */
    public int insertSubject(Subject subject);

    /**
     * 修改专题信息
     *
     * @param subject 专题信息
     * @return 结果
     */
    public int updateSubject(Subject subject);

    /**
     * 修改专题分类ID对应的专题分类名称
     *
     * @param subject 专题信息
     * @return 结果
     */
    public int updateSubjectByXXId(Subject subject);

    /**
     * 更新专题对应的商品数量
     *
     * @param ids 专题ID集合
     * @return 结果
     */
    public int updateSubjectProductCountByIds(Long[] ids);

    /**
     * 删除专题信息
     *
     * @param id 专题信息主键
     * @return 结果
     */
    public int deleteSubjectById(Long id);

    /**
     * 批量删除专题信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSubjectByIds(Long[] ids);
}
