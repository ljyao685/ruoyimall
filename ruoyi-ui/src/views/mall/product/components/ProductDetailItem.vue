<template>
  <div class="app">
    <div class="product_label">
      <el-divider content-position="left"><i class="el-icon-s-flag"></i>&nbsp;基础信息</el-divider>
    </div>
    <div class="product_table">
      <el-row>
        <el-col :span="3" class="product_table_title">编号</el-col>
        <el-col :span="6" class="product_table_title">商品名称</el-col>
        <el-col :span="4" class="product_table_title">商品货号</el-col>
        <el-col :span="4" class="product_table_title">品牌</el-col>
        <el-col :span="4" class="product_table_title">商品分类</el-col>
        <el-col :span="3" class="product_table_title">价格/元</el-col>
      </el-row>
      <el-row>
        <el-col :span="3" class="product_table_cell">{{ product.id }}</el-col>
        <el-col :span="6" class="product_table_cell" :title="product.name">{{ product.name }}</el-col>
        <el-col :span="4" class="product_table_cell">{{ product.productSn }}</el-col>
        <el-col :span="4" class="product_table_cell">{{ product.brandName }}</el-col>
        <el-col :span="4" class="product_table_cell">{{ product.productCategoryName }}</el-col>
        <el-col :span="3" class="product_table_cell">￥{{ product.price }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="3" class="product_table_title">赠送成长值</el-col>
        <el-col :span="6" class="product_table_title">赠送积分</el-col>
        <el-col :span="4" class="product_table_title">库存</el-col>
        <el-col :span="4" class="product_table_title">单位</el-col>
        <el-col :span="4" class="product_table_title">审核状态</el-col>
        <el-col :span="3" class="product_table_title">上架状态</el-col>
      </el-row>
      <el-row>
        <el-col :span="3" class="product_table_cell">{{ product.giftGrowth }}</el-col>
        <el-col :span="6" class="product_table_cell">{{ product.giftPoint }}</el-col>
        <el-col :span="4" class="product_table_cell">{{ product.stock }}</el-col>
        <el-col :span="4" class="product_table_cell">{{ product.unit }}</el-col>
        <el-col :span="4" class="product_table_cell">
          <span style="color: #67C23A" v-if="product.verifyStatus===1">审核通过</span>
          <span style="color: #F56C6C" v-if="product.verifyStatus===2">审核不通过</span>
          <span style="color: #E6A23C" v-if="product.verifyStatus===0">未审核</span>
        </el-col>
        <el-col :span="3" class="product_table_cell">
          <el-tag size="small" type="success" v-if="product.publishStatus===1">销售中</el-tag>
          <el-tag size="small" type="info" v-else>已下架</el-tag>
        </el-col>
      </el-row>
      <el-row>
        <el-col class="product_table_cell subTitle"><font style="font-weight: bold">副标题：</font>{{ product.subTitle }}
        </el-col>
      </el-row>
    </div>
    <div class="product_label" style="margin-top: 50px;">
      <el-divider content-position="left"><i class="el-icon-s-flag"></i>&nbsp;商品SKU库存信息</el-divider>
    </div>
    <div>
      <el-table :data="product.skuStockList" border>
        <el-table-column label="SKU编号" align="center" min-width="110" prop="skuCode"/>
        <el-table-column
          v-for="(item,index) in productAttrList"
          :label="item.key"
          :key="index"
          align="center">
          <template slot-scope="scope">
            {{ item.value }}
          </template>
        </el-table-column>
        <el-table-column label="销售价格/元" align="center" prop="price"/>
        <el-table-column label="商品库存" align="center" prop="stock"/>
        <el-table-column label="库存预警值" align="center" prop="lowStock"/>
      </el-table>
    </div>
    <div v-show="product.promotionType!==0" class="product_label" style="margin-top: 50px;">
      <el-divider content-position="left"><i class="el-icon-s-flag"></i>&nbsp;商品促销信息</el-divider>
    </div>
    <div class="product_table" v-show="product.promotionType===1">
      <el-row>
        <el-col :span="8" class="product_table_title">开始时间</el-col>
        <el-col :span="8" class="product_table_title">结束时间</el-col>
        <el-col :span="8" class="product_table_title">促销价格/元</el-col>
      </el-row>
      <el-row>
        <el-col :span="8" class="product_table_cell">{{ product.promotionStartTime }}</el-col>
        <el-col :span="8" class="product_table_cell">{{ product.promotionEndTime }}</el-col>
        <el-col :span="8" class="product_table_cell">{{ product.promotionPrice }}</el-col>
      </el-row>
    </div>
    <div v-show="product.promotionType===2">
      <el-table :data="product.memberPriceList" border>
        <el-table-column label="会员等级" align="center" prop="memberLevelName"/>
        <el-table-column label="会员价格/元" align="center" prop="memberPrice"/>
      </el-table>
    </div>
    <div v-show="product.promotionType===3">
      <el-table :data="product.productLadderList" border>
        <el-table-column label="购买数量" align="center" prop="count"/>
        <el-table-column label="折扣" align="center" prop="discount">
          <template slot-scope="scope">{{ scope.row.discount }}</template>
        </el-table-column>
        <el-table-column label="折后价格/元" align="center" prop="price"/>
      </el-table>
    </div>
    <div v-show="product.promotionType===4">
      <el-table :data="product.productFullReductionList" border>
        <el-table-column label="满" align="center" prop="fullPrice">
          <template slot-scope="scope">{{ scope.row.fullPrice }} 元</template>
        </el-table-column>
        <el-table-column label="立减" align="center" prop="reducePrice">
          <template slot-scope="scope">{{ scope.row.reducePrice }} 元</template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>

export default {
  name: "ProductDetailItem",
  props: {
    product: Object,
  },
  data() {
    return {
      //商品属性分类对应的属性值
      productAttrList: [],
    }
  },
  created() {
    this.getProdAttrList();
  },
  methods: {
    // 获取商品属性分类属性值信息
    getProdAttrList() {
      if (this.product.skuStockList.length > 0) {
        this.productAttrList = JSON.parse(this.product.skuStockList[0].spData);
      }
    },
  }
}
</script>
<style scoped>
.product_label >>> .el-divider__text {
  font-weight: bold;
}

.product_table {
  text-align: center;
  border-top: 1px solid #d9d9d9;
  border-left: 1px solid #d9d9d9;
}

.product_table_title {
  font-weight: bold;
  border-right: 1px solid #d9d9d9;
  border-bottom: 1px solid #d9d9d9;
  height: 40px;
  line-height: 40px;
  background: #f8f8f8;
}

.product_table_cell {
  border-right: 1px solid #d9d9d9;
  border-bottom: 1px solid #d9d9d9;
  height: 40px;
  line-height: 40px;
  /*文本超出部分显示省略号*/
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.subTitle {
  text-align: left;
  padding-left: 8px;
}
</style>
