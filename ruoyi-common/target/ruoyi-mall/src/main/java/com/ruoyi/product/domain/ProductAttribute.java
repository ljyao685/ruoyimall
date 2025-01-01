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
 * @Date: 2021/8/25 10:22
 * @描述：商品属性可选信息 <p>
 * 商品属性信息表
 */
@Data
@Entity
@Table(name = "p_product_attribute")
@org.hibernate.annotations.Table(appliesTo = "p_product_attribute", comment = "商品属性信息表")
@ApiModel(value = "ProductAttribute", description = "商品属性信息")
public class ProductAttribute extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品属性分类ID")
    @Column(columnDefinition = "bigint(20) comment '商品属性分类ID'")
    private Long productAttributeCategoryId;//归属于哪个属性分类中

    @Excel(name = "商品属性名称")
    @ApiModelProperty(value = "商品属性名称")
    @Column(columnDefinition = "varchar(255) comment '商品属性名称'")
    private String name;

    @ApiModelProperty(value = "属性选择类型：0->唯一；1->单选；2->多选")
    @Column(columnDefinition = "int(2) comment '属性选择类型：0->唯一；1->单选；2->多选'")
    private Integer selectType;

    @ApiModelProperty(value = "属性录入方式：0->手工录入；1->从列表中选取")
    @Column(columnDefinition = "int(2) comment '属性录入方式：0->手工录入；1->从列表中选取'")
    private Integer inputType;

    @ApiModelProperty(value = "可选值列表，以逗号隔开")
    @Column(columnDefinition = "varchar(255) comment '可选值列表，以逗号隔开'")
    private String inputList;

    @ApiModelProperty(value = "排序字段：最高的可以单独上传图片")
    @Column(columnDefinition = "int(10) comment '排序'")
    private Integer sort;

    @ApiModelProperty(value = "分类筛选样式：1->普通；1->颜色")
    @Column(columnDefinition = "int(2) comment '分类筛选样式：1->普通；1->颜色'")
    private Integer filterType;

    @ApiModelProperty(value = "检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    @Column(columnDefinition = "int(2) comment '检索类型；0->不需要进行检索；1->关键字检索；2->范围检索'")
    private Integer searchType;

    @ApiModelProperty(value = "相同属性产品是否关联；0->不关联；1->关联")
    @Column(columnDefinition = "int(2) comment '相同属性产品是否关联；0->不关联；1->关联'")
    private Integer relatedStatus;

    @ApiModelProperty(value = "是否支持手动新增；0->不支持；1->支持")
    @Column(columnDefinition = "int(2) comment '是否支持手动新增；0->不支持；1->支持'")
    private Integer handAddStatus;

    @ApiModelProperty(value = "属性的类型；0->规格；1->参数")
    @Column(columnDefinition = "int(2) comment '属性的类型；0->规格；1->参数'")
    private Integer type;

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
                .append("filterType", getFilterType())
                .append("handAddStatus", getHandAddStatus())
                .append("inputList", getInputList())
                .append("inputType", getInputType())
                .append("name", getName())
                .append("productAttributeCategoryId", getProductAttributeCategoryId())
                .append("relatedStatus", getRelatedStatus())
                .append("searchType", getSearchType())
                .append("selectType", getSelectType())
                .append("sort", getSort())
                .append("type", getType())
                .toString();
    }
}
