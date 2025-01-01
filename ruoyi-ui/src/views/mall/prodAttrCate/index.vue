<template>
  <div :style="{height: mainHeight}">
    <el-container style="height:100%;">
      <el-header style="height:auto;padding:10px;">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px"
                 @submit.native.prevent>
          <el-form-item label="类型名称" prop="name">
            <el-input
              v-model="queryParams.name"
              placeholder="请输入类型名称"
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
              v-hasPermi="['mall:prodAttrCate:add']"
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
              v-hasPermi="['mall:prodAttrCate:edit']"
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
              v-hasPermi="['mall:prodAttrCate:remove']"
            >删除
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </el-header>

      <el-main style="height:100%;padding:0;">
        <el-table ref="table" style="width: 100%;" height="100%" v-loading="loading" :data="prodAttrCateList"
                  @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="类型名称" align="center" prop="name"/>
          <el-table-column label="属性数量" align="center" prop="attributeCount"/>
          <el-table-column label="参数数量" align="center" prop="paramCount"/>
          <el-table-column label="设置" min-width="120" align="center">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="getAttrList(scope.$index, scope.row)">属性列表
              </el-button>
              <el-button
                size="mini"
                @click="getParamList(scope.$index, scope.row)">参数列表
              </el-button>
            </template>
          </el-table-column>
          <el-table-column width="180" label="操作" align="center">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['mall:prodAttrCate:edit']"
              >修改
              </el-button>
              <el-button
                style="color: #ff4949"
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['mall:prodAttrCate:remove']"
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

    <!-- 添加或修改商品属性分类信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="30%" append-to-body :close-on-click-modal="false">
      <el-form ref="form" v-if="open" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类型名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入属性类型名称"/>
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
  addProdAttrCate,
  delProdAttrCate,
  exportProdAttrCate,
  getProdAttrCate,
  listProdAttrCate,
  updateProdAttrCate
} from "@/api/mall/prodAttrCate";

export default {
  name: "ProdAttrCate",
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
      // 商品属性分类信息表格数据
      prodAttrCateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        searchValue: null,
        tenantId: null,
        attributeCount: null,
        name: null,
        paramCount: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [{
          required: true, message: '请输入类型名称', trigger: 'blur'
        }]
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
  },
  methods: {
    /** 查询商品属性分类信息列表 */
    getList() {
      this.loading = true;
      listProdAttrCate(this.queryParams).then(response => {
        this.prodAttrCateList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        createBy: null,
        createTime: null,
        remark: null,
        searchValue: null,
        tenantId: null,
        updateBy: null,
        updateTime: null,
        attributeCount: 0,
        name: null,
        paramCount: 0
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 查看属性列表 */
    getAttrList(index, row) {
      const params = { cid: row.id, cname: row.name, type: 0 };
      this.$tab.openPage("商品类型-属性列表", '/mall/prodAttr', params);
    },
    /** 查看参数列表 */
    getParamList(index, row) {
      const params = { cid: row.id, cname: row.name, type: 1 };
      this.$tab.openPage("商品类型-参数列表", '/mall/prodAttr', params);
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加属性类型信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProdAttrCate(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改属性类型信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateProdAttrCate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProdAttrCate(this.form).then(response => {
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
      this.$confirm('是否确认删除商品属性分类信息编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delProdAttrCate(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有商品属性分类信息数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportProdAttrCate(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });
    }
  }
};
</script>
