package com.ruoyi.content.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.content.domain.PreferenceArea;
import com.ruoyi.content.mapper.PreferenceAreaMapper;
import com.ruoyi.content.service.IPreferenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优选专区信息Service业务层处理
 *
 * @author lb
 * @date 2022-01-14
 */
@Service
public class PreferenceAreaServiceImpl implements IPreferenceAreaService {
    @Autowired
    private PreferenceAreaMapper preferenceAreaMapper;

    /**
     * 查询优选专区信息
     *
     * @param id 优选专区信息主键
     * @return 优选专区信息
     */
    @Override
    public PreferenceArea selectPreferenceAreaById(Long id) {
        return preferenceAreaMapper.selectPreferenceAreaById(id);
    }

    /**
     * 查询优选专区信息列表
     *
     * @param preferenceArea 优选专区信息
     * @return 优选专区信息
     */
    @Override
    public List<PreferenceArea> selectPreferenceAreaList(PreferenceArea preferenceArea) {
        return preferenceAreaMapper.selectPreferenceAreaList(preferenceArea);
    }

    /**
     * 新增优选专区信息
     *
     * @param preferenceArea 优选专区信息
     * @return 结果
     */
    @Override
    public int insertPreferenceArea(PreferenceArea preferenceArea) {
        preferenceArea.setTenantId(SecurityUtils.getUserId());
        preferenceArea.setCreateBy(SecurityUtils.getUsername());
        preferenceArea.setCreateTime(DateUtils.getNowDate());
        return preferenceAreaMapper.insertPreferenceArea(preferenceArea);
    }

    /**
     * 修改优选专区信息
     *
     * @param preferenceArea 优选专区信息
     * @return 结果
     */
    @Override
    public int updatePreferenceArea(PreferenceArea preferenceArea) {
        preferenceArea.setUpdateBy(SecurityUtils.getUsername());
        preferenceArea.setUpdateTime(DateUtils.getNowDate());
        return preferenceAreaMapper.updatePreferenceArea(preferenceArea);
    }

    /**
     * 批量删除优选专区信息
     *
     * @param ids 需要删除的优选专区信息主键
     * @return 结果
     */
    @Override
    public int deletePreferenceAreaByIds(Long[] ids) {
        return preferenceAreaMapper.deletePreferenceAreaByIds(ids);
    }

    /**
     * 删除优选专区信息信息
     *
     * @param id 优选专区信息主键
     * @return 结果
     */
    @Override
    public int deletePreferenceAreaById(Long id) {
        return preferenceAreaMapper.deletePreferenceAreaById(id);
    }
}
