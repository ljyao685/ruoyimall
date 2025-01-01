<template>
  <div :style="{height: mainHeight}">
    <el-container style="height:100%;">
      <el-header style="height:auto;padding:10px;">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
          <el-form-item label="属性名称" prop="name">
            <el-input
              v-model="queryParams.name"
              placeholder="请输入属性名称"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            <el-button v-if="$route.query.cid" type="warning" icon="el-icon-back" size="mini" @click="close">返回
            </el-button>
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
              v-hasPermi="['mall:prodAttr:add']"
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
              v-hasPermi="['mall:prodAttr:edit']"
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
              v-hasPermi="['mall:prodAttr:remove']"
            >删除
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </el-header>

      <el-main style="height:100%;padding:0;">
        <el-table ref="table" style="width: 100%;" height="100%" v-loading="loading" :data="prodAttrList"
                  @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="属性名称" align="center" prop="name"/>
          <el-table-column label="属性类型" align="center">
            <template slot-scope="scope">{{ $route.query.cname }}</template>
          </el-table-column>
          <el-table-column label="属性是否可选" align="center">
            <template slot-scope="scope">{{ scope.row.selectType|selectTypeFilter }}</template>
          </el-table-column>
          <el-table-column label="属性值的录入方式" align="center">
            <template slot-scope="scope">{{ scope.row.inputType|inputTypeFilter }}</template>
          </el-table-column>
          <el-table-column label="可选值列表" align="center">
            <template slot-scope="scope">{{ scope.row.inputList }}</template>
          </el-table-column>
          <el-table-column label="排序" align="center">
            <template slot-scope="scope">{{ scope.row.sort }}</template>
          </el-table-column>
          <el-table-column width="180" label="操作" align="center">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['mall:prodAttr:edit']"
              >修改
              </el-button>
              <el-button
                style="color: #ff4949"
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['mall:prodAttr:remove']"
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

    <!-- 添加或修改商品属性信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="50%" append-to-body :close-on-click-modal="false">
      <el-form ref="form" v-if="open" :model="form" :rules="rules" label-width="125px">
        <el-form-item label="属性名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入属性名称"/>
        </el-form-item>
        <el-form-item label="属性类型">
          <el-select v-model="form.productAttributeCategoryId" placeholder="请选择属性类型">
            <el-option
              v-for="item in productAttrCateList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分类筛选样式">
          <el-radio-group v-model="form.filterType">
            <el-radio :label="0">普通</el-radio>
            <el-radio :label="1">颜色</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="能否进行检索">
          <el-radio-group v-model="form.searchType">
            <el-radio :label="0">不需要检索</el-radio>
            <el-radio :label="1">关键字检索</el-radio>
            <el-radio :label="2">范围检索</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="商品属性关联">
          <el-radio-group v-model="form.relatedStatus">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="属性是否可选">
          <el-radio-group v-model="form.selectType">
            <el-radio :label="0">唯一</el-radio>
            <el-radio :label="1">单选</el-radio>
            <el-radio :label="2">复选</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="属性值的录入方式">
          <el-radio-group v-model="form.inputType">
            <el-radio :label="0">手工录入</el-radio>
            <el-radio :label="1">从下面列表中选择</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="属性值可选值列表">
          <el-input :autosize="true" type="textarea" v-model="inputListFormat" placeholder="多个属性值请按回车键区分开"></el-input>
        </el-form-item>
        <el-form-item label="是否支持手动新增">
          <el-radio-group v-model="form.handAddStatus">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="排序">
          <el-input v-model="form.sort" placeholder="请输入排序(值越大，越排前)"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注"/>
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
import {addProdAttr, delProdAttr, exportProdAttr, getProdAttr, listProdAttr, updateProdAttr} from "@/api/mall/prodAttr";
import {listProdAttrCate} from "@/api/mall/prodAttrCate";

export default {
  name: "ProdAttr",
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
      // 商品属性信息表格数据
      prodAttrList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        productAttributeCategoryId: null,
        type: null,
      },
      //属性类型列表
      productAttrCateList: [],
      //属性可选值列表
      inputListFormat: undefined,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          {required: true, message: '请输入属性名称', trigger: 'blur'},
          {min: 2, max: 140, message: '长度在 2 到 140 个字符', trigger: 'blur'}
        ]
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
  watch: {
    inputListFormat: function (newValue, oldValue) {
      if (newValue) {
        newValue = newValue.replace(/\n/g, ',');
        this.form.inputList = newValue;
      }
    }
  },
  created() {
    this.getList();
    this.getAttrCateList();
  },
  methods: {
    /** 查询商品属性信息列表 */
    getList() {
      if (this.$route.query.cid != null) {
        this.queryParams.productAttributeCategoryId = this.$route.query.cid;
      }
      if (this.$route.query.type != null) {
        this.queryParams.type = this.$route.query.type;
      }
      this.loading = true;
      listProdAttr(this.queryParams).then(response => {
        this.prodAttrList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询属性类型列表 */
    getAttrCateList() {
      listProdAttrCate().then(response => {
        this.productAttrCateList = response.rows;
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.inputListFormat = undefined;
      this.form = {
        id: null,
        createBy: null,
        createTime: null,
        remark: null,
        searchValue: null,
        tenantId: null,
        updateBy: null,
        updateTime: null,
        filterType: 0,
        handAddStatus: 0,
        inputList: null,
        inputType: 0,
        name: null,
        productAttributeCategoryId: Number(this.$route.query.cid),
        relatedStatus: 0,
        searchType: 0,
        selectType: 0,
        sort: 0,
        type: Number(this.$route.query.type)
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
    /** 返回上一级 */
    close() {
      this.$router.go(-1);
      this.$store.dispatch("tagsView/delView", this.$route);
      this.$store.dispatch("tagsView/delCachedView", this.$route);
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
      this.title = "添加商品属性信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProdAttr(id).then(response => {
        this.form = response.data;
        this.inputListFormat = this.form.inputList ? this.form.inputList.replace(/,/g, '\n') : undefined;
        this.open = true;
        this.title = "修改商品属性信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateProdAttr(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProdAttr(this.form).then(response => {
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
      this.$confirm('是否确认删除商品属性信息编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delProdAttr(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有商品属性信息数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportProdAttr(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });
    }
  },
  filters: {
    inputTypeFilter(value) {
      if (value === 1) {
        return '从列表中选取';
      } else {
        return '手工录入'
      }
    },
    selectTypeFilter(value) {
      if (value === 1) {
        return '单选';
      } else if (value === 2) {
        return '多选';
      } else {
        return '唯一'
      }
    },
  }
};
</script>
