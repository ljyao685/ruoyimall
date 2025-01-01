package com.ruoyi.member.service;

import com.ruoyi.member.domain.MemberLevel;

import java.util.List;

/**
 * 会员等级信息Service接口
 *
 * @author lb
 * @date 2022-01-12
 */
public interface IMemberLevelService {
    /**
     * 查询会员等级信息
     *
     * @param id 会员等级信息主键
     * @return 会员等级信息
     */
    public MemberLevel selectMemberLevelById(Long id);

    /**
     * 查询会员等级信息列表
     *
     * @param memberLevel 会员等级信息
     * @return 会员等级信息集合
     */
    public List<MemberLevel> selectMemberLevelList(MemberLevel memberLevel);

    /**
     * 新增会员等级信息
     *
     * @param memberLevel 会员等级信息
     * @return 结果
     */
    public int insertMemberLevel(MemberLevel memberLevel);

    /**
     * 修改会员等级信息
     *
     * @param memberLevel 会员等级信息
     * @return 结果
     */
    public int updateMemberLevel(MemberLevel memberLevel);

    /**
     * 批量删除会员等级信息
     *
     * @param ids 需要删除的会员等级信息主键集合
     * @return 结果
     */
    public int deleteMemberLevelByIds(Long[] ids);

    /**
     * 删除会员等级信息信息
     *
     * @param id 会员等级信息主键
     * @return 结果
     */
    public int deleteMemberLevelById(Long id);
}
