<template>
  <div style="margin-top: 50px">
    <el-form :model="product" ref="productSaleForm" label-width="120px" style="width: 600px">
      <el-form-item label="赠送积分：">
        <el-input v-model="product.giftPoint"></el-input>
      </el-form-item>
      <el-form-item label="赠送成长值：">
        <el-input v-model="product.giftGrowth"></el-input>
      </el-form-item>
      <el-form-item label="积分购买限制：">
        <el-input v-model="product.usePointLimit"></el-input>
      </el-form-item>
      <el-form-item label="预告商品：">
        <el-switch
          v-model="product.previewStatus"
          :active-value="1"
          :inactive-value="0">
        </el-switch>
      </el-form-item>
      <el-form-item label="商品上架：">
        <el-switch
          v-model="product.publishStatus"
          :active-value="1"
          :inactive-value="0">
        </el-switch>
      </el-form-item>
      <el-form-item label="商品推荐：">
        <span style="margin-right: 10px">新品</span>
        <el-switch
          v-model="product.newStatus"
          :active-value="1"
          :inactive-value="0">
        </el-switch>
        <span style="margin-left: 50px;margin-right: 10px">推荐</span>
        <el-switch
          v-model="product.recommendStatus"
          :active-value="1"
          :inactive-value="0">
        </el-switch>
      </el-form-item>
      <el-form-item label="服务保证：">
        <el-checkbox-group v-model="selectServiceList">
          <el-checkbox :label="1">无忧退货</el-checkbox>
          <el-checkbox :label="2">快速退款</el-checkbox>
          <el-checkbox :label="3">免费包邮</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="详细页标题：">
        <el-input v-model="product.detailTitle"></el-input>
      </el-form-item>
      <el-form-item label="详细页描述：">
        <el-input v-model="product.detailDesc"></el-input>
      </el-form-item>
      <el-form-item label="商品关键字：">
        <el-input v-model="product.keywords"></el-input>
      </el-form-item>
      <el-form-item label="商品备注：">
        <el-input v-model="product.remark" type="textarea" :autoSize="true"></el-input>
      </el-form-item>
      <el-form-item label="选择优惠方式：">
        <el-radio-group v-model="product.promotionType" size="small">
          <el-radio-button :label="0">无优惠</el-radio-button>
          <el-radio-button :label="1">特惠促销</el-radio-button>
          <el-radio-button :label="2">会员价格</el-radio-button>
          <el-radio-button :label="3">阶梯价格</el-radio-button>
          <el-radio-button :label="4">满减价格</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item v-show="product.promotionType===1">
        <div>
          开始时间：
          <el-date-picker
            v-model="product.promotionStartTime"
            type="datetime"
            :picker-options="pickerOptions1"
            placeholder="选择开始时间">
          </el-date-picker>
        </div>
        <div class="littleMargin">
          结束时间：
          <el-date-picker
            v-model="product.promotionEndTime"
            type="datetime"
            :picker-options="pickerOptions1"
            placeholder="选择结束时间">
          </el-date-picker>
        </div>
        <div class="littleMargin">
          促销价格：
          <el-input style="width: 220px" v-model="product.promotionPrice" placeholder="输入促销价格"></el-input>
        </div>

      </el-form-item>
      <el-form-item v-show="product.promotionType===2">
        <div v-for="(item, index) in product.memberPriceList" :class="{littleMargin:index!==0}">
          {{ item.memberLevelName }}：
          <el-input v-model="item.memberPrice" style="width: 200px"></el-input>
        </div>
      </el-form-item>
      <el-form-item v-show="product.promotionType===3">
        <el-table :data="product.productLadderList"
                  style="width: 80%" size="small" border>
          <el-table-column
            label="数量"
            align="center"
            width="120">
            <template slot-scope="scope">
              <el-input size="mini" v-model="scope.row.count"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            label="折扣"
            align="center"
            width="120">
            <template slot="header">
              <span>折扣</span><br>
              <span style="color: #f56c6c;font-size: 11px;">*输入0-1的数值</span>
            </template>
            <template slot-scope="scope">
              <el-input size="mini" v-model="scope.row.discount" @change="handleChangeDiscount(scope.row)"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            label="操作">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="handleAddProductLadder(scope.$index, scope.row)">添加</el-button>
              <el-button style="color:#f56c6c;" size="mini" type="text"
                         @click="handleRemoveProductLadder(scope.$index, scope.row)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form-item>
      <el-form-item v-show="product.promotionType===4">
        <el-table :data="product.productFullReductionList"
                  style="width: 80%" size="small" border>
          <el-table-column
            label="满"
            align="center"
            width="120">
            <template slot-scope="scope">
              <el-input size="mini" v-model="scope.row.fullPrice"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            label="立减"
            align="center"
            width="120">
            <template slot-scope="scope">
              <el-input size="mini" v-model="scope.row.reducePrice"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            label="操作">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="handleAddFullReduction(scope.$index, scope.row)">添加</el-button>
              <el-button style="color:#f56c6c;" size="mini" type="text"
                         @click="handleRemoveFullReduction(scope.$index, scope.row)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form-item>
      <el-form-item style="text-align: center">
        <el-button size="medium" @click="handlePrev">上一步，填写商品信息</el-button>
        <el-button type="primary" size="medium" @click="handleNext">下一步，填写商品属性</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {listMemberLevel} from '@/api/mall/memberLevel'

