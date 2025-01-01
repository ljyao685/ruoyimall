package com.ruoyi.product.domain;

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

/**
 * @Author: lb
 * @Date: 2021/8/25 9:40
 * @描述：商品所属品牌 <p>
 * 商品品牌信息表
 */
@Data
@Entity
@Table(name = "p_brand")
@org.hibernate.annotations.Table(appliesTo = "p_brand", comment = "商品品牌信息表")
@ApiModel(value = "Brand", description = "商品品牌信息")
public class Brand extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @Excel(name = "品牌名称")
    @ApiModelProperty(value = "品牌名称")
    @Column(columnDefinition = "varchar(255) comment '品牌名称'")
    private String name;

    @ApiModelProperty(value = "首字母")
    @Column(columnDefinition = "varchar(50) comment '品牌首字母'")
    private String firstLetter;

    @ApiModelProperty(value = "排序")
    @Column(columnDefinition = "int(10) comment '排序'")
    private Integer sort;

    @ApiModelProperty(value = "是否为品牌制造商：0->不是；1->是")
    @Column(columnDefinition = "int(2) comment '是否为品牌制造商：0->不是；1->是'")
    private Integer factoryStatus;

    @ApiModelProperty(value = "显示状态：0->不显示；1->显示")
    @Column(columnDefinition = "int(2) comment '显示状态：0->不显示；1->显示'")
    private Integer showStatus;

    @ApiModelProperty(value = "产品数量")
    @Column(columnDefinition = "int(10) comment '产品数量'")
    private Integer productCount;

    @ApiModelProperty(value = "产品评论数量")
    @Column(columnDefinition = "int(10) comment '产品评论数量'")
    private Integer productCommentCount;

    @ApiModelProperty(value = "品牌logo")
    @Column(columnDefinition = "varchar(255) comment '品牌logo'")
    private String logo;

    @ApiModelProperty(value = "专区大图")
    @Column(columnDefinition = "varchar(255) comment '专区大图'")
    private String bigPic;

    @ApiModelProperty(value = "品牌故事")
    @Column(columnDefinition = "varchar(255) comment '品牌故事'")
    private String brandStory;

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
                .append("bigPic", getBigPic())
                .append("brandStory", getBrandStory())
                .append("factoryStatus", getFactoryStatus())
                .append("firstLetter", getFirstLetter())
                .append("logo", getLogo())
                .append("name", getName())
                .append("productCommentCount", getProductCommentCount())
                .append("productCount", getProductCount())
                .append("showStatus", getShowStatus())
                .append("sort", getSort())
                .toString();
    }
}
