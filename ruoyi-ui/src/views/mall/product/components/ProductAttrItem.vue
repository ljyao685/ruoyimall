<template>
  <div style="margin-top: 50px">
    <el-form :model="product" ref="productAttrForm" label-width="120px" style="width: 720px" size="small">
      <el-form-item label="属性类型：">
        <el-select v-model="product.productAttributeCategoryId"
                   placeholder="请选择属性类型"
                   @change="handleProductAttrChange">
          <el-option
            v-for="item in productAttributeCategoryOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="商品规格：">
        <el-card shadow="never" class="cardBg">
          <div v-for="(productAttr,idx) in selectProductAttr" :key="idx">
            {{ productAttr.name }}：
            <el-checkbox-group v-if="productAttr.handAddStatus===0&&productAttr.inputList"
                               v-model="selectProductAttr[idx].values">
              <el-checkbox v-for="item in getInputListArr(productAttr.inputList)" :label="item" :key="item"
                           class="littleMarginLeft"></el-checkbox>
            </el-checkbox-group>
            <div v-else>
              <el-checkbox-group v-model="selectProductAttr[idx].values">
                <div v-for="(item,index) in selectProductAttr[idx].options" style="display: inline-block"
                     class="littleMarginLeft">
                  <el-checkbox :label="item" :key="item"></el-checkbox>
                  <el-button style="color: #ff4949" type="text" class="littleMarginLeft"
                             @click="handleRemoveProductAttrValue(idx,index)">删除
                  </el-button>
                </div>
              </el-checkbox-group>
              <el-input v-model="addProductAttrValue" style="width: 160px;margin-left: 10px" size="small"
                        clearable></el-input>
              <el-button class="littleMarginLeft" size="small" @click="handleAddProductAttrValue(idx)">增加</el-button>
            </div>
          </div>
        </el-card>
        <div v-if="product.productAttributeCategoryId">
          <el-table style="width: 100%;margin-top: 20px" class="sku_table" :data="product.skuStockList" border
                    size="small">
            <el-table-column
              v-for="(item,index) in selectProductAttr"
              :label="item.name"
              :key="item.id"
              align="center">
              <template slot-scope="scope">
                {{ getProductSkuSp(scope.row, index) }}
              </template>
            </el-table-column>
            <el-table-column
              label="销售价格"
              width="80"
              align="center">
              <template slot-scope="scope">
                <el-input size="mini" v-model="scope.row.price"></el-input>
              </template>
            </el-table-column>
            <el-table-column
              label="商品库存"
              width="80"
              align="center">
              <template slot-scope="scope">
                <el-input size="mini" v-model="scope.row.stock"></el-input>
              </template>
            </el-table-column>
            <el-table-column
              label="库存预警值"
              width="90"
              align="center">
              <template slot-scope="scope">
                <el-input size="mini" v-model="scope.row.lowStock"></el-input>
              </template>
            </el-table-column>
            <el-table-column
              label="SKU编号"
              align="center">
              <template slot-scope="scope">
                <el-input size="mini" v-model="scope.row.skuCode"></el-input>
              </template>
            </el-table-column>
            <el-table-column
              label="操作"
              width="70"
              align="center">
              <template slot-scope="scope">
                <el-button
                  style="color: #f56c6c"
                  type="text"
                  @click="handleRemoveProductSku(scope.$index, scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-button
            type="primary"
            style="margin-top: 20px"
            @click="handleRefreshProductSkuList">刷新列表
          </el-button>
          <el-button
            type="primary"
            style="margin-top: 20px"
            @click="handleSyncProductSkuPrice">同步价格
          </el-button>
          <el-button
            type="primary"
            style="margin-top: 20px"
            @click="handleSyncProductSkuStock">同步库存
          </el-button>
        </div>
      </el-form-item>
      <el-form-item label="属性图片：" v-if="hasAttrPic">
        <el-card shadow="never" class="cardBg">
          <div style="display: inline-block" v-for="(item,index) in selectProductAttrPics">
            <span style="font-weight: bold;">{{ item.name }}:</span>
            <common-upload style="margin-right: 10px;" :limit="1" v-model="item.pic" :isShowTip="false"></common-upload>
          </div>
          <div style="font-size: 12px;">
            请上传 大小不超过 <b style="color: #f56c6c">5MB</b> 格式为 <b style="color: #f56c6c">png/jpg/jpeg</b> 的文件
          </div>
        </el-card>
      </el-form-item>
      <el-form-item label="商品参数：">
        <el-card shadow="never" class="cardBg">
          <div v-for="(item,index) in selectProductParam" :class="{littleMarginTop:index!==0}">
            <div class="paramInputLabel">{{ item.name }}:</div>
            <el-select v-if="item.inputType===1" class="paramInput" v-model="selectProductParam[index].value">
              <el-option
                v-for="item in getParamInputList(item.inputList)"
                :key="item"
                :label="item"
                :value="item">
              </el-option>
            </el-select>
            <el-input v-else class="paramInput" v-model="selectProductParam[index].value"></el-input>
          </div>
        </el-card>
      </el-form-item>
      <el-form-item label="商品相册：">
        <common-upload v-model="selectProductPics"></common-upload>
      </el-form-item>
      <el-form-item label="规格参数：">
        <el-tabs v-model="activeHtmlName" type="card">
          <el-tab-pane label="电脑端详情" name="pc">
            <editor :width="595" :height="300" v-model="product.detailHtml"></editor>
          </el-tab-pane>
          <el-tab-pane label="移动端详情" name="mobile">
            <editor :width="595" :height="300" v-model="product.detailMobileHtml"></editor>
          </el-tab-pane>
        </el-tabs>
      </el-form-item>
      <el-form-item style="text-align: center">
        <el-button size="medium" @click="handlePrev">上一步，填写商品促销</el-button>
        <el-button type="primary" size="medium" @click="handleNext">下一步，选择商品关联</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {listProdAttrCate} from '@/api/mall/prodAttrCate'
