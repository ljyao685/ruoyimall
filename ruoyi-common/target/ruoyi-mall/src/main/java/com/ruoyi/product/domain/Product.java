package com.ruoyi.product.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntityByMySql;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: lb
 * @Date: 2021/8/24 16:51
 * @描述：商品 <p>
 * 商品信息表
 */
@Data
@Entity
@Table(name = "p_product")
@org.hibernate.annotations.Table(appliesTo = "p_product", comment = "商品信息表")
@ApiModel(value = "Product", description = "商品信息")
public class Product extends BaseEntityByMySql {
    private static final long serialVersionUID = 1L;

    @Excel(name = "商品名称")
    @ApiModelProperty(value = "商品名称")
    @Column(columnDefinition = "varchar(255) comment '商品名称'")
    private String name;

    @ApiModelProperty(value = "品牌ID")
    @Column(columnDefinition = "bigint(20) comment '品牌ID'")
    private Long brandId;

    @Excel(name = "品牌名称")
    @ApiModelProperty(value = "品牌名称")
    @Column(columnDefinition = "varchar(255) comment '品牌名称'")
    private String brandName;

    @ApiModelProperty(value = "商品分类ID")
    @Column(columnDefinition = "bigint(20) comment '商品分类ID'")
    private Long productCategoryId;

    @Excel(name = "商品分类名称")
    @ApiModelProperty(value = "商品分类名称")
    @Column(columnDefinition = "varchar(255) comment '商品分类名称'")
    private String productCategoryName;

    @ApiModelProperty(value = "商品重量计费模版ID")
    @Column(columnDefinition = "bigint(20) comment '商品重量计费模版ID'")
    private Long weightTemplateId;

    @ApiModelProperty(value = "商品属性分类ID")
    @Column(columnDefinition = "bigint(20) comment '商品属性分类ID'")
    private Long productAttributeCategoryId;

    @ApiModelProperty(value = "展示图片")
    @Column(columnDefinition = "varchar(255) comment '展示图片'")
    private String picture;

    @Excel(name = "商品货号")
    @ApiModelProperty(value = "商品货号")
    @Column(columnDefinition = "varchar(255) comment '商品货号'")
    private String productSn;

    @ApiModelProperty(value = "删除状态：0->未删除；1->已删除")
    @Column(columnDefinition = "int(2) comment '删除状态：0->未删除；1->已删除'")
    private Integer deleteStatus;

    @ApiModelProperty(value = "上架状态：0->下架；1->上架")
    @Column(columnDefinition = "int(2) comment '上架状态：0->下架；1->上架'")
    private Integer publishStatus;

    @ApiModelProperty(value = "新品状态：0->不是新品；1->新品")
    @Column(columnDefinition = "int(2) comment '新品状态：0->不是新品；1->新品'")
    private Integer newStatus;

    @ApiModelProperty(value = "推荐状态：0->不推荐；1->推荐")
    @Column(columnDefinition = "int(2) comment '推荐状态：0->不推荐；1->推荐'")
    private Integer recommendStatus;

    @ApiModelProperty(value = "审核状态：0->未审核；1->审核通过；2->审核不通过")
    @Column(columnDefinition = "int(2) comment '审核状态：0->未审核；1->审核通过；2->审核不通过'")
    private Integer verifyStatus;

    @ApiModelProperty(value = "排序")
    @Column(columnDefinition = "int(10) comment '排序'")
    private Integer sort;

    @Excel(name = "销量")
    @ApiModelProperty(value = "销量")
    @Column(columnDefinition = "decimal(19,2) comment '销量'")
    private BigDecimal sale;

    @Excel(name = "销售价格")
    @ApiModelProperty(value = "销售价格")
    @Column(columnDefinition = "decimal(19,2) comment '销售价格'")
    private BigDecimal price;

    @ApiModelProperty(value = "促销价格")
    @Column(columnDefinition = "decimal(19,2) comment '促销价格'")
    private BigDecimal promotionPrice;

    @ApiModelProperty(value = "赠送的成长值")
    @Column(columnDefinition = "int(10) comment '赠送的成长值'")
    private Integer giftGrowth;

    @ApiModelProperty(value = "赠送的积分")
    @Column(columnDefinition = "int(10) comment '赠送的积分'")
    private Integer giftPoint;

    @ApiModelProperty(value = "限制使用的积分数")
    @Column(columnDefinition = "int(10) comment '限制使用的积分数'")
    private Integer usePointLimit;

    @ApiModelProperty(value = "副标题")
    @Column(columnDefinition = "varchar(255) comment '副标题'")
    private String subTitle;

    @ApiModelProperty(value = "市场价")
    @Column(columnDefinition = "decimal(19,2) comment '市场价'")
    private BigDecimal originalPrice;

