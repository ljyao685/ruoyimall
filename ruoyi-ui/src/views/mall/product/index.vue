<template>
  <div :style="{height: mainHeight}">
    <el-container style="height:100%;">
      <el-header style="height:auto;padding:10px;">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
          <el-form-item label="商品名称" prop="name">
            <el-input
              v-model="queryParams.name"
              placeholder="请输入商品名称"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="商品货号" prop="productSn">
            <el-input
              v-model="queryParams.productSn"
              placeholder="请输入商品货号"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="品牌" prop="brandId">
            <el-select v-model="queryParams.brandId" clearable size="small" placeholder="请选择品牌" @change="handleQuery">
              <el-option v-for="brand in brandListOptions" :key="brand.id" :label="brand.name"
                         :value="brand.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="商品分类" prop="productCategoryId">
            <el-cascader v-model="selectProductCategoryId" size="small" clearable filterable
                         :options="prodCateListOptions" @change="handleQuery"></el-cascader>
          </el-form-item>
          <el-form-item label="上架状态" prop="publishStatus">
            <el-select v-model="queryParams.publishStatus" clearable size="small" placeholder="请选择上架状态"
                       @change="handleQuery">
              <el-option v-for="publish in publishStatusOptions" :key="publish.value" :label="publish.label"
                         :value="publish.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="审核状态" prop="verifyStatus">
            <el-select v-model="queryParams.verifyStatus" clearable size="small" placeholder="请选择审核状态"
                       @change="handleQuery">
              <el-option v-for="verify in verifyStatusOptions" :key="verify.value" :label="verify.label"
                         :value="verify.value"></el-option>
            </el-select>
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
              v-hasPermi="['mall:product:add']"
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
              v-hasPermi="['mall:product:edit']"
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
              v-hasPermi="['mall:product:remove']"
            >删除
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </el-header>

      <el-main style="height:100%;padding:0;">
        <el-table ref="table" style="width: 100%;" height="100%" v-loading="loading" :data="productList"
                  @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="编号" align="center" width="60" prop="id"/>
          <el-table-column label="商品主图" align="center" prop="picture">
            <template slot-scope="scope">
              <el-image style="width: 40px;height: 40px;"
                        :src="scope.row.picture"
                        :preview-src-list="getProductPics(scope.row)"></el-image>
            </template>
          </el-table-column>
          <el-table-column label="商品名称" align="center" prop="name">
            <template slot-scope="scope">
              <span>{{ scope.row.name }}</span><br>
              <span>品牌：{{ scope.row.brandName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="价格/货号" align="center" prop="price">
            <template slot-scope="scope">
              <span>价格：￥{{ scope.row.price }}</span><br>
              <span v-if="scope.row.productSn">货号：{{ scope.row.productSn }}</span>
            </template>
          </el-table-column>
          <el-table-column label="标签" align="center">
            <template slot-scope="scope">
              上架：
              <el-switch
                :active-value="1"
                :inactive-value="0"
                v-model="scope.row.publishStatus"
                @change="handleStatusChange(scope.row,'publish')">
              </el-switch>
              <div style="height: 5px"></div>
              新品：
              <el-switch
                :active-value="1"
                :inactive-value="0"
                v-model="scope.row.newStatus"
                @change="handleStatusChange(scope.row,'new')">
              </el-switch>
              <div style="height: 5px"></div>
              推荐：
              <el-switch
                :active-value="1"
                :inactive-value="0"
                v-model="scope.row.recommendStatus"
                @change="handleStatusChange(scope.row,'recommend')">
              </el-switch>
            </template>
          </el-table-column>
          <el-table-column label="排序" align="center" width="80" prop="sort"/>
          <el-table-column label="SKU库存" align="center" width="90" prop="sort">
            <template slot-scope="scope">
              <el-button type="primary" icon="el-icon-edit" circle
                         @click="handleUpdateProductSku(scope.row)"></el-button>
            </template>
          </el-table-column>
          <el-table-column label="销量" align="center" width="80" prop="sale"/>
          <el-table-column label="审核状态" align="center" width="100" prop="verifyStatus">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.verifyStatus===1" size="mini" type="success">审核通过</el-tag>
              <el-tooltip :content="scope.row.remark" placement="top">
                <el-tag v-if="scope.row.verifyStatus===2" size="mini" type="danger">审核不通过</el-tag>
              </el-tooltip>
              <el-dropdown v-if="scope.row.verifyStatus===0" size="mini" @command="handleCommand">
                <span style="color: #E6A23C">
                  待审核<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item style="color: #67C23A" :command="{row:scope.row,sta:1}" icon="el-icon-check">审核通过
                  </el-dropdown-item>
                  <el-dropdown-item style="color: #F56C6C" :command="{row:scope.row,sta:2}" icon="el-icon-close">审核不通过
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="150" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-view"
                @click="handleShowDetail(scope.row)"
                v-hasPermi="['mall:product:query']"
              >查看
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['mall:product:edit']"
              >修改
              </el-button>
              <el-button
                style="color: #ff4949"
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['mall:product:remove']"
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

    <!--    商品详情对话框-->
    <el-dialog title="商品详情" :visible.sync="detailVisible" width="800px" append-to-body :close-on-click-modal="false">
      <product-detail-item v-if="detailVisible" :product="productInfo"></product-detail-item>
    </el-dialog>

    <!-- 添加或修改商品信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body :close-on-click-modal="false">
      <el-card v-if="open" class="form-container" shadow="never">
        <el-steps :active="active" finish-status="success" align-center>
          <el-step title="填写商品信息"></el-step>
          <el-step title="填写商品促销"></el-step>
          <el-step title="填写商品属性"></el-step>
          <el-step title="选择商品关联"></el-step>
        </el-steps>
        <product-info-item
          v-show="showStatus[0]"
          v-model="form"
          @nextStep="nextStep">
        </product-info-item>
        <product-sale-item
          v-show="showStatus[1]"
          v-model="form"
          @prevStep="prevStep"
          @nextStep="nextStep">
        </product-sale-item>
        <product-attr-item
          v-show="showStatus[2]"
          v-model="form"
          @prevStep="prevStep"
          @nextStep="nextStep">
        </product-attr-item>
        <product-relation-item
          v-show="showStatus[3]"
          v-model="form"
          @prevStep="prevStep"
          @finishCommit="finishCommit">
        </product-relation-item>
      </el-card>
    </el-dialog>

    <!-- 修改商品sku信息对话框 -->
    <el-dialog style="margin-top: 8vh !important;" title="编辑SKU库存信息" :visible.sync="skuVisible" width="680px"
               append-to-body :close-on-click-modal="false">
      <div>
        <div class="dialog-sku-header">
          <span style="font-size: 15px;margin-right: 50px;">商品货号：<b>{{ productInfo.productSn }}</b></span>
          <el-input style="width: 280px;" v-model="skuCode" clearable size="small" placeholder="请输入sku编号搜索">
            <el-button slot="append" icon="el-icon-search" @click="getProductSkuList"></el-button>
          </el-input>
        </div>
        <el-table class="sku_table" v-if="skuVisible" :data="productSkuStockList" border>
          <el-table-column label="SKU编号" align="center" min-width="110" prop="skuCode">
            <template slot-scope="scope">
              <el-input size="small" v-model="scope.row.skuCode"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            v-for="(item,index) in productAttrList"
            :label="item.name"
            :key="item.id"
            align="center">
            <template slot-scope="scope">
              {{ getProductSkuSp(scope.row, index) }}
            </template>
          </el-table-column>
          <el-table-column
            label="销售价格"
            align="center">
            <template slot-scope="scope">
              <el-input size="small" v-model="scope.row.price"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            label="商品库存"
            align="center">
            <template slot-scope="scope">
              <el-input size="small" v-model="scope.row.stock"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            label="库存预警值"
            align="center">
            <template slot-scope="scope">
              <el-input size="small" v-model="scope.row.lowStock"></el-input>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="skuVisible=false">取 消</el-button>
        <el-button type="primary" @click="submitProductSkuForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  addProduct,
  delProduct,
  exportProduct,
  getProduct,
  listProduct,
  updateProduct,
  updateProductSku,
  updateProductStatus
} from "@/api/mall/product";
import {listBrand} from "@/api/mall/brand";
import {getProdCateCascaderList} from "@/api/mall/prodCate";
import {listProdAttr} from '@/api/mall/prodAttr'
import {listProdSkuStock} from "@/api/mall/prodSkuStock";
import ProductDetailItem from "@/views/mall/product/components/ProductDetailItem";
import ProductInfoItem from "@/views/mall/product/components/ProductInfoItem";
import ProductSaleItem from "@/views/mall/product/components/ProductSaleItem";
import ProductAttrItem from "@/views/mall/product/components/ProductAttrItem";
import ProductRelationItem from "@/views/mall/product/components/ProductRelationItem";

export default {
  name: "Product",
  components: {ProductDetailItem, ProductInfoItem, ProductSaleItem, ProductAttrItem, ProductRelationItem},
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
      // 商品信息表格数据
      productList: [],
      // 品牌列表集合
      brandListOptions: [],
      // 分类级联选择列表集合
      prodCateListOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 商品详情
      detailVisible: false,
      // 商品信息
      productInfo: {},
      // 商品分类选中的Id集合
      selectProductCategoryId: [],
      //编辑sku信息
      productSkuStockList: [],
      skuVisible: false,
      // sku编码，条件查询
      skuCode: "",
      //商品属性分类对应的属性值
      productAttrList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        brandId: null,
        name: null,
        productCategoryId: null,
        productSn: null,
        publishStatus: null,
        verifyStatus: null,
      },
      // 上架状态集合
      publishStatusOptions: [
        {label: '上架', value: 1},
        {label: '下架', value: 0}
      ],
      // 审核状态集合
      verifyStatusOptions: [
        {label: '未审核', value: 0},
        {label: '审核通过', value: 1},
        {label: '审核不通过', value: 2}
      ],
      // 商品新增修改默认步骤条
      active: 0,
      showStatus: [true, false, false, false],
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
  watch: {
    selectProductCategoryId(value) {
      if (value && value.length > 0) {
        this.queryParams.productCategoryId = value[value.length - 1];
      } else {
        this.queryParams.productCategoryId = undefined;
      }
    }
  },
  created() {
    this.getList();
    this.getBrandList();
    this.getCateCascaderList();
  },
  methods: {
    /** 查询商品信息列表 */
    getList() {
      this.loading = true;
      listProduct(this.queryParams).then(response => {
        this.productList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 查询品牌列表集合
    getBrandList() {
      listBrand().then(response => {
        this.brandListOptions = response.rows;
      })
    },
    // 查询分类级联选择列表集合
    getCateCascaderList() {
      getProdCateCascaderList().then(response => {
        this.prodCateListOptions = response.rows;
      })
    },
    // 获取商品的主图和画册图片
    getProductPics(row) {
      let pics = [];
      if (row.picture) {
        pics.push(row.picture);
      }
      if (row.albumPics) {
        let albumPics = row.albumPics.split(',');
        for (let i = 0; i < albumPics.length; i++) {
          pics.push(albumPics[i]);
        }
      }
      return pics;
    },
    // 表单重置
    reset() {
      this.active = 0;
      this.showStatus = [true, false, false, false];
      this.form = {
        id: null,
        createBy: null,
        createTime: null,
        remark: null,
        searchValue: null,
        tenantId: null,
        updateBy: null,
        updateTime: null,
        albumPics: null,
        brandId: null,
        brandName: null,
        deleteStatus: 0,
        description: null,
        detailDesc: null,
        detailHtml: null,
        detailMobileHtml: null,
        detailTitle: null,
        giftGrowth: 0,
        giftPoint: 0,
        keywords: null,
        lowStock: 0,
        name: null,
        newStatus: 1,
        originalPrice: 0,
        picture: null,
        previewStatus: 1,
        price: 0,
        productAttributeCategoryId: null,
        productCategoryId: null,
        productCategoryName: null,
        productSn: null,
        promotionEndTime: null,
        promotionPerLimit: 0,
        promotionPrice: 0,
        promotionStartTime: null,
        promotionType: 0,
        publishStatus: 1,
        recommendStatus: 1,
        sale: 0,
        serviceIds: null,
        sort: 0,
        stock: 0,
        subTitle: null,
        unit: null,
        usePointLimit: 0,
        verifyStatus: 0,
        weight: 0,
        weightTemplateId: null,
        //会员价格{memberLevelId: 0,memberPrice: 0,memberLevelName: null}
        memberPriceList: [],
        //商品满减
        productFullReductionList: [{fullPrice: 0, reducePrice: 0}],
        //商品阶梯价格
        productLadderList: [{count: 0, discount: 0, price: 0}],
        //商品属性相关{productAttributeId: 0, value: ''}
        productAttributeValueList: [],
        //商品sku库存信息{lowStock: 0, pic: '', price: 0, sale: 0, skuCode: '', spData: '', stock: 0}
        skuStockList: [],
        //商品相关专题{subjectId: 0}
        subjectProductRelationList: [],
        //商品相关优选{preferenceAreaId: 0}
        preferenceAreaProductRelationList: [],
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
    // 解析获取商品sku中的属性值
    getProductSkuSp(row, index) {
      let spData = JSON.parse(row.spData);
      if (spData != null && index < spData.length) {
        return spData[index].value;
      } else {
        return null;
      }
    },
    // 查询商品sku库存列表
    getProductSkuList() {
      listProdSkuStock({productId: this.productInfo.pid, skuCode: this.skuCode}).then(response => {
        this.productSkuStockList = response.rows;
      })
    },
    /** 修改sku信息 */
    handleUpdateProductSku(row) {
      this.productInfo = {
        pid: row.id,
        productSn: row.productSn
      };
      //查询商品属性分类对应的属性值
      listProdAttr({productAttributeCategoryId: row.productAttributeCategoryId, type: 0}).then(response => {
        this.productAttrList = response.rows;
      })
      //查询商品sku列表
      listProdSkuStock({productId: row.id, skuCode: this.skuCode}).then(response => {
        this.productSkuStockList = response.rows;
        this.skuVisible = true;
      })
    },
    /** 修改状态 */
    handleStatusChange(row, type) {
      let data = {
        id: row.id
      }
      if (type === 'publish') data.publishStatus = row.publishStatus;
      else if (type === 'new') data.newStatus = row.newStatus;
      else data.recommendStatus = row.recommendStatus;
      updateProductStatus(data).then(response => {
        this.$modal.msgSuccess("修改成功");
      }).catch(error => {
        if (type === 'publish') row.publishStatus = row.publishStatus === 0 ? 1 : 0
        else if (type === 'new') row.newStatus = row.newStatus === 0 ? 1 : 0
        else row.recommendStatus = row.recommendStatus === 0 ? 1 : 0
      })
    },
    // 上一步
    prevStep() {
      if (this.active > 0 && this.active < this.showStatus.length) {
        this.active--;
        this.hideAll();
        this.showStatus[this.active] = true;
      }
    },
    // 下一步
    nextStep() {
      if (this.active < this.showStatus.length - 1) {
        this.active++;
        this.hideAll();
        this.showStatus[this.active] = true;
      }
    },
    // 提交
    hideAll() {
      for (let i = 0; i < this.showStatus.length; i++) {
        this.showStatus[i] = false;
      }
    },
    // 审核商品
    handleCommand(command) {
      let data = {
        id: command.row.id,
        verifyStatus: command.sta
      }
      if (command.sta === 1) {
        this.$confirm('是否确认审核通过商品编号 "' + command.row.id + '" 的商品信息？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          updateProductStatus(data).then(response => {
            this.$modal.msgSuccess("审核成功");
            this.getList();
          })
        }).catch(() => {
        });
      } else {
        this.$prompt('请输入备注说明', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          inputErrorMessage: '备注说明不能为空',
          inputValidator: (value) => {
            if (!value) return false;
          }
        }).then(({value}) => {
          data.remark = value;
          updateProductStatus(data).then(response => {
            this.$modal.msgSuccess("审核成功");
            this.getList();
          })
        }).catch(() => {
        });
      }
    },
    /** 查看详情按钮 */
    handleShowDetail(row) {
      getProduct(row.id).then(response => {
        this.productInfo = response.data;
        this.detailVisible = true;
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProduct(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品";
      });
    },
    /** 提交 */
    finishCommit() {
      this.$confirm('是否要提交该产品', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.form.id) {
          updateProduct(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
        } else {
          addProduct(this.form).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.open = false;
            this.getList();
          });
        }
      })
    },
    /** 提交商品sku列表信息 */
    submitProductSkuForm() {
      if (this.productSkuStockList.length === 0) return;
      this.$confirm('是否确认修改商品SKU库存信息?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        updateProductSku(this.productSkuStockList).then(response => {
          this.$modal.msgSuccess("修改成功");
          this.skuVisible = false;
        })
      }).catch(() => {
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除商品信息编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delProduct(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有商品信息数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportProduct(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });
    }
  }
};
</script>
<style scoped>
.dialog-sku-header {
  margin-bottom: 8px;
}

.sku_table >>> .el-input__inner {
  padding: 0 8px;
  text-align: center;
}
</style>
