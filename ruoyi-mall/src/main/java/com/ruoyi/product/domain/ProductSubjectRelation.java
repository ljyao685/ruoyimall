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
 * @Date: 2022/1/12 16:49
 * @desc：专题和商品关系表
 */
@Data
@Entity
@Table(name = "p_product_subject_relation")
@org.hibernate.annotations.Table(appliesTo = "p_product_subject_relation", comment = "专题和商品关系表")
@ApiModel(value = "ProductSubjectRelation", description = "专题和商品关系表")
public class ProductSubjectRelation extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "专题ID")
    @Column(columnDefinition = "bigint(20) comment '专题ID'")
    private Long subjectId;

    @ApiModelProperty(value = "商品ID")
    @Column(columnDefinition = "bigint(20) comment '商品ID'")
    private Long productId;

    public ProductSubjectRelation() {
    }

    public ProductSubjectRelation(Long productId) {
        this.productId = productId;
    }
}
