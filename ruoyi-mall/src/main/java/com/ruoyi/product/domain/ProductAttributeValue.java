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
 * @Date: 2021/8/25 11:05
 * @描述：商品包含的属性对应的值 <p>
 * 商品属性值信息表
 */
@Data
@Entity
@Table(name = "p_product_attribute_value")
@org.hibernate.annotations.Table(appliesTo = "p_product_attribute_value", comment = "商品属性值信息表")
@ApiModel(value = "ProductAttributeValue", description = "商品属性值信息")
public class ProductAttributeValue extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    @Column(columnDefinition = "bigint(20) comment '商品ID'")
    private Long productId;

    @ApiModelProperty(value = "商品属性ID")
    @Column(columnDefinition = "bigint(20) comment '商品属性ID'")
    private Long productAttributeId;

    @ApiModelProperty(value = "手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开")
    @Column(columnDefinition = "varchar(255) comment '商品包含属性值'")
    private String value;

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
                .append("productId", getProductId())
                .append("value", getValue())
                .toString();
    }
}
