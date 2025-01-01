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
import java.math.BigDecimal;

/**
 * @Author: lb
 * @Date: 2021/8/25 11:46
 * @描述：商品库存相关信息 <p>
 * 商品sku库存信息表
 */
@Data
@Entity
@Table(name = "p_product_sku_stock")
@org.hibernate.annotations.Table(appliesTo = "p_product_sku_stock", comment = "商品sku库存信息表")
@ApiModel(value = "ProductSkuStock", description = "商品sku库存信息")
public class ProductSkuStock extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    @Column(columnDefinition = "bigint(20) comment '商品ID'")
    private Long productId;

    @ApiModelProperty(value = "sku编码")
    @Column(columnDefinition = "varchar(200) comment 'sku编码'")
    private String skuCode;

    @ApiModelProperty(value = "价格")
    @Column(columnDefinition = "decimal(19,2) comment '价格'")
    private BigDecimal price;

    @ApiModelProperty(value = "库存")
    @Column(columnDefinition = "decimal(19,2) comment '库存'")
    private BigDecimal stock;

    @ApiModelProperty(value = "预警库存")
    @Column(columnDefinition = "decimal(19,2) comment '预警库存'")
    private BigDecimal lowStock;

    @ApiModelProperty(value = "展示图片")
    @Column(columnDefinition = "varchar(255) comment '展示图片'")
    private String pic;

    @ApiModelProperty(value = "销量")
    @Column(columnDefinition = "decimal(19,2) comment '销量'")
    private BigDecimal sale;

    @ApiModelProperty(value = "单品促销价格")
    @Column(columnDefinition = "decimal(19,2) comment '单品促销价格'")
    private BigDecimal promotionPrice;

    @ApiModelProperty(value = "锁定库存")
    @Column(columnDefinition = "decimal(19,2) comment '锁定库存'")
    private BigDecimal lockStock;

    @ApiModelProperty(value = "商品销售属性，json格式")
    @Column(columnDefinition = "varchar(255) comment '商品销售属性'")
    private String spData;

    public ProductSkuStock() {
    }

    public ProductSkuStock(Long productId) {
        this.productId = productId;
    }

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
                .append("lockStock", getLockStock())
                .append("lowStock", getLowStock())
                .append("pic", getPic())
                .append("price", getPrice())
                .append("productId", getProductId())
                .append("promotionPrice", getPromotionPrice())
                .append("sale", getSale())
                .append("skuCode", getSkuCode())
                .append("spData", getSpData())
                .append("stock", getStock())
                .toString();
    }
}
