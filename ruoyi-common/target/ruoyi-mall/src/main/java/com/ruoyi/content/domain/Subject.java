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
 * @Date: 2022/1/13 10:33
 * @desc：专题信息表
 */
@Data
@Entity
@Table(name = "c_subject")
@org.hibernate.annotations.Table(appliesTo = "c_subject", comment = "专题信息表")
@ApiModel(value = "Subject", description = "专题信息")
public class Subject extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @Excel(name = "专题名称")
    @ApiModelProperty(value = "专题名称")
    @Column(columnDefinition = "varchar(255) comment '专题名称'")
    private String name;

    @ApiModelProperty(value = "专题分类ID")
    @Column(columnDefinition = "bigint(20) comment '专题分类ID'")
    private Long subjectCategoryId;

    @Excel(name = "专题分类名称")
    @ApiModelProperty(value = "专题分类名称")
    @Column(columnDefinition = "varchar(255) comment '专题分类名称'")
    private String subjectCategoryName;

    @ApiModelProperty(value = "专题主图")
    @Column(columnDefinition = "varchar(255) comment '专题主图'")
    private String pic;

    @ApiModelProperty(value = "画册图片用逗号分割")
    @Column(columnDefinition = "varchar(1000) comment '画册图片用逗号分割'")
    private String albumPics;

    @ApiModelProperty(value = "关联产品数量")
    @Column(columnDefinition = "int(10) comment '关联产品数量'")
    private Integer productCount;

    @ApiModelProperty(value = "收藏数")
    @Column(columnDefinition = "int(10) comment '收藏数'")
    private Integer collectCount;

    @ApiModelProperty(value = "阅读数")
    @Column(columnDefinition = "int(10) comment '阅读数'")
    private Integer readCount;

    @ApiModelProperty(value = "评价数")
    @Column(columnDefinition = "int(10) comment '评价数'")
    private Integer commentCount;

    @ApiModelProperty(value = "转发数")
    @Column(columnDefinition = "int(10) comment '转发数'")
    private Integer forwardCount;

    @ApiModelProperty(value = "推荐状态：0->不推荐；1->推荐")
    @Column(columnDefinition = "int(2) comment '推荐状态：0->不推荐；1->推荐'")
    private Integer recommendStatus;

    @ApiModelProperty(value = "显示状态：0->不显示；1->显示")
    @Column(columnDefinition = "int(2) comment '显示状态：0->不显示；1->显示'")
    private Integer showStatus;

    @ApiModelProperty(value = "专题描述")
    @Column(columnDefinition = "varchar(255) comment '专题描述'")
    private String description;

}
