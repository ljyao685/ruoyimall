<template>
  <div :style="{height: mainHeight}">
    <el-container style="height:100%;">
      <el-header style="height:auto;padding:10px;">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="100px"
                 @submit.prevent.native>
          <el-form-item label="优选专区名称" prop="name">
            <el-input
              v-model="queryParams.name"
              placeholder="请输入优选专区名称"
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
              v-hasPermi="['mall:preferenceArea:add']"
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
              v-hasPermi="['mall:preferenceArea:edit']"
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
              v-hasPermi="['mall:preferenceArea:remove']"
            >删除
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </el-header>

      <el-main style="height:100%;padding:0;">
        <el-table ref="table" style="width: 100%;" height="100%" v-loading="loading" :data="preferenceAreaList"
                  @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="优选专区名称" align="center" prop="name"/>
          <el-table-column label="副标题" align="center" prop="subTitle"/>
          <el-table-column label="展示图片" align="center" prop="pic">
            <template slot-scope="scope">
              <el-image style="width: 30px;height: 30px;"
                        :src="scope.row.pic"
                        :preview-src-list="scope.row.pic?scope.row.pic.split(','):[]">
                <div slot="error" class="el-image__error" style="background: none">
                  <i class="el-icon-picture-outline"></i>
                </div>
              </el-image>
            </template>
          </el-table-column>
          <el-table-column label="显示状态" align="center" prop="showStatus">
            <template slot-scope="scope">
              <el-switch
                :active-value="1"
                :inactive-value="0"
                v-model="scope.row.showStatus"
                @change="handleStatusChange(scope.row,'show')">
              </el-switch>
            </template>
          </el-table-column>
          <el-table-column label="排序" align="center" prop="sort"/>
          <el-table-column label="产品数量" align="center" prop="productCount"/>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['mall:preferenceArea:edit']"
              >修改
              </el-button>
              <el-button
                style="color: #ff4949"
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['mall:preferenceArea:remove']"
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

    <!-- 添加或修改优选专区信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="50%" append-to-body :close-on-click-modal="false">
      <el-form ref="form" v-if="open" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="优选专区名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入优选专区名称"/>
        </el-form-item>
        <el-form-item label="副标题" prop="subTitle">
          <el-input v-model="form.subTitle" placeholder="请输入副标题"/>
        </el-form-item>
        <el-form-item label="展示图片" prop="pic">
          <common-upload :limit="1" v-model="form.pic"></common-upload>
        </el-form-item>
        <el-form-item label="显示状态">
          <el-radio-group v-model="form.showStatus">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序"/>
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
import {
  addPreferenceArea,
  delPreferenceArea,
  exportPreferenceArea,
  getPreferenceArea,
  listPreferenceArea,
  updatePreferenceArea
} from "@/api/mall/preferenceArea";

export default {
  name: "PreferenceArea",
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
      // 优选专区信息表格数据
      preferenceAreaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          {required: true, message: '请输入优选专区名称', trigger: 'blur'},
          {min: 2, max: 140, message: '长度在 2 到 140 个字符', trigger: 'blur'}
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
  },
  methods: {
    /** 查询优选专区信息列表 */
    getList() {
      this.loading = true;
      listPreferenceArea(this.queryParams).then(response => {
        this.preferenceAreaList = response.rows;
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
        name: null,
        pic: null,
        productCount: 0,
        showStatus: 0,
        sort: 0,
        subTitle: null
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
    /** 修改状态 */
    handleStatusChange(row, type) {
      let data = {
        id: row.id
      }
      if (type === 'show') data.showStatus = row.showStatus;
      updatePreferenceArea(data).then(response => {
        this.$modal.msgSuccess("修改成功");
      }).catch(error => {
        if (type === 'show') row.showStatus = row.showStatus === 0 ? 1 : 0
      })
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
      this.title = "添加优选专区信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPreferenceArea(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改优选专区信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePreferenceArea(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPreferenceArea(this.form).then(response => {
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
      this.$confirm('是否确认删除优选专区信息编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delPreferenceArea(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有优选专区信息数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportPreferenceArea(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });
    }
  }
};
</script>
