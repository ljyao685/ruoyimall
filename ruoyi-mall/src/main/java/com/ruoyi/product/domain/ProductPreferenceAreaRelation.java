package com.ruoyi.product.domain;

import com.ruoyi.common.core.domain.BaseEntityByMySql;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: lb
 * @Date: 2022/1/12 16:50
 * @desc：优选专区和商品的关系表
 */
@Data
@Entity
@Table(name = "p_product_preference_area_relation")
@org.hibernate.annotations.Table(appliesTo = "p_product_preference_area_relation", comment = "优选专区和商品的关系表")
@ApiModel(value = "ProductPreferenceAreaRelation", description = "优选专区和商品的关系表")
public class ProductPreferenceAreaRelation extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "优选专区ID")
    @Column(columnDefinition = "bigint(20) comment '优选专区ID'")
    private Long preferenceAreaId;

    @ApiModelProperty(value = "商品ID")
    @Column(columnDefinition = "bigint(20) comment '商品ID'")
    private Long productId;

    public ProductPreferenceAreaRelation() {
    }

    public ProductPreferenceAreaRelation(Long productId) {
        this.productId = productId;
    }
}
