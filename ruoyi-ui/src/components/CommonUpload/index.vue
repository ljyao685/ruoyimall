<template>
  <div class="component-upload-common">
    <el-upload
      :action="uploadUrl"
      :data="dataObj"
      :list-type="prefixDir==='image'?'picture-card':'text'"
      :limit="limit"
      name="file"
      :show-file-list="prefixDir==='image'"
      :file-list="fileList"
      :accept="fileType.join(',')"
      :before-upload="handleBeforeUpload"
      :on-success="handleUploadSuccess"
      :on-error="handleUploadError"
      :on-remove="handleRemove"
      :on-preview="handlePreview"
      :on-exceed="handleExceed"
      :class="{hide: this.fileList.length >= this.limit}"
    >
      <i class="el-icon-plus" v-if="prefixDir==='image'"></i>
      <el-button v-if="prefixDir!='image'" size="small" type="primary">点击上传</el-button>
    </el-upload>

    <!-- 上传提示 -->
    <div class="el-upload__tip" slot="tip" v-if="showTip">
      请上传
      <template v-if="fileSize"> 大小不超过 <b style="color: #f56c6c">{{ fileSize }}MB</b></template>
      <template v-if="fileType"> 格式为 <b style="color: #f56c6c">{{ fileType.join("/") }}</b></template>
      的文件
    </div>

    <el-dialog
      :visible.sync="dialogVisible"
      title="预览"
      width="800"
      append-to-body
    >
      <img
        v-if="dialogVisible&&prefixDir==='image'"
        :src="fileList[0].url"
        style="display: block; max-width: 100%; margin: 0 auto"
      />
    </el-dialog>
  </div>
</template>

<script>
import {deleteOss, policy} from '@/api/oss/index'

export default {
  props: {
    value: [String, Object, Array],
    // 路径前缀
    prefixDir: {
      type: String,
      default: 'image'
    },
    // 图片数量限制
    limit: {
      type: Number,
      default: 5,
    },
    // 大小限制(MB)
    fileSize: {
      type: Number,
      default: 5,
    },
    // 文件类型, 例如['png', 'jpg', 'jpeg']
    fileType: {
      type: Array,
      default: () => ["png", "jpg", "jpeg"],
    },
    // 是否显示提示
    isShowTip: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      uploadUrl: "https://mall-oss-web.oss-cn-chengdu.aliyuncs.com",
      dataObj: {
        policy: '',
        signature: '',
        key: '',
        ossaccessKeyId: '',
        dir: '',
        host: '',
      },
      dialogVisible: false,
      fileList: []
    };
  },
  watch: {
    value: {
      handler(val) {
        if (val) {
          // 首先将值转为数组
          const list = Array.isArray(val) ? val : this.value.split(',');
          // 然后将数组转为对象数组
          this.fileList = list.map(item => {
            if (typeof item === "string") {
              item = {url: item};
            }
            return item;
          });
        } else {
          this.fileList = [];
          return [];
        }
      },
      deep: true,
      immediate: true
    }
  },
  computed: {
    // 是否显示提示
    showTip() {
      return this.isShowTip && (this.fileType || this.fileSize) && this.fileList.length !== this.limit;
    },
  },
  methods: {
    // 返回
    emitInput(fileList) {
      let newList = fileList.map(item => {
        return item.url
      })
      this.$emit("input", newList.join(','));
    },
    // 删除
    handleRemove(file, fileList) {
      deleteOss({fileKey: this.dataObj.key}).then(response => {
        if (response.code === 200) {
          this.emitInput(fileList);
        } else {
          this.$modal.msgError(response.msg);
        }
      })
    },
    // 上传前loading加载
    handleBeforeUpload(file) {
      let _self = this;
      if (this.fileSize) {
        const isLt = file.size / 1024 / 1024 < this.fileSize;
        if (!isLt) {
          this.$modal.msgError(`上传文件大小不能超过 ${this.fileSize} MB!`);
          return false;
        }
      }
      return new Promise((resolve, reject) => {
        policy({dirType: _self.prefixDir}).then(response => {
          _self.dataObj.policy = response.data.policy;
          _self.dataObj.signature = response.data.signature;
          _self.dataObj.ossaccessKeyId = response.data.accessKeyId;
          _self.dataObj.key = response.data.dir + '/' + file.name;
          _self.dataObj.dir = response.data.dir;
          _self.dataObj.host = response.data.host;
          resolve(true)
        }).catch(err => {
          console.log(err)
          reject(false)
        })
      })
    },
    // 文件个数超出
    handleExceed() {
      this.$modal.msgError(`上传文件数量不能超过 ${this.limit} 个!`);
    },
    // 预览
    handlePreview(file) {
      this.dialogVisible = true;
    },
    // 上传成功回调
    handleUploadSuccess(res, file) {
      this.fileList.push({name: file.name, url: this.dataObj.host + '/' + this.dataObj.dir + '/' + file.name});
      this.emitInput(this.fileList);
    },
    // 上传失败
    handleUploadError() {
      this.$modal.msgError("上传失败");
    },
  }
};
</script>
<style scoped lang="scss">
// .el-upload--picture-card 控制加号部分
::v-deep.hide .el-upload--picture-card {
  display: none;
}

// 去掉动画效果
::v-deep .el-list-enter-active,
::v-deep .el-list-leave-active {
  transition: all 0s;
}

::v-deep .el-list-enter, .el-list-leave-active {
  opacity: 0;
  transform: translateY(0);
}
</style>

