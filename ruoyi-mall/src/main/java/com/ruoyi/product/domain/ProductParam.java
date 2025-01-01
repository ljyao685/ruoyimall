package com.ruoyi.product.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: lb
 * @Date: 2022/1/12 16:47
 * @desc：商品参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductParam extends Product {
    @ApiModelProperty("商品阶梯价格设置")
    private List<ProductLadder> productLadderList;
    @ApiModelProperty("商品满减价格设置")
    private List<ProductFullReduction> productFullReductionList;
    @ApiModelProperty("商品会员价格设置")
    private List<ProductMemberPrice> memberPriceList;
    @ApiModelProperty("商品的sku库存信息")
    private List<ProductSkuStock> skuStockList;
    @ApiModelProperty("商品参数及自定义规格属性")
    private List<ProductAttributeValue> productAttributeValueList;
    @ApiModelProperty("专题和商品关系")
    private List<ProductSubjectRelation> subjectProductRelationList;
    @ApiModelProperty("优选专区和商品的关系")
    private List<ProductPreferenceAreaRelation> preferenceAreaProductRelationList;
}
