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
import java.util.List;

/**
 * @Author: lb
 * @Date: 2021/8/25 10:19
 * @描述：商品属性所属分类 <p>
 * 商品属性分类信息表
 */
@Data
@Entity
@Table(name = "p_product_attribute_category")
@org.hibernate.annotations.Table(appliesTo = "p_product_attribute_category", comment = "商品属性分类信息表")
@ApiModel(value = "ProductAttributeCategory", description = "商品属性分类信息")
public class ProductAttributeCategory extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @Excel(name = "属性分类名称")
    @ApiModelProperty(value = "属性分类名称")
    @Column(columnDefinition = "varchar(255) comment '属性分类名称'")
    private String name;

    @ApiModelProperty(value = "属性数量")
    @Column(columnDefinition = "int(10) comment '属性数量'")
    private Integer attributeCount;

    @ApiModelProperty(value = "参数数量")
    @Column(columnDefinition = "int(10) comment '参数数量'")
    private Integer paramCount;

    @Transient
    private List<ProductAttribute> prodAttrList;//属性分类对应的属性列表

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
                .append("attributeCount", getAttributeCount())
                .append("name", getName())
                .append("paramCount", getParamCount())
                .toString();
    }
}
