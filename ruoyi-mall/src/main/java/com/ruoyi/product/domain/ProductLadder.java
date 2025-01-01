package com.ruoyi.product.domain;

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
 * @Date: 2021/8/25 11:29
 * @描述：购买商品满多少数量可打折 <p>
 * 阶梯价格信息表
 */
@Data
@Entity
@Table(name = "p_product_ladder")
@org.hibernate.annotations.Table(appliesTo = "p_product_ladder", comment = "阶梯价格信息表")
@ApiModel(value = "ProductLadder", description = "阶梯价格信息")
public class ProductLadder extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    @Column(columnDefinition = "bigint(20) comment '商品ID'")
    private Long productId;

    @ApiModelProperty(value = "需满足的商品数量")
    @Column(columnDefinition = "int(10) comment '需满足的商品数量'")
    private Integer count;

    @ApiModelProperty(value = "折扣")
    @Column(columnDefinition = "decimal(19,2) comment '折扣'")
    private BigDecimal discount;

    @ApiModelProperty(value = "折后价格")
    @Column(columnDefinition = "decimal(19,2) comment '折后价格'")
    private BigDecimal price;

}
