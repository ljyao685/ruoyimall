<template>
  <div style="margin-top: 50px">
    <el-form :model="product"
             ref="productRelationForm"
             label-width="120px"
             style="width: 700px"
             size="small">
      <el-form-item label="关联专题：">
        <el-transfer
          style="display: inline-block"
          filterable
          :filter-method="filterMethod"
          filter-placeholder="请输入专题名称"
          v-model="selectSubject"
          :titles="subjectTitles"
          :data="subjectList">
        </el-transfer>
      </el-form-item>
      <el-form-item label="关联优选：">
        <el-transfer
          style="display: inline-block"
          filterable
          :filter-method="filterMethod"
          filter-placeholder="请输入优选名称"
          v-model="selectPreferenceArea"
          :titles="preferenceAreaTitles"
          :data="preferenceAreaList">
        </el-transfer>
      </el-form-item>
      <el-form-item style="text-align: center">
        <el-button size="medium" @click="handlePrev">上一步，填写商品属性</el-button>
        <el-button type="primary" size="medium" @click="handleFinishCommit">完成，提交商品</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {listSubject} from "@/api/mall/subject";
import {listPreferenceArea} from "@/api/mall/preferenceArea";

export default {
  name: "ProductRelationItem",
  props: {
    value: Object,
  },
  data() {
    return {
      //商品
      product: {},
      //所有专题列表
      subjectList: [],
      //专题左右标题
      subjectTitles: ['待选择', '已选择'],
      //所有优选专区列表
      preferenceAreaList: [],
      //优选专区左右标题
      preferenceAreaTitles: ['待选择', '已选择']
    };
  },
  created() {
    this.product = this.value;
    this.getSubjectList();
    this.getPreferenceAreaList();
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
    //选中的专题
    selectSubject: {
      get: function () {
        let subjects = [];
        if (this.product.subjectProductRelationList == null || this.product.subjectProductRelationList.length <= 0) {
          return subjects;
        }
        for (let i = 0; i < this.product.subjectProductRelationList.length; i++) {
          subjects.push(this.product.subjectProductRelationList[i].subjectId);
        }
        return subjects;
      },
      set: function (newValue) {
        this.product.subjectProductRelationList = [];
        for (let i = 0; i < newValue.length; i++) {
          this.product.subjectProductRelationList.push({subjectId: newValue[i]});
        }
      }
    },
    //选中的优选
    selectPreferenceArea: {
      get: function () {
        let preferenceAreas = [];
        if (this.product.preferenceAreaProductRelationList == null || this.product.preferenceAreaProductRelationList.length <= 0) {
          return preferenceAreas;
        }
        for (let i = 0; i < this.product.preferenceAreaProductRelationList.length; i++) {
          preferenceAreas.push(this.product.preferenceAreaProductRelationList[i].preferenceAreaId);
        }
        return preferenceAreas;
      },
      set: function (newValue) {
        this.product.preferenceAreaProductRelationList = [];
        for (let i = 0; i < newValue.length; i++) {
          this.product.preferenceAreaProductRelationList.push({preferenceAreaId: newValue[i]});
        }
      }
    }
  },
  methods: {
    /** 查询专题列表 */
    getSubjectList() {
      listSubject().then(response => {
        let list = response.rows;
        for (let i = 0; i < list.length; i++) {
          this.subjectList.push({
            label: list[i].name,
            key: list[i].id
          });
        }
      })
    },
    /** 查询优选专区列表 */
    getPreferenceAreaList() {
      listPreferenceArea().then(response => {
        let list = response.rows;
        for (let i = 0; i < list.length; i++) {
          this.preferenceAreaList.push({
            label: list[i].name,
            key: list[i].id
          });
        }
      })
    },
    filterMethod(query, item) {
      return item.label.indexOf(query) > -1;
    },
    handlePrev() {
      this.$emit('prevStep')
    },
    handleFinishCommit() {
      this.$emit('finishCommit');
    }
  }
}
</script>

<style scoped>

</style>
