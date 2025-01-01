package com.ruoyi.member.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.member.domain.MemberLevel;
import com.ruoyi.member.mapper.MemberLevelMapper;
import com.ruoyi.member.service.IMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员等级信息Service业务层处理
 *
 * @author lb
 * @date 2022-01-12
 */
@Service
public class MemberLevelServiceImpl implements IMemberLevelService {
    @Autowired
    private MemberLevelMapper memberLevelMapper;

    /**
     * 查询会员等级信息
     *
     * @param id 会员等级信息主键
     * @return 会员等级信息
     */
    @Override
    public MemberLevel selectMemberLevelById(Long id) {
        return memberLevelMapper.selectMemberLevelById(id);
    }

    /**
     * 查询会员等级信息列表
     *
     * @param memberLevel 会员等级信息
     * @return 会员等级信息
     */
    @Override
    public List<MemberLevel> selectMemberLevelList(MemberLevel memberLevel) {
        return memberLevelMapper.selectMemberLevelList(memberLevel);
    }

    /**
     * 新增会员等级信息
     *
     * @param memberLevel 会员等级信息
     * @return 结果
     */
    @Override
    public int insertMemberLevel(MemberLevel memberLevel) {
        memberLevel.setTenantId(SecurityUtils.getUserId());
        memberLevel.setCreateBy(SecurityUtils.getUsername());
        memberLevel.setCreateTime(DateUtils.getNowDate());
        return memberLevelMapper.insertMemberLevel(memberLevel);
    }

    /**
     * 修改会员等级信息
     *
     * @param memberLevel 会员等级信息
     * @return 结果
     */
    @Override
    public int updateMemberLevel(MemberLevel memberLevel) {
        memberLevel.setUpdateBy(SecurityUtils.getUsername());
        memberLevel.setUpdateTime(DateUtils.getNowDate());
        return memberLevelMapper.updateMemberLevel(memberLevel);
    }

    /**
     * 批量删除会员等级信息
     *
     * @param ids 需要删除的会员等级信息主键
     * @return 结果
     */
    @Override
    public int deleteMemberLevelByIds(Long[] ids) {
        return memberLevelMapper.deleteMemberLevelByIds(ids);
    }

    /**
     * 删除会员等级信息信息
     *
     * @param id 会员等级信息主键
     * @return 结果
     */
    @Override
    public int deleteMemberLevelById(Long id) {
        return memberLevelMapper.deleteMemberLevelById(id);
    }
}
