<template>
  <div :style="{height: mainHeight}">
    <el-container style="height:100%;">
      <el-header style="height:auto;padding:10px;">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px"
                 @submit.native.prevent>
          <el-form-item label="分类名称" prop="name">
            <el-input
              v-model="queryParams.name"
              placeholder="请输入分类名称"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['mall:prodCate:add']"
            >新增
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              plain
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate"
              v-hasPermi="['mall:prodCate:edit']"
            >修改
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              plain
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['mall:prodCate:remove']"
            >删除
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </el-header>

      <el-main style="height:100%;padding:0;">
        <el-table ref="table" style="width: 100%;" height="100%" :row-class-name="tableRowClassName"
                  v-loading="loading" :data="prodCateList" row-key="id"
                  :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
          <el-table-column label="分类名称" align="left" min-width="100" prop="name"/>
          <el-table-column label="级别" align="center" prop="level">
            <template slot-scope="scope">{{ scope.row.level | levelFilter }}</template>
          </el-table-column>
          <el-table-column label="商品数量" align="center" prop="productCount">
            <template slot-scope="scope">
              <span v-if="scope.row.level===0"></span>
              <span v-else @click="getProductList(scope.row.id)">
                <el-link>{{ scope.row.productCount }}</el-link></span>
            </template>
          </el-table-column>
          <el-table-column label="数量单位" align="center" prop="productUnit"/>
          <el-table-column label="排序" align="center" prop="sort"/>
          <el-table-column label="显示状态" align="center" prop="showStatus">
            <template slot-scope="scope">
              <el-switch
                :active-value="1"
                :inactive-value="0"
                v-model="scope.row.showStatus"
                @change="handleStatusChange( scope.row,'show')">
              </el-switch>
            </template>
          </el-table-column>
          <el-table-column label="导航栏" align="center" prop="navStatus">
            <template slot-scope="scope">
              <el-switch
                :active-value="1"
                :inactive-value="0"
                v-model="scope.row.navStatus"
                @change="handleStatusChange( scope.row,'nav')">
              </el-switch>
            </template>
          </el-table-column>
          <el-table-column width="180" label="操作" align="center">
            <template slot-scope="scope">
              <el-button
                v-show="scope.row.level===0"
                size="mini"
                type="text"
                icon="el-icon-plus"
                @click="handleAdd(scope.row)"
                v-hasPermi="['mall:prodCate:add']"
              >新增
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['mall:prodCate:edit']"
              >修改
              </el-button>
              <el-button
                style="color: #ff4949"
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['mall:prodCate:remove']"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
      <el-footer style="border-top:1px solid #ccc;height:auto;padding:10px;">
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </el-footer>
    </el-container>

    <!-- 查看分类商品数量信息对话框 -->
    <el-dialog :title="dataTitle" :visible.sync="dataVisible" width="720px" append-to-body
               :close-on-click-modal="false">
      <el-table :data="dataList" size="small" width="100%" height="400px" border>
        <el-table-column type="index" align="center"></el-table-column>
        <el-table-column label="商品名称" align="center" min-width="110" prop="name"></el-table-column>
        <el-table-column label="货号" align="center" prop="productSn"></el-table-column>
        <el-table-column label="上架状态" align="center" prop="publishStatus">
          <template slot-scope="scope">
            <el-tag size="mini" type="success" v-if="scope.row.publishStatus===1">销售中</el-tag>
            <el-tag size="mini" type="info" v-else>已下架</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="销量" align="center" prop="sale"></el-table-column>
        <el-table-column label="单位" align="center" prop="unit"></el-table-column>
      </el-table>
      <el-footer style="border-top:1px solid #ccc;height:auto;padding:10px;">
        <pagination
          v-show="dataTotal>0"
          :total="dataTotal"
          :page.sync="dataParams.pageNum"
          :limit.sync="dataParams.pageSize"
          @pagination="getProductList(prodCateId)"
        />
      </el-footer>
    </el-dialog>

    <!-- 添加或修改商品分类信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="50%" append-to-body :close-on-click-modal="false">
      <el-form v-if="open" ref="form" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="上级分类" prop="parentId">
          <select-tree v-model="form.parentId" :dataOptions="selectProductCateList" :clearable="false"></select-tree>
        </el-form-item>
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入分类名称"/>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model.number="form.sort" placeholder="请输入排序(数值越大，越排前)"/>
        </el-form-item>
        <el-form-item label="显示状态">
          <el-radio-group v-model="form.showStatus">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否显示在导航栏">
          <el-radio-group v-model="form.navStatus">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="分类图标" prop="icon">
          <common-upload :limit="1" v-model="form.icon"></common-upload>
        </el-form-item>
        <el-form-item label="商品单位" prop="productUnit">
          <el-input v-model="form.productUnit" placeholder="请输入数量单位"/>
        </el-form-item>
        <el-form-item v-for="(filterProductAttr, index) in filterProductAttrList"
                      :label="index | filterLabelFilter"
                      :key="filterProductAttr.key"
        >
          <el-cascader
            clearable
            v-model="filterProductAttr.value"
            :options="filterAttrs">
          </el-cascader>
          <el-button style="margin-left: 20px" size="mini" type="danger"
                     @click.prevent="removeFilterAttr(filterProductAttr)">删除
          </el-button>
          <el-button size="mini" type="primary" @click="handleAddFilterAttr()">新增</el-button>
        </el-form-item>
        <el-form-item label="关键字" prop="keywords">
          <el-input v-model="form.keywords" placeholder="请输入关键字"/>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入描述"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  addProdCate,
  delProdCate,
  exportProdCate,
  getProdCate,
  getProdCateTreeList,
  listProdCate,
  updateProdCate,
  updateProdCateStatus
} from "@/api/mall/prodCate";
import {listProdAttrCateWithAttr} from "@/api/mall/prodAttrCate";
import {listProduct} from "@/api/mall/product";

