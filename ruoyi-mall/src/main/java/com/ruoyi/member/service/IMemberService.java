package com.ruoyi.member.service;

import com.ruoyi.member.domain.Member;

import java.util.List;

/**
 * 会员信息Service接口
 *
 * @author lb
 * @date 2022-01-27
 */
public interface IMemberService {
    /**
     * 查询会员信息
     *
     * @param id 会员信息主键
     * @return 会员信息
     */
    public Member selectMemberById(Long id);

    /**
     * 查询会员信息列表
     *
     * @param member 会员信息
     * @return 会员信息集合
     */
    public List<Member> selectMemberList(Member member);

    /**
     * 新增会员信息
     *
     * @param member 会员信息
     * @return 结果
     */
    public int insertMember(Member member);

    /**
     * 修改会员信息
     *
     * @param member 会员信息
     * @return 结果
     */
    public int updateMember(Member member);

    /**
     * 批量删除会员信息
     *
     * @param ids 需要删除的会员信息主键集合
     * @return 结果
     */
    public int deleteMemberByIds(Long[] ids);

    /**
     * 删除会员信息信息
     *
     * @param id 会员信息主键
     * @return 结果
     */
    public int deleteMemberById(Long id);
}
