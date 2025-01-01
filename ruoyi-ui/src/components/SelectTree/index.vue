<template>
  <el-select
    style="margin-top: 0;"
    popper-class="select-tree"
    ref="selectUpResId"
    :size="size"
    v-model="value"
    :clearable="clearable"
    :multiple="multiple"
    filterable
    placeholder="请选择"
    @clear="handleClear"
  >
    <!--如果不设置一个下拉选项，下面的树形组件将无法正常使用-->
    <el-option :value="value" :label="valueTitle">
      <!-- 设置树形组件-->
      <el-tree ref="selectTree" style="font-weight: bold;" id="tree-option" :data="dataOptions" :props="defaultProps"
               accordion highlight-current :node-key="defaultProps.id" :default-expanded-keys="defaultExpandedKey"
               :expand-on-click-node="false" @node-click="handleNodeClick">
      </el-tree>
    </el-option>
  </el-select>

</template>

<script>

export default {
  name: "SelectTree",
  props: {
    //下拉数据
    dataOptions: Array,
    //绑定值
    value: [Number, String],
    size: {
      type: String,
      default: 'medium'
    },
    clearable: {
      type: Boolean,
      default: true
    },
    multiple: {
      type: Boolean,
      default: false
    },
  },
  data() {
    return {
      valueId: this.value, // 初始值
      valueTitle: null,
      defaultExpandedKey: [0],
      defaultProps: {
        id: 'id',
        label: 'label',
        children: 'children',
      },
    }
  },
  watch: {
    /**
     * 监听绑定的值变化
     */
    value() {
      this.valueId = this.value
      this.initHandle()
    }
  },
  mounted() {
    this.$nextTick(function () {
      this.initHandle()
    })
  },
  methods: {
    // 初始化值
    initHandle() {
      if (this.valueId || this.valueId === 0) {
        this.valueTitle = this.$refs.selectTree.getNode(this.valueId).data[this.defaultProps.label] // 初始化显示
        this.$refs.selectTree.setCurrentKey(this.valueId)  // 设置默认选中
        this.defaultExpandedKey = [this.valueId] // 设置默认展开
      }
      //设置滚动
      this.$nextTick(() => {
        let scrollWrap = document.querySelectorAll('.select-tree .el-scrollbar .el-select-dropdown__wrap')[0]
        let scrollBar = document.querySelectorAll('.select-tree .el-scrollbar .el-scrollbar__bar')
        scrollWrap.style.cssText = 'margin-right: -17px; max-height: none; overflow: hidden;'
        scrollBar.forEach(ele => ele.style.width = 0)
      })
    },
    handleNodeClick(node) {
      // 这里主要配置树形组件点击节点后，设置选择器的值；自己配置的数据，仅供参考
      this.valueId = node[this.defaultProps.id]
      this.valueTitle = node[this.defaultProps.label]
      let data = {
        id: this.valueId,
        name: this.valueTitle
      }
      // 选择器执行完成后，使其失去焦点隐藏下拉框的效果
      this.$refs.selectUpResId.blur()
      this.$emit('modelValChange', this.valueId)
      this.$emit('selectBack', data)
      this.defaultExpandedKey = [0]
    },
    // 选择器配置可以清空选项，用户点击清空按钮时触发
    handleClear() {
      // 将选择器的值置空
      this.valueId = undefined
      this.valueTitle = ''
      let data = {
        id: this.valueId,
        name: this.valueTitle
      }
      this.clearSelected()
      this.$emit('modelValChange', undefined)
      this.$emit('selectBack', data)
      this.defaultExpandedKey = [0]
    },
    /* 清空选中样式 */
    clearSelected() {
      let allNode = document.querySelectorAll('#tree-option .el-tree-node')
      allNode.forEach((element) => element.classList.remove('is-current'))
    }
  }
}
</script>

<style scoped>
.el-select-dropdown el-popper {
  margin-top: 0 !important;
}

.el-scrollbar .el-scrollbar__view .el-select-dropdown__item {
  height: auto;
  max-height: 274px;
  padding: 0;
  overflow: hidden;
  overflow-y: auto;
}

.el-tree >>> .is-current > .el-tree-node__content .el-tree-node__label {
  color: #1890ff;
}

</style>