export default {
  name: "ProdCate",
  data() {
    return {
      mainHeight: '100px',//表格默认高度
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 商品分类信息表格数据
      prodCateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 分类商品数量信息
      prodCateId: null,
      dataParams: {pageNum: 1, pageSize: 10},
      dataTotal: 0,
      dataType: '',//product-商品数量
      dataList: [],
      dataTitle: '',
      dataVisible: false,
      // 查询参数
      queryParams: {
        name: null,
        parentId: null,
      },
      //分类集合
      selectProductCateList: [],
      //筛选属性集合
      filterAttrs: [],
      //商品属性集合
      filterProductAttrList: [{
        value: null
      }],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        parentId: [
          {required: true, message: '请选择父级分类', trigger: 'blur'},
        ],
        name: [
          {required: true, message: '请输入分类名称', trigger: 'blur'},
        ],
      }
    };
  },
  mounted() {
    //此处动态计算窗口变化
    this.mainHeight = document.documentElement.clientHeight - 85 + 'px'
    const that = this
    window.onresize = function temp() {
      that.mainHeight = document.documentElement.clientHeight - 85 + 'px'
    }
  },
  created() {
    this.getList();
    this.getProdCateTreeList();
    this.getProductAttrCateList();
  },
  methods: {
    /** 查询商品分类信息列表 */
    getList() {
      this.loading = true;
      listProdCate(this.queryParams).then(response => {
        this.prodCateList = this.handleTree(response.rows);
        this.loading = false;
      });
    },
    //查询分类下拉选择树
    getProdCateTreeList() {
      getProdCateTreeList().then(response => {
        this.selectProductCateList.push({id: 0, label: '主类目', children: response.rows});
      });
    },
    //查询属性分类及对应属性
    getProductAttrCateList() {
      listProdAttrCateWithAttr().then(response => {
        let list = response.rows;
        for (let i = 0; i < list.length; i++) {
          let productAttrCate = list[i];
          let children = [];
          if (productAttrCate.prodAttrList != null && productAttrCate.prodAttrList.length > 0) {
            for (let j = 0; j < productAttrCate.prodAttrList.length; j++) {
              children.push({
                label: productAttrCate.prodAttrList[j].name,
                value: productAttrCate.prodAttrList[j].id
              })
            }
          }
          this.filterAttrs.push({label: productAttrCate.name, value: productAttrCate.id, children: children});
        }
      });
    },
    //行的 className 的回调方法
    tableRowClassName({row, rowIndex}) {
      if (row.level === 0) {
        return 'table-row-level_0';
      } else {
        return 'table-row-level_1';
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.filterProductAttrList = [{value: null}];
      this.form = {
        id: null,
        createBy: null,
        createTime: null,
        remark: null,
        searchValue: null,
        tenantId: null,
        updateBy: null,
        updateTime: null,
        description: null,
        icon: null,
        keywords: null,
        level: null,
        name: null,
        navStatus: 0,
        parentId: 0,
        productCount: 0,
        productUnit: null,
        showStatus: 0,
        sort: 0,
        productAttributeIdList: []
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    //查看分类对应的商品信息
    getProductList(rowId) {
      this.prodCateId = rowId;
      this.dataParams.productCategoryId = rowId;
      listProduct(this.dataParams).then(response => {
        this.dataList = response.rows;
        this.dataTotal = response.total;
        this.dataTitle = "分类商品列表";
        this.dataVisible = true;
      })
    },
    //修改商品分类状态
    handleStatusChange(row, type) {
      let data = {
        id: row.id
      }
      if (type === 'show') data.showStatus = row.showStatus;
      else data.navStatus = row.navStatus;
      updateProdCateStatus(data).then(response => {
        this.$message({
          message: '修改成功',
          type: 'success',
          duration: 1000
        });
      }).catch(error => {
        if (type === 'show') row.showStatus = row.showStatus === 0 ? 1 : 0
        else row.navStatus = row.navStatus === 0 ? 1 : 0
      })
    },
    //转移分类下的商品
    handleTransferProduct(row) {
      console.log('handleAddProductCate');
    },
    //移除分类属性
    removeFilterAttr(productAttribute) {
      if (this.filterProductAttrList.length === 1) {
        this.$message({
          message: '至少要留一个',
          type: 'warning',
          duration: 1000
        });
        return;
      }
      let index = this.filterProductAttrList.indexOf(productAttribute);
      if (index !== -1) {
        this.filterProductAttrList.splice(index, 1)
      }
    },
    //新增分类属性
    handleAddFilterAttr() {
      if (this.filterProductAttrList.length === 3) {
        this.$message({
          message: '最多添加三个',
          type: 'warning',
          duration: 1000
        });
        return;
      }
      this.filterProductAttrList.push({
        value: null,
        key: Date.now()
      });
    },
    //获取分类对应的属性
    getProductAttributeIdList() {
      //获取选中的筛选商品属性
      let productAttributeIdList = [];
      for (let i = 0; i < this.filterProductAttrList.length; i++) {
        let item = this.filterProductAttrList[i];
        if (item.value !== null && item.value.length === 2) {
          productAttributeIdList.push(item.value[1]);
        }
      }
      return productAttributeIdList;
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      if (row) {
        this.form.parentId = row.id;
      }
      this.open = true;
      this.title = "添加商品分类信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProdCate(id).then(response => {
        let attrs = response.attrInfo;
        if (attrs != null && attrs.length > 0) {
          this.filterProductAttrList = [];
          for (let i = 0; i < attrs.length; i++) {
            this.filterProductAttrList.push({
              key: Date.now() + i,
              value: [attrs[i].productAttributeCategoryId, attrs[i].id]
            })
          }
        }
        this.form = response.data;
        this.open = true;
        this.title = "修改商品分类信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.productAttributeIdList = this.getProductAttributeIdList();
          if (this.form.id != null) {
            updateProdCate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProdCate(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除商品分类信息编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delProdCate(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有商品分类信息数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportProdCate(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });
    }
  },
  filters: {
    levelFilter(value) {
      if (value === 0) {
        return '一级';
      } else if (value === 1) {
        return '二级';
      }
    },
    disableNextLevel(value) {
      if (value === 0) {
        return false;
      } else {
        return true;
      }
    },
    filterLabelFilter(index) {
      if (index === 0) {
        return '筛选属性';
      } else {
        return '';
      }
    }
  }
};
</script>
<style>
.el-table .table-row-level_0 {
  font-weight: bold;
}

.el-table .table-row-level_1 {
  font-weight: lighter;
  font-size: 13px;
}
</style>
