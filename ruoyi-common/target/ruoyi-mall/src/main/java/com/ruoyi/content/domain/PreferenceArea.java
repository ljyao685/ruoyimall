package com.ruoyi.content.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntityByMySql;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: lb
 * @Date: 2022/1/13 11:02
 * @desc：优选专区信息表
 */
@Data
@Entity
@Table(name = "c_preference_area")
@org.hibernate.annotations.Table(appliesTo = "c_preference_area", comment = "优选专区信息表")
@ApiModel(value = "PreferenceArea", description = "优选专区信息")
public class PreferenceArea extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @Excel(name = "优选专区名称")
    @ApiModelProperty(value = "优选专区名称")
    @Column(columnDefinition = "varchar(255) comment '优选专区名称'")
    private String name;

    @ApiModelProperty(value = "副标题")
    @Column(columnDefinition = "varchar(255) comment '副标题'")
    private String subTitle;

    @ApiModelProperty(value = "展示图片")
    @Column(columnDefinition = "varchar(255) comment '展示图片'")
    private String pic;

    @ApiModelProperty(value = "关联产品数量")
    @Column(columnDefinition = "int(10) comment '关联产品数量'")
    private Integer productCount;

    @ApiModelProperty(value = "排序")
    @Column(columnDefinition = "int(10) comment '排序'")
    private Integer sort;

    @ApiModelProperty(value = "显示状态：0->不显示；1->显示")
    @Column(columnDefinition = "int(2) comment '显示状态：0->不显示；1->显示'")
    private Integer showStatus;

}
