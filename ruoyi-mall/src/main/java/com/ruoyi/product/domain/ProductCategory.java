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
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lb
 * @Date: 2021/8/25 10:07
 * @描述：商品所属分类 <p>
 * 商品分类信息表
 */
@Data
@Entity
@Table(name = "p_product_category")
@org.hibernate.annotations.Table(appliesTo = "p_product_category", comment = "商品分类信息表")
@ApiModel(value = "ProductCategory", description = "商品分类信息")
public class ProductCategory extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父级编号：0表示一级分类")
    @Column(columnDefinition = "bigint(20) comment '父级编号：0表示一级分类'")
    private Long parentId;

    @Excel(name = "分类名称")
    @ApiModelProperty(value = "分类名称")
    @Column(columnDefinition = "varchar(255) comment '分类名称'")
    private String name;

    @ApiModelProperty(value = "分类级别：0->1级；1->2级")
    @Column(columnDefinition = "int(2) comment '分类级别：0->1级；1->2级'")
    private Integer level;

    @ApiModelProperty(value = "商品数量")
    @Column(columnDefinition = "int(10) comment '商品数量'")
    private Integer productCount;

    @ApiModelProperty(value = "数量单位")
    @Column(columnDefinition = "varchar(10) comment '数量单位'")
    private String productUnit;

    @ApiModelProperty(value = "是否显示在导航栏：0->不显示；1->显示")
    @Column(columnDefinition = "int(2) comment '是否显示在导航栏：0->不显示；1->显示'")
    private Integer navStatus;

    @ApiModelProperty(value = "显示状态：0->不显示；1->显示")
    @Column(columnDefinition = "int(2) comment '显示状态：0->不显示；1->显示'")
    private Integer showStatus;

    @ApiModelProperty(value = "排序")
    @Column(columnDefinition = "int(10) comment '排序'")
    private Integer sort;

    @ApiModelProperty(value = "图标")
    @Column(columnDefinition = "varchar(100) comment '图标'")
    private String icon;

    @ApiModelProperty(value = "关键字")
    @Column(columnDefinition = "varchar(255) comment '关键字'")
    private String keywords;

    @ApiModelProperty(value = "描述")
    @Column(columnDefinition = "varchar(255) comment '描述'")
    private String description;

    @Transient
    private List<Long> productAttributeIdList;//相关筛选属性ID集合

    /**
     * 子类型
     */
    @Transient
    private List<ProductCategory> children = new ArrayList<ProductCategory>();

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
                .append("description", getDescription())
                .append("icon", getIcon())
                .append("keywords", getKeywords())
                .append("level", getLevel())
                .append("name", getName())
                .append("navStatus", getNavStatus())
                .append("parentId", getParentId())
                .append("productCount", getProductCount())
                .append("productUnit", getProductUnit())
                .append("showStatus", getShowStatus())
                .append("sort", getSort())
                .toString();
    }
}
