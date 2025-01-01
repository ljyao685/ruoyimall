package com.ruoyi.content.service;

import com.ruoyi.content.domain.PreferenceArea;

import java.util.List;

/**
 * 优选专区信息Service接口
 *
 * @author lb
 * @date 2022-01-14
 */
public interface IPreferenceAreaService {
    /**
     * 查询优选专区信息
     *
     * @param id 优选专区信息主键
     * @return 优选专区信息
     */
    public PreferenceArea selectPreferenceAreaById(Long id);

    /**
     * 查询优选专区信息列表
     *
     * @param preferenceArea 优选专区信息
     * @return 优选专区信息集合
     */
    public List<PreferenceArea> selectPreferenceAreaList(PreferenceArea preferenceArea);

    /**
     * 新增优选专区信息
     *
     * @param preferenceArea 优选专区信息
     * @return 结果
     */
    public int insertPreferenceArea(PreferenceArea preferenceArea);

    /**
     * 修改优选专区信息
     *
     * @param preferenceArea 优选专区信息
     * @return 结果
     */
    public int updatePreferenceArea(PreferenceArea preferenceArea);

    /**
     * 批量删除优选专区信息
     *
     * @param ids 需要删除的优选专区信息主键集合
     * @return 结果
     */
    public int deletePreferenceAreaByIds(Long[] ids);

    /**
     * 删除优选专区信息信息
     *
     * @param id 优选专区信息主键
     * @return 结果
     */
    public int deletePreferenceAreaById(Long id);
}
