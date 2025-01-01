package com.ruoyi.member.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntityByMySql;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author: lb
 * @Date: 2022/1/12 16:04
 * @desc：会员等级信息表
 */
@Data
@Entity
@Table(name = "m_member_level")
@org.hibernate.annotations.Table(appliesTo = "m_member_level", comment = "会员等级信息表")
@ApiModel(value = "MemberLevel", description = "会员等级信息")
public class MemberLevel extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @Excel(name = "会员等级名称")
    @ApiModelProperty(value = "会员等级名称")
    @Column(columnDefinition = "varchar(255) comment '会员等级名称'")
    private String name;

    @Excel(name = "成长值")
    @ApiModelProperty(value = "成长值")
    @Column(columnDefinition = "int(10) comment '成长值'")
    private Integer growthPoint;

    @ApiModelProperty(value = "是否为默认等级：0->不是；1->是")
    @Column(columnDefinition = "int(2) comment '是否为默认等级：0->不是；1->是'")
    private Integer defaultStatus;

    @ApiModelProperty(value = "免运费标准")
    @Column(columnDefinition = "decimal(19,2) comment '免运费标准'")
    private BigDecimal freeFreightPoint;

    @ApiModelProperty(value = "每次评价获取的成长值")
    @Column(columnDefinition = "int(2) comment '每次评价获取的成长值'")
    private Integer commentGrowthPoint;

    @ApiModelProperty(value = "是否有免邮特权：0->否；1->是")
    @Column(columnDefinition = "int(2) comment '是否有免邮特权：0->否；1->是'")
    private Integer priviledgeFreeFreight;

    @ApiModelProperty(value = "是否有签到特权：0->否；1->是")
    @Column(columnDefinition = "int(2) comment '是否有签到特权：0->否；1->是'")
    private Integer priviledgeSignIn;

    @ApiModelProperty(value = "是否有评论获奖励特权：0->否；1->是")
    @Column(columnDefinition = "int(2) comment '是否有评论获奖励特权：0->否；1->是'")
    private Integer priviledgeComment;

    @ApiModelProperty(value = "是否有专享活动特权：0->否；1->是")
    @Column(columnDefinition = "int(2) comment '是否有专享活动特权：0->否；1->是'")
    private Integer priviledgePromotion;

    @ApiModelProperty(value = "是否有会员价格特权：0->否；1->是")
    @Column(columnDefinition = "int(2) comment '是否有会员价格特权：0->否；1->是'")
    private Integer priviledgeMemberPrice;

    @ApiModelProperty(value = "是否有生日特权：0->否；1->是")
    @Column(columnDefinition = "int(2) comment '是否有生日特权：0->否；1->是'")
    private Integer priviledgeBirthday;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("remark", getRemark())
                .append("searchValue", getSearchValue())
                .append("tenantId", getTenantId())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("commentGrowthPoint", getCommentGrowthPoint())
                .append("defaultStatus", getDefaultStatus())
                .append("freeFreightPoint", getFreeFreightPoint())
                .append("growthPoint", getGrowthPoint())
                .append("name", getName())
                .append("priviledgeBirthday", getPriviledgeBirthday())
                .append("priviledgeComment", getPriviledgeComment())
                .append("priviledgeFreeFreight", getPriviledgeFreeFreight())
                .append("priviledgeMemberPrice", getPriviledgeMemberPrice())
                .append("priviledgePromotion", getPriviledgePromotion())
                .append("priviledgeSignIn", getPriviledgeSignIn())
                .toString();
    }
}