export default {
  name: "ProductSaleItem",
  props: {
    value: Object,
  },
  data() {
    return {
      //商品
      product: {},
      //日期选择器配置
      pickerOptions1: {
        disabledDate(time) {
          return time.getTime() < Date.now();
        }
      }
    }
  },
  created() {
    this.product = this.value;
    if (!this.isEdit || !this.product.memberPriceList || this.product.memberPriceList.length == 0) {
      this.getMemberLevelList();
    }
  },
  computed: {
    //是否编辑
    isEdit() {
      if (this.product.id) {
        return true;
      } else {
        return false;
      }
    },
    //选中的服务保证
    selectServiceList: {
      get() {
        let list = [];
        if (this.product.serviceIds === undefined || this.product.serviceIds == null || this.product.serviceIds === '') return list;
        let ids = this.product.serviceIds.split(',');
        for (let i = 0; i < ids.length; i++) {
          list.push(Number(ids[i]));
        }
        return list;
      },
      set(newValue) {
        let serviceIds = '';
        if (newValue != null && newValue.length > 0) {
          for (let i = 0; i < newValue.length; i++) {
            serviceIds += newValue[i] + ',';
          }
          if (serviceIds.endsWith(',')) {
            serviceIds = serviceIds.substr(0, serviceIds.length - 1)
          }
          this.product.serviceIds = serviceIds;
        } else {
          this.product.serviceIds = null;
        }
      }
    }
  },
  watch: {
    //阶梯价格
    'product.productLadderList': function (newValue) {
      if (!newValue || newValue.length == 0) {
        this.product.productLadderList = [{count: 0, discount: 0, price: 0}];
      }
    },
    //满减价格
    'product.productFullReductionList': function (newValue) {
      if (!newValue || newValue.length == 0) {
        this.product.productFullReductionList = [{fullPrice: 0, reducePrice: 0}];
      }
    },
  },
  methods: {
    //查询会员等级列表
    getMemberLevelList() {
      listMemberLevel({defaultStatus: 0}).then(response => {
        let memberPriceList = [];
        let dataList = response.rows;
        for (let i = 0; i < dataList.length; i++) {
          let item = dataList[i];
          memberPriceList.push({memberLevelId: item.id, memberLevelName: item.name, memberPrice: 0})
        }
        this.product.memberPriceList = memberPriceList;
      });
    },
    //移除阶梯价格列表元素
    handleRemoveProductLadder(index, row) {
      let productLadderList = this.product.productLadderList;
      if (productLadderList.length === 1) {
        productLadderList.pop();
        productLadderList.push({
          count: 0,
          discount: 0,
          price: 0
        })
      } else {
        productLadderList.splice(index, 1);
      }
    },
    //添加阶梯价格列表元素
    handleAddProductLadder(index, row) {
      let productLadderList = this.product.productLadderList;
      if (productLadderList.length < 3) {
        productLadderList.push({
          count: 0,
          discount: 0,
          price: 0
        })
      } else {
        this.$message({
          message: '最多只能添加三条',
          type: 'warning'
        });
      }
    },
    //移除满减价格列表元素
    handleRemoveFullReduction(index, row) {
      let fullReductionList = this.product.productFullReductionList;
      if (fullReductionList.length === 1) {
        fullReductionList.pop();
        fullReductionList.push({
          fullPrice: 0,
          reducePrice: 0
        });
      } else {
        fullReductionList.splice(index, 1);
      }
    },
    //添加满减价格列表元素
    handleAddFullReduction(index, row) {
      let fullReductionList = this.product.productFullReductionList;
      if (fullReductionList.length < 3) {
        fullReductionList.push({
          fullPrice: 0,
          reducePrice: 0
        });
      } else {
        this.$message({
          message: '最多只能添加三条',
          type: 'warning'
        });
      }
    },
    //根据折扣计算折后价格
    handleChangeDiscount(row) {
      row.price = this.product.price * row.discount;
    },
    handlePrev() {
      this.$emit('prevStep')
    },
    handleNext() {
      this.$emit('nextStep')
    }
  }
}
</script>

<style scoped>
.littleMargin {
  margin-top: 10px;
}
</style>
