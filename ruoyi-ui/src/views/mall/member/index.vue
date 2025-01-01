<template>
  <div :style="{height: mainHeight}">
    <el-container style="height:100%;">
      <el-header style="height:auto;padding:10px;">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
          <el-form-item label="昵称" prop="nickname">
            <el-input
              v-model="queryParams.nickname"
              placeholder="请输入昵称"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              v-model="queryParams.password"
              placeholder="请输入密码"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="个性签名" prop="personalizedSignature">
            <el-input
              v-model="queryParams.personalizedSignature"
              placeholder="请输入个性签名"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="手机号码" prop="phone">
            <el-input
              v-model="queryParams.phone"
              placeholder="请输入手机号码"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="注册时间" prop="registerTime">
            <el-date-picker clearable size="small"
                            v-model="queryParams.registerTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择注册时间">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="用户来源" prop="sourceType">
            <el-select v-model="queryParams.sourceType" placeholder="请选择用户来源" clearable size="small">
              <el-option label="请选择字典生成" value=""/>
            </el-select>
          </el-form-item>
          <el-form-item label="帐号启用状态:0-&gt;禁用；1-&gt;启用" prop="status">
            <el-select v-model="queryParams.status" placeholder="请选择帐号启用状态:0-&gt;禁用；1-&gt;启用" clearable size="small">
              <el-option label="请选择字典生成" value=""/>
            </el-select>
          </el-form-item>
          <el-form-item label="用户名" prop="username">
            <el-input
              v-model="queryParams.username"
              placeholder="请输入用户名"
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
              v-hasPermi="['mall:member:add']"
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
              v-hasPermi="['mall:member:edit']"
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
              v-hasPermi="['mall:member:remove']"
            >删除
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              plain
              icon="el-icon-download"
              size="mini"
              :loading="exportLoading"
              @click="handleExport"
              v-hasPermi="['mall:member:export']"
            >导出
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </el-header>

      <el-main style="height:100%;padding:0;">
        <el-table ref="table" style="width: 100%;" height="100%" v-loading="loading" :data="memberList"
                  @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="id" align="center" prop="id"/>
          <el-table-column label="备注" align="center" prop="remark"/>
          <el-table-column label="搜索值" align="center" prop="searchValue"/>
          <el-table-column label="租户ID" align="center" prop="tenantId"/>
          <el-table-column label="生日" align="center" prop="birthday" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.birthday, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="所在城市" align="center" prop="city"/>
          <el-table-column label="性别：0-&gt;未知；1-&gt;男；2-&gt;女" align="center" prop="gender"/>
          <el-table-column label="成长值" align="center" prop="growth"/>
          <el-table-column label="历史积分数量" align="center" prop="historyIntegration"/>
          <el-table-column label="头像" align="center" prop="icon"/>
          <el-table-column label="积分" align="center" prop="integration"/>
          <el-table-column label="会员等级ID" align="center" prop="memberLevelId"/>
          <el-table-column label="昵称" align="center" prop="nickname"/>
          <el-table-column label="密码" align="center" prop="password"/>
          <el-table-column label="个性签名" align="center" prop="personalizedSignature"/>
          <el-table-column label="手机号码" align="center" prop="phone"/>
          <el-table-column label="注册时间" align="center" prop="registerTime" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.registerTime, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="用户来源" align="center" prop="sourceType"/>
          <el-table-column label="帐号启用状态:0-&gt;禁用；1-&gt;启用" align="center" prop="status"/>
          <el-table-column label="用户名" align="center" prop="username"/>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['mall:member:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['mall:member:remove']"
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

    <!-- 添加或修改会员信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" v-if="open" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注"/>
        </el-form-item>
        <el-form-item label="搜索值" prop="searchValue">
          <el-input v-model="form.searchValue" placeholder="请输入搜索值"/>
        </el-form-item>
        <el-form-item label="租户ID" prop="tenantId">
          <el-input v-model="form.tenantId" placeholder="请输入租户ID"/>
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker clearable size="small"
                          v-model="form.birthday"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择生日">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="所在城市" prop="city">
          <el-input v-model="form.city" placeholder="请输入所在城市"/>
        </el-form-item>
        <el-form-item label="性别：0-&gt;未知；1-&gt;男；2-&gt;女" prop="gender">
          <el-input v-model="form.gender" placeholder="请输入性别：0-&gt;未知；1-&gt;男；2-&gt;女"/>
        </el-form-item>
        <el-form-item label="成长值" prop="growth">
          <el-input v-model="form.growth" placeholder="请输入成长值"/>
        </el-form-item>
        <el-form-item label="历史积分数量" prop="historyIntegration">
          <el-input v-model="form.historyIntegration" placeholder="请输入历史积分数量"/>
        </el-form-item>
        <el-form-item label="头像" prop="icon">
          <el-input v-model="form.icon" placeholder="请输入头像"/>
        </el-form-item>
        <el-form-item label="积分" prop="integration">
          <el-input v-model="form.integration" placeholder="请输入积分"/>
        </el-form-item>
        <el-form-item label="会员等级ID" prop="memberLevelId">
          <el-input v-model="form.memberLevelId" placeholder="请输入会员等级ID"/>
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="form.nickname" placeholder="请输入昵称"/>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码"/>
        </el-form-item>
        <el-form-item label="个性签名" prop="personalizedSignature">
          <el-input v-model="form.personalizedSignature" placeholder="请输入个性签名"/>
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号码"/>
        </el-form-item>
        <el-form-item label="注册时间" prop="registerTime">
          <el-date-picker clearable size="small"
                          v-model="form.registerTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择注册时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="用户来源" prop="sourceType">
          <el-select v-model="form.sourceType" placeholder="请选择用户来源">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="帐号启用状态:0-&gt;禁用；1-&gt;启用">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名"/>
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
import {addMember, delMember, exportMember, getMember, listMember, updateMember} from "@/api/mall/member";

export default {
  name: "Member",
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
      // 会员信息表格数据
      memberList: [],
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
        birthday: null,
        city: null,
        gender: null,
        growth: null,
        historyIntegration: null,
        icon: null,
        integration: null,
        memberLevelId: null,
        nickname: null,
        password: null,
        personalizedSignature: null,
        phone: null,
        registerTime: null,
        sourceType: null,
        status: null,
        username: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
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
    /** 查询会员信息列表 */
    getList() {
      this.loading = true;
      listMember(this.queryParams).then(response => {
        this.memberList = response.rows;
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
        birthday: null,
        city: null,
        gender: null,
        growth: null,
        historyIntegration: null,
        icon: null,
        integration: null,
        memberLevelId: null,
        nickname: null,
        password: null,
        personalizedSignature: null,
        phone: null,
        registerTime: null,
        sourceType: null,
        status: 0
        ,
        username: null
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
      this.title = "添加会员信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMember(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改会员信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMember(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMember(this.form).then(response => {
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
      this.$confirm('是否确认删除会员信息编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delMember(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有会员信息数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportMember(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });
    }
  }
};
</script>
