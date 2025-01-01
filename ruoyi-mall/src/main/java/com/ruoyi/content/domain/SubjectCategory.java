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
 * @Date: 2022/1/13 10:53
 * @desc：专题分类信息表
 */
@Data
@Entity
@Table(name = "c_subject_category")
@org.hibernate.annotations.Table(appliesTo = "c_subject_category", comment = "专题分类信息表")
@ApiModel(value = "SubjectCategory", description = "专题分类信息")
public class SubjectCategory extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @Excel(name = "专题分类名称")
    @ApiModelProperty(value = "专题分类名称")
    @Column(columnDefinition = "varchar(255) comment '专题分类名称'")
    private String name;

    @ApiModelProperty(value = "专题分类图标")
    @Column(columnDefinition = "varchar(255) comment '专题分类图标'")
    private String icon;

    @ApiModelProperty(value = "专题数量")
    @Column(columnDefinition = "int(10) comment '专题数量'")
    private Integer subjectCount;

    @ApiModelProperty(value = "显示状态：0->不显示；1->显示")
    @Column(columnDefinition = "int(2) comment '显示状态：0->不显示；1->显示'")
    private Integer showStatus;

    @ApiModelProperty(value = "排序")
    @Column(columnDefinition = "int(10) comment '排序'")
    private Integer sort;

}
