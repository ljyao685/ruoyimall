<template>
  <div style="margin-top: 50px">
    <el-form :model="product" :rules="rules" ref="productInfoForm" label-width="120px" style="width: 600px">
      <el-form-item label="商品分类：" prop="productCategoryId">
        <el-cascader
          style="width: 100%"
          v-model="selectProductCateValue"
          :options="prodCateListOptions">
        </el-cascader>
      </el-form-item>
      <el-form-item label="商品名称：" prop="name">
        <el-input v-model="product.name" placeholder="请输入商品名称"></el-input>
      </el-form-item>
      <el-form-item label="副标题：" prop="subTitle">
        <el-input v-model="product.subTitle" placeholder="请输入副标题"></el-input>
      </el-form-item>
      <el-form-item label="商品品牌：" prop="brandId">
        <el-select
          v-model="product.brandId"
          @change="handleBrandChange"
          placeholder="请选择品牌">
          <el-option
            v-for="item in brandListOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="商品介绍：">
        <el-input
          :autoSize="true"
          v-model="product.description"
          type="textarea"
          placeholder="请输入内容"></el-input>
      </el-form-item>
      <el-form-item label="商品货号：">
        <el-input v-model="product.productSn" placeholder="请输入商品货号"></el-input>
      </el-form-item>
      <el-form-item label="商品售价：">
        <el-input v-model="product.price"></el-input>
      </el-form-item>
      <el-form-item label="市场价：">
        <el-input v-model="product.originalPrice"></el-input>
      </el-form-item>
      <el-form-item label="商品库存：">
        <el-input v-model="product.stock"></el-input>
      </el-form-item>
      <el-form-item label="计量单位：">
        <el-input v-model="product.unit" placeholder="请输入商品计量单位"></el-input>
      </el-form-item>
      <el-form-item label="商品重量：">
        <el-input v-model="product.weight" style="width: 300px"></el-input>
        <span style="margin-left: 20px">克</span>
      </el-form-item>
      <el-form-item label="排序：">
        <el-input v-model="product.sort"></el-input>
      </el-form-item>
      <el-form-item style="text-align: center">
        <el-button type="primary" size="medium" @click="handleNext('productInfoForm')">下一步，填写商品促销</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {listBrand} from "@/api/mall/brand";
import {getProdCateCascaderList} from "@/api/mall/prodCate";

export default {
  name: "ProductInfoItem",
  props: {
    value: Object,
  },
  data() {
    return {
      //商品
      product: {},
      //编辑模式时是否初始化成功
      hasEditCreated: false,
      // 品牌列表集合
      brandListOptions: [],
      // 分类级联选择列表集合
      prodCateListOptions: [],
      //选中商品分类的值
      selectProductCateValue: [],
      rules: {
        name: [
          {required: true, message: '请输入商品名称', trigger: 'blur'},
          {min: 2, max: 140, message: '长度在 2 到 140 个字符', trigger: 'blur'}
        ],
        subTitle: [{required: true, message: '请输入商品副标题', trigger: 'blur'}],
        productCategoryId: [{required: true, message: '请选择商品分类', trigger: 'blur'}],
        brandId: [{required: true, message: '请选择商品品牌', trigger: 'blur'}],
        description: [{required: true, message: '请输入商品介绍', trigger: 'blur'}],
        requiredProp: [{required: true, message: '该项为必填项', trigger: 'blur'}]
      }
    };
  },
  created() {
    this.product = this.value;
    this.getBrandList();
    this.getProductCateList();
  },
  computed: {
    //商品ID
    productId() {
      return this.product.id;
    },
    //是否编辑
    isEdit() {
      if (this.product.id) {
        return true;
      } else {
        return false;
      }
    },
  },
  watch: {
    productId: function (newValue) {
      if (!this.isEdit) return;
      if (this.hasEditCreated) return;
      if (newValue === undefined || newValue == null || newValue === 0) return;
      this.handleEditCreated();
    },
    selectProductCateValue: function (newValue) {
      if (newValue != null && newValue.length === 2) {
        this.product.productCategoryId = newValue[1];
        this.product.productCategoryName = this.getCateNameById(this.product.productCategoryId);
      } else {
        this.product.productCategoryId = null;
        this.product.productCategoryName = null;
      }
    }
  },
  methods: {
    // 处理商品分类回显
    handleEditCreated() {
      if (this.product.productCategoryId != null) {
        this.selectProductCateValue.push(this.product.cateParentId);
        this.selectProductCateValue.push(this.product.productCategoryId);
      }
      this.hasEditCreated = true;
    },
    // 查询商品品牌列表
    getBrandList() {
      listBrand().then(response => {
        this.brandListOptions = response.rows;
      });
    },
    // 查询商品分类级选器列表
    getProductCateList() {
      getProdCateCascaderList().then(response => {
        this.prodCateListOptions = response.rows;
      });
    },
    // 根据分类ID获取分类名称
    getCateNameById(id) {
      let name = null;
      for (let i = 0; i < this.prodCateListOptions.length; i++) {
        for (let j = 0; j < this.prodCateListOptions[i].children.length; j++) {
          if (this.prodCateListOptions[i].children[j].value === id) {
            name = this.prodCateListOptions[i].children[j].label;
            return name;
          }
        }
      }
      return name;
    },
    // 下一步
    handleNext(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$emit('nextStep');
        } else {
          this.$modal.msgError("验证失败");
          return false;
        }
      });
    },
    // 选择品牌获取品牌名
    handleBrandChange(val) {
      let brandName = '';
      for (let i = 0; i < this.brandListOptions.length; i++) {
        if (this.brandListOptions[i].id === val) {
          brandName = this.brandListOptions[i].name;
          break;
        }
      }
      this.product.brandName = brandName;
    }
  }
}
</script>

<style scoped>
</style>