import {listProdAttr} from '@/api/mall/prodAttr'

export default {
  name: "ProductAttrItem",
  props: {
    value: Object,
  },
  data() {
    return {
      //商品
      product: {},
      //编辑模式时是否初始化成功
      hasEditCreated: false,
      //商品属性分类下拉选项
      productAttributeCategoryOptions: [],
      //选中的商品属性
      selectProductAttr: [],
      //选中的商品参数
      selectProductParam: [],
      //选中的商品属性图片
      selectProductAttrPics: [],
      //可手动添加的商品属性
      addProductAttrValue: '',
      //商品富文本详情激活类型
      activeHtmlName: 'pc'
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
    //是否有商品属性图片
    hasAttrPic() {
      if (this.selectProductAttrPics.length < 1) {
        return false;
      }
      return true;
    },
    //商品的编号
    productId() {
      return this.product.id;
    },
    //商品的主图和画册图片
    selectProductPics: {
      get: function () {
        let pics = [];
        if (this.product.picture) {
          pics.push(this.product.picture);
        }
        if (this.product.albumPics) {
          let albumPics = this.product.albumPics.split(',');
          for (let i = 0; i < albumPics.length; i++) {
            pics.push(albumPics[i]);
          }
        }
        return pics.join(',');
      },
      set: function (newValue) {
        if (newValue) {
          let pics = newValue.split(',');
          if (pics.length > 0) {
            // 取第一张作展示图片
            this.product.picture = pics[0];
            pics.splice(0, 1);
            if (pics.length > 0) this.product.albumPics = pics.join(',');
          }
        }
      }
    }
  },
  created() {
    this.product = this.value;
    this.getProductAttrCateList();
  },
  watch: {
    productId: function (newValue) {
      if (!this.isEdit) return;
      if (this.hasEditCreated) return;
      if (newValue === undefined || newValue == null || newValue === 0) return;
      this.handleEditCreated();
    }
  },
  methods: {
    handleEditCreated() {
      //根据商品属性分类id获取属性和参数
      if (this.product.productAttributeCategoryId != null) {
        this.handleProductAttrChange(this.product.productAttributeCategoryId);
      }
      this.hasEditCreated = true;
    },
    getProductAttrCateList() {
      listProdAttrCate().then(response => {
        this.productAttributeCategoryOptions = response.rows;
      });
    },
    getProductAttrList(type, cid) {
      let param = {productAttributeCategoryId: cid, type: type};
      listProdAttr(param).then(response => {
        let list = response.rows;
        if (type === 0) {
          this.selectProductAttr = [];
          for (let i = 0; i < list.length; i++) {
            let options = [];
            let values = [];
            if (this.isEdit) {
              if (list[i].handAddStatus === 1) {
                //编辑状态下获取手动添加编辑属性
                options = this.getEditAttrOptions(list[i].id);
              }
              //编辑状态下获取选中属性
              values = this.getEditAttrValues(i);
            }
            this.selectProductAttr.push({
              id: list[i].id,
              name: list[i].name,
              handAddStatus: list[i].handAddStatus,
              inputList: list[i].inputList,
              values: values,
              options: options
            });
          }
          if (this.isEdit) {
            //编辑模式下刷新商品属性图片
            this.refreshProductAttrPics();
          }
        } else {
          this.selectProductParam = [];
          for (let i = 0; i < list.length; i++) {
            let value = null;
            if (this.isEdit) {
              //编辑模式下获取参数属性
              value = this.getEditParamValue(list[i].id);
            }
            this.selectProductParam.push({
              id: list[i].id,
              name: list[i].name,
              value: value,
              inputType: list[i].inputType,
              inputList: list[i].inputList
            });
          }
        }
      });
    },
    //获取设置的可手动添加属性值
    getEditAttrOptions(id) {
      let options = [];
      for (let i = 0; i < this.product.productAttributeValueList.length; i++) {
        let attrValue = this.product.productAttributeValueList[i];
        if (attrValue.productAttributeId === id) {
          let strArr = attrValue.value.split(',');
          for (let j = 0; j < strArr.length; j++) {
            options.push(strArr[j]);
          }
          break;
        }
      }
      return options;
    },
    //获取选中的属性值
    getEditAttrValues(index) {
      let values = new Set();
      if (index === 0) {
        for (let i = 0; i < this.product.skuStockList.length; i++) {
          let sku = this.product.skuStockList[i];
          let spData = JSON.parse(sku.spData);
          if (spData != null && spData.length >= 1) {
            values.add(spData[0].value);
          }
        }
      } else if (index === 1) {
        for (let i = 0; i < this.product.skuStockList.length; i++) {
          let sku = this.product.skuStockList[i];
          let spData = JSON.parse(sku.spData);
          if (spData != null && spData.length >= 2) {
            values.add(spData[1].value);
          }
        }
      } else {
        for (let i = 0; i < this.product.skuStockList.length; i++) {
          let sku = this.product.skuStockList[i];
          let spData = JSON.parse(sku.spData);
          if (spData != null && spData.length >= 3) {
            values.add(spData[2].value);
          }
        }
      }
      return Array.from(values);
    },
    //获取属性的值
    getEditParamValue(id) {
      for (let i = 0; i < this.product.productAttributeValueList.length; i++) {
        if (id === this.product.productAttributeValueList[i].productAttributeId) {
          return this.product.productAttributeValueList[i].value;
        }
      }
    },
    handleProductAttrChange(value) {
      this.getProductAttrList(0, value);
      this.getProductAttrList(1, value);
    },
    getInputListArr(inputList) {
      return inputList.split(',');
    },
    handleAddProductAttrValue(idx) {
      let options = this.selectProductAttr[idx].options;
      if (this.addProductAttrValue == null || this.addProductAttrValue == '') {
        this.$message({
          message: '属性值不能为空',
          type: 'warning',
          duration: 1000
        });
        return
      }
      if (options.indexOf(this.addProductAttrValue) !== -1) {
        this.$message({
          message: '属性值不能重复',
          type: 'warning',
          duration: 1000
        });
        return;
      }
      this.selectProductAttr[idx].options.push(this.addProductAttrValue);
      this.addProductAttrValue = null;
    },
    handleRemoveProductAttrValue(idx, index) {
      this.selectProductAttr[idx].options.splice(index, 1);
    },
    getProductSkuSp(row, index) {
      let spData = JSON.parse(row.spData);
      if (spData != null && index < spData.length) {
        return spData[index].value;
      } else {
        return null;
      }
    },
    handleRefreshProductSkuList() {
      this.$confirm('刷新列表将导致sku信息重新生成，是否要刷新', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.refreshProductAttrPics();
        this.refreshProductSkuList();
      });
    },
    handleSyncProductSkuPrice() {
      this.$confirm('将同步第一个sku的价格到所有sku,是否继续', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.product.skuStockList !== null && this.product.skuStockList.length > 0) {
          let tempSkuList = [];
          tempSkuList = tempSkuList.concat(tempSkuList, this.product.skuStockList);
          let price = this.product.skuStockList[0].price;
          if (price) {
            for (let i = 0; i < tempSkuList.length; i++) {
              tempSkuList[i].price = price;
            }
            this.product.skuStockList = [];
            this.product.skuStockList = this.product.skuStockList.concat(this.product.skuStockList, tempSkuList);
          } else {
            this.$modal.msgError("同步失败，第一个价格不能为空")
          }
        }
      });
    },
    handleSyncProductSkuStock() {
      this.$confirm('将同步第一个sku的库存到所有sku,是否继续', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.product.skuStockList !== null && this.product.skuStockList.length > 0) {
          let tempSkuList = [];
          tempSkuList = tempSkuList.concat(tempSkuList, this.product.skuStockList);
          let stock = this.product.skuStockList[0].stock;
          // let lowStock = this.product.skuStockList[0].lowStock;
          if (stock) {
            for (let i = 0; i < tempSkuList.length; i++) {
              tempSkuList[i].stock = stock;
              // tempSkuList[i].lowStock = lowStock;
            }
            this.product.skuStockList = [];
            this.product.skuStockList = this.product.skuStockList.concat(this.product.skuStockList, tempSkuList);
          } else {
            this.$modal.msgError("同步失败，第一个库存不能为空")
          }
        }
      });
    },
    refreshProductSkuList() {
      this.product.skuStockList = [];
      let skuList = this.product.skuStockList;
      //只有一个属性时
      if (this.selectProductAttr.length === 1) {
        let attr = this.selectProductAttr[0];
        for (let i = 0; i < attr.values.length; i++) {
          skuList.push({
            spData: JSON.stringify([{key: attr.name, value: attr.values[i]}])
          });
        }
      } else if (this.selectProductAttr.length === 2) {
        let attr0 = this.selectProductAttr[0];
        let attr1 = this.selectProductAttr[1];
        for (let i = 0; i < attr0.values.length; i++) {
          if (attr1.values.length === 0) {
            skuList.push({
              spData: JSON.stringify([{key: attr0.name, value: attr0.values[i]}])
            });
            continue;
          }
          for (let j = 0; j < attr1.values.length; j++) {
            let spData = [];
            spData.push({key: attr0.name, value: attr0.values[i]});
            spData.push({key: attr1.name, value: attr1.values[j]});
            skuList.push({
              spData: JSON.stringify(spData)
            });
          }
        }
      } else {
        let attr0 = this.selectProductAttr[0];
        let attr1 = this.selectProductAttr[1];
        let attr2 = this.selectProductAttr[2];
        for (let i = 0; i < attr0.values.length; i++) {
          if (attr1.values.length === 0) {
            skuList.push({
              spData: JSON.stringify([{key: attr0.name, value: attr0.values[i]}])
            });
            continue;
          }
          for (let j = 0; j < attr1.values.length; j++) {
            if (attr2.values.length === 0) {
              let spData = [];
              spData.push({key: attr0.name, value: attr0.values[i]});
              spData.push({key: attr1.name, value: attr1.values[j]});
              skuList.push({
                spData: JSON.stringify(spData)
              });
              continue;
            }
            for (let k = 0; k < attr2.values.length; k++) {
              let spData = [];
              spData.push({key: attr0.name, value: attr0.values[i]});
              spData.push({key: attr1.name, value: attr1.values[j]});
              spData.push({key: attr2.name, value: attr2.values[k]});
              skuList.push({
                spData: JSON.stringify(spData)
              });
            }
          }
        }
      }
    },
    refreshProductAttrPics() {
      this.selectProductAttrPics = [];
      if (this.selectProductAttr.length >= 1) {
        let values = this.selectProductAttr[0].values;
        for (let i = 0; i < values.length; i++) {
          let pic = null;
          if (this.isEdit) {
            //编辑状态下获取图片
            pic = this.getProductSkuPic(values[i]);
          }
          this.selectProductAttrPics.push({name: values[i], pic: pic})
        }
      }
    },
    //获取商品相关属性的图片
    getProductSkuPic(name) {
      for (let i = 0; i < this.product.skuStockList.length; i++) {
        let spData = JSON.parse(this.product.skuStockList[i].spData);
        if (name === spData[0].value) {
          return this.product.skuStockList[i].pic;
        }
      }
      return null;
    },
    //合并商品属性
    mergeProductAttrValue() {
      this.product.productAttributeValueList = [];
      for (let i = 0; i < this.selectProductAttr.length; i++) {
        let attr = this.selectProductAttr[i];
        if (attr.handAddStatus === 1 && attr.options != null && attr.options.length > 0) {
          this.product.productAttributeValueList.push({
            productAttributeId: attr.id,
            value: this.getOptionStr(attr.options)
          });
        }
      }
      for (let i = 0; i < this.selectProductParam.length; i++) {
        let param = this.selectProductParam[i];
        this.product.productAttributeValueList.push({
          productAttributeId: param.id,
          value: param.value
        });
      }
    },
    //合并商品属性图片
    mergeProductAttrPics() {
      for (let i = 0; i < this.selectProductAttrPics.length; i++) {
        for (let j = 0; j < this.product.skuStockList.length; j++) {
          let spData = JSON.parse(this.product.skuStockList[j].spData);
          if (spData[0].value === this.selectProductAttrPics[i].name) {
            this.product.skuStockList[j].pic = this.selectProductAttrPics[i].pic;
          }
        }
      }
    },
    getOptionStr(arr) {
      let str = '';
      for (let i = 0; i < arr.length; i++) {
        str += arr[i];
        if (i != arr.length - 1) {
          str += ',';
        }
      }
      return str;
    },
    handleRemoveProductSku(index, row) {
      let list = this.product.skuStockList;
      if (list.length === 1) {
        list.pop();
      } else {
        list.splice(index, 1);
      }
    },
    getParamInputList(inputList) {
      return inputList.split(',');
    },
    handlePrev() {
      this.$emit('prevStep')
    },
    handleNext() {
      this.mergeProductAttrValue();
      this.mergeProductAttrPics();
      this.$emit('nextStep')
    }
  }
}
</script>

<style scoped>
.littleMarginLeft {
  margin-left: 10px;
}

.littleMarginTop {
  margin-top: 10px;
}

.paramInput {
  width: 250px;
}

.paramInputLabel {
  display: inline-block;
  width: 100px;
  text-align: right;
  padding-right: 10px
}

.cardBg {
  background: #F8F9FC;
}

.sku_table >>> .el-input__inner {
  padding: 0 8px;
  text-align: center;
}
</style>