    @Excel(name = "库存")
    @ApiModelProperty(value = "库存")
    @Column(columnDefinition = "decimal(19,2) comment '库存'")
    private BigDecimal stock;

    @ApiModelProperty(value = "库存预警值")
    @Column(columnDefinition = "decimal(19,2) comment '库存预警值'")
    private BigDecimal lowStock;

    @Excel(name = "单位")
    @ApiModelProperty(value = "单位")
    @Column(columnDefinition = "varchar(50) comment '单位'")
    private String unit;

    @ApiModelProperty(value = "商品重量，默认为克")
    @Column(columnDefinition = "decimal(19,2) comment '商品重量，默认为克'")
    private BigDecimal weight;

    @ApiModelProperty(value = "是否为预告商品：0->不是；1->是")
    @Column(columnDefinition = "int(2) comment '是否为预告商品：0->不是；1->是'")
    private Integer previewStatus;

    @ApiModelProperty(value = "以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮")
    @Column(columnDefinition = "varchar(100) comment '以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮'")
    private String serviceIds;//支持多个，以逗号分割

    @ApiModelProperty(value = "关键字")
    @Column(columnDefinition = "varchar(255) comment '关键字'")
    private String keywords;

    @ApiModelProperty(value = "画册图片，连产品图片限制为5张，以逗号分割")
    @Column(columnDefinition = "varchar(1000) comment '画册图片，连产品图片限制为5张，以逗号分割'")
    private String albumPics;

    @ApiModelProperty(value = "详细标题")
    @Column(columnDefinition = "varchar(255) comment '详细标题'")
    private String detailTitle;

    @ApiModelProperty(value = "促销开始时间")
    @Column(columnDefinition = "datetime(0) comment '促销开始时间'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date promotionStartTime;

    @ApiModelProperty(value = "促销结束时间")
    @Column(columnDefinition = "datetime(0) comment '促销结束时间'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date promotionEndTime;

    @ApiModelProperty(value = "活动限购数量")
    @Column(columnDefinition = "int(10) comment '活动限购数量'")
    private Integer promotionPerLimit;

    @ApiModelProperty(value = "促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购")
    @Column(columnDefinition = "int(2) comment '促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购'")
    private Integer promotionType;

    @ApiModelProperty(value = "商品描述")
    @Column(columnDefinition = "text(0) comment '商品描述'")
    private String description;

    @ApiModelProperty(value = "详细描述")
    @Column(columnDefinition = "text(0) comment '详细描述'")
    private String detailDesc;

    @ApiModelProperty(value = "产品详情网页内容")
    @Column(columnDefinition = "text(0) comment '产品详情网页内容'")
    private String detailHtml;

    @ApiModelProperty(value = "移动端网页详情")
    @Column(columnDefinition = "text(0) comment '移动端网页详情'")
    private String detailMobileHtml;

    @Transient
    private Long cateParentId;//商品所选分类的父id

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
                .append("albumPics", getAlbumPics())
                .append("brandId", getBrandId())
                .append("brandName", getBrandName())
                .append("deleteStatus", getDeleteStatus())
                .append("description", getDescription())
                .append("detailDesc", getDetailDesc())
                .append("detailHtml", getDetailHtml())
                .append("detailMobileHtml", getDetailMobileHtml())
                .append("detailTitle", getDetailTitle())
                .append("giftGrowth", getGiftGrowth())
                .append("giftPoint", getGiftPoint())
                .append("keywords", getKeywords())
                .append("lowStock", getLowStock())
                .append("name", getName())
                .append("newStatus", getNewStatus())
                .append("originalPrice", getOriginalPrice())
                .append("picture", getPicture())
                .append("previewStatus", getPreviewStatus())
                .append("price", getPrice())
                .append("productAttributeCategoryId", getProductAttributeCategoryId())
                .append("productCategoryId", getProductCategoryId())
                .append("productCategoryName", getProductCategoryName())
                .append("productSn", getProductSn())
                .append("promotionEndTime", getPromotionEndTime())
                .append("promotionPerLimit", getPromotionPerLimit())
                .append("promotionPrice", getPromotionPrice())
                .append("promotionStartTime", getPromotionStartTime())
                .append("promotionType", getPromotionType())
                .append("publishStatus", getPublishStatus())
                .append("recommendStatus", getRecommendStatus())
                .append("sale", getSale())
                .append("serviceIds", getServiceIds())
                .append("sort", getSort())
                .append("stock", getStock())
                .append("subTitle", getSubTitle())
                .append("unit", getUnit())
                .append("usePointLimit", getUsePointLimit())
                .append("verifyStatus", getVerifyStatus())
                .append("weight", getWeight())
                .append("weightTemplateId", getWeightTemplateId())
                .toString();
    }

}
