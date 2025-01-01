package com.ruoyi.member.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.member.domain.Member;
import com.ruoyi.member.mapper.MemberMapper;
import com.ruoyi.member.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员信息Service业务层处理
 *
 * @author lb
 * @date 2022-01-27
 */
@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 查询会员信息
     *
     * @param id 会员信息主键
     * @return 会员信息
     */
    @Override
    public Member selectMemberById(Long id) {
        return memberMapper.selectMemberById(id);
    }

    /**
     * 查询会员信息列表
     *
     * @param member 会员信息
     * @return 会员信息
     */
    @Override
    public List<Member> selectMemberList(Member member) {
        return memberMapper.selectMemberList(member);
    }

    /**
     * 新增会员信息
     *
     * @param member 会员信息
     * @return 结果
     */
    @Override
    public int insertMember(Member member) {
        member.setTenantId(SecurityUtils.getUserId());
        member.setCreateBy(SecurityUtils.getUsername());
        member.setCreateTime(DateUtils.getNowDate());
        return memberMapper.insertMember(member);
    }

    /**
     * 修改会员信息
     *
     * @param member 会员信息
     * @return 结果
     */
    @Override
    public int updateMember(Member member) {
        member.setUpdateBy(SecurityUtils.getUsername());
        member.setUpdateTime(DateUtils.getNowDate());
        return memberMapper.updateMember(member);
    }

    /**
     * 批量删除会员信息
     *
     * @param ids 需要删除的会员信息主键
     * @return 结果
     */
    @Override
    public int deleteMemberByIds(Long[] ids) {
        return memberMapper.deleteMemberByIds(ids);
    }

    /**
     * 删除会员信息信息
     *
     * @param id 会员信息主键
     * @return 结果
     */
    @Override
    public int deleteMemberById(Long id) {
        return memberMapper.deleteMemberById(id);
    }
}
