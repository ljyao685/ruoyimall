package com.ruoyi.product.domain;

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
 * @Date: 2021/8/25 10:42
 * @描述：商品所属分类和可选属性关联信息 <p>
 * 商品分类&属性关联信息表
 */
@Data
@Entity
@Table(name = "p_product_category_attribute_relation")
@org.hibernate.annotations.Table(appliesTo = "p_product_category_attribute_relation", comment = "商品分类属性关联信息表")
@ApiModel(value = "ProductCategoryAttributeRelation", description = "商品分类属性关联信息")
public class ProductCategoryAttributeRelation extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品分类ID")
    @Column(columnDefinition = "bigint(20) comment '商品分类ID'")
    private Long productCategoryId;

    @ApiModelProperty(value = "商品属性ID")
    @Column(columnDefinition = "bigint(20) comment '商品属性ID'")
    private Long productAttributeId;

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
                .append("productAttributeId", getProductAttributeId())
                .append("productCategoryId", getProductCategoryId())
                .toString();
    }
}
