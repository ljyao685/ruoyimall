package com.ruoyi.product.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntityByMySql;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author: lb
 * @Date: 2021/8/25 11:24
 * @描述： 购买商品满多少减多少的优惠价格 <p>
 * 满减价格信息表
 */
@Data
@Entity
@Table(name = "p_product_full_reduction")
@org.hibernate.annotations.Table(appliesTo = "p_product_full_reduction", comment = "满减价格信息表")
@ApiModel(value = "ProductFullReduction", description = "满减价格信息")
public class ProductFullReduction extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    @Column(columnDefinition = "bigint(20) comment '商品ID'")
    private Long productId;

    @Excel(name = "需满足的价格")
    @ApiModelProperty(value = "需满足的价格")
    @Column(columnDefinition = "decimal(19,2) comment '需满足的价格'")
    private BigDecimal fullPrice;

    @Excel(name = "减(优惠)价格")
    @ApiModelProperty(value = "减(优惠)价格")
    @Column(columnDefinition = "decimal(19,2) comment '减(优惠)价格'")
    private BigDecimal reducePrice;

}
