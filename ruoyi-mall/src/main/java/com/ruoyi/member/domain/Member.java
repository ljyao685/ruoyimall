package com.ruoyi.member.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntityByMySql;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: lb
 * @Date: 2022/1/27 13:32
 * @desc：会员信息表
 */
@Data
@Entity
@Table(name = "m_member")
@org.hibernate.annotations.Table(appliesTo = "m_member", comment = "会员信息表")
@ApiModel(value = "Member", description = "会员信息")
public class Member extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会员等级ID")
    @Column(columnDefinition = "bigint(20) comment '会员等级ID'")
    private Long memberLevelId;

    @Excel(name = "用户名")
    @ApiModelProperty(value = "用户名")
    @Column(columnDefinition = "varchar(255) comment '用户名'")
    private String username;

    @ApiModelProperty(value = "密码")
    @Column(columnDefinition = "varchar(255) comment '密码'")
    private String password;

    @Excel(name = "昵称")
    @ApiModelProperty(value = "昵称")
    @Column(columnDefinition = "varchar(255) comment '昵称'")
    private String nickname;

    @ApiModelProperty(value = "头像")
    @Column(columnDefinition = "varchar(255) comment '头像'")
    private String icon;

    @Excel(name = "手机号码")
    @ApiModelProperty(value = "手机号码")
    @Column(columnDefinition = "varchar(255) comment '手机号码'")
    private String phone;

    @ApiModelProperty(value = "帐号启用状态:0->禁用；1->启用")
    @Column(columnDefinition = "int(2) comment '帐号启用状态:0->禁用；1->启用'")
    private Integer status;

    @Excel(name = "注册时间")
    @ApiModelProperty(value = "注册时间")
    @Column(columnDefinition = "datetime(0) comment '注册时间'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;

    @ApiModelProperty(value = "性别：0->未知；1->男；2->女")
    @Column(columnDefinition = "int(2) comment '性别：0->未知；1->男；2->女'")
    private Integer gender;

    @ApiModelProperty(value = "生日")
    @Column(columnDefinition = "date comment '生日'")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @ApiModelProperty(value = "所在城市")
    @Column(columnDefinition = "varchar(255) comment '所在城市'")
    private String city;

    @ApiModelProperty(value = "个性签名")
    @Column(columnDefinition = "varchar(255) comment '个性签名'")
    private String personalizedSignature;

    @ApiModelProperty(value = "用户来源：0->微信公众号；1->小程序；2->APP注册；3->后台新增")
    @Column(columnDefinition = "int(2) comment '用户来源'")
    private Integer sourceType;

    @ApiModelProperty(value = "积分")
    @Column(columnDefinition = "int(10) comment '积分'")
    private Integer integration;

    @ApiModelProperty(value = "成长值")
    @Column(columnDefinition = "int(10) comment '成长值'")
    private Integer growth;

    @ApiModelProperty(value = "历史积分数量")
    @Column(columnDefinition = "int(10) comment '历史积分数量'")
    private Integer historyIntegration;

}
