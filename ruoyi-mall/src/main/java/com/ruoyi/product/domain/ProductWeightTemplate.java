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
 * @Date: 2021/8/25 11:35
 * @描述：商品重量或件数对应所需的邮寄费用 <p>
 * 商品重量计费模版信息表
 */
@Data
@Entity
@Table(name = "p_product_weight_template")
@org.hibernate.annotations.Table(appliesTo = "p_product_weight_template", comment = "商品重量计费模版信息表")
@ApiModel(value = "ProductWeightTemplate", description = "商品重量计费模版信息")
public class ProductWeightTemplate extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @Excel(name = "模版名称")
    @ApiModelProperty(value = "模版名称")
    @Column(columnDefinition = "varchar(255) comment '模版名称'")
    private String name;

    @ApiModelProperty(value = "计费类型:0->按重量；1->按件数")
    @Column(columnDefinition = "int(2) comment '计费类型:0->按重量；1->按件数'")
    private Integer chargeType;

    @ApiModelProperty(value = "首重/kg")
    @Column(columnDefinition = "decimal(19,2) comment '首重/kg'")
    private BigDecimal firstWeight;

    @ApiModelProperty(value = "首费/元")
    @Column(columnDefinition = "decimal(19,2) comment '首费/元'")
    private BigDecimal firstFee;

    @ApiModelProperty(value = "剩余重量")
    @Column(columnDefinition = "decimal(19,2) comment '剩余重量'")
    private BigDecimal continueWeight;//除首重外还有多重

    @ApiModelProperty(value = "剩余费用")
    @Column(columnDefinition = "decimal(19,2) comment '剩余费用'")
    private BigDecimal continueFee;//首重外的剩余重量所需费用

    @ApiModelProperty(value = "目的地（省、市）")
    @Column(columnDefinition = "varchar(255) comment '目的地（省、市）'")
    private String dest;

}
