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
 * @Date: 2021/8/25 11:16
 * @描述：商品针对不同等级会员对应不同价格 <p>
 * 商品会员价格信息表
 */
@Data
@Entity
@Table(name = "p_product_member_price")
@org.hibernate.annotations.Table(appliesTo = "p_product_member_price", comment = "商品会员价格信息表")
@ApiModel(value = "ProductMemberPrice", description = "商品会员价格信息")
public class ProductMemberPrice extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    @Column(columnDefinition = "bigint(20) comment '商品ID'")
    private Long productId;

    @ApiModelProperty(value = "会员等级ID")
    @Column(columnDefinition = "bigint(20) comment '会员等级ID'")
    private Long memberLevelId;

    @Excel(name = "会员等级名称")
    @ApiModelProperty(value = "会员等级名称")
    @Column(columnDefinition = "varchar(255) comment '会员等级名称'")
    private String memberLevelName;

    @Excel(name = "会员价格")
    @ApiModelProperty(value = "会员价格")
    @Column(columnDefinition = "decimal(19,2) comment '会员价格'")
    private BigDecimal memberPrice;

}
