<template>
  <div class="container" v-if="visibleAdd">
    <div>
      <span class="_back" style="height: 50px;
    width: 40%;
    display: inline-block;
    line-height: 50px;
    margin-right: 20px;
    font-size: 20px;
    cursor: pointer"
      >
        <i class="el-icon-arrow-left" @click="cancel">返回</i>
      </span>
      <span style="height: 50px;
    width: 40%;
    display: inline-block;
    line-height: 50px;
    margin-right: 20px;
    font-size: 20px;"
      >{{ title }}</span>
    </div>
    <!-- 添加或修改供应商信息对话框 -->
    <el-form ref="form" :model="form" :rules="rules" label-width="180px">
      <el-form-item label="供应商名称" prop="supplierName">
        <el-input v-model="form.supplierName" :disabled="isDisabled" placeholder="请输入供应商名称" maxlength="64"/>
      </el-form-item>
      <el-form-item label="统一社会信用代码" prop="creditCode">
        <el-input v-model="form.creditCode" :disabled="isDisabled" placeholder="请输入统一社会信用代码" maxlength="19"/>
      </el-form-item>
      <el-form-item label="供应商注册地址" prop="address">
        <el-input v-model="form.address" :disabled="isDisabled" placeholder="请输入供应商注册地址" maxlength="128"/>
      </el-form-item>
      <el-form-item label="供应商注册资金(万元)" prop="capital">
        <el-input v-model="form.capital" :disabled="isDisabled" placeholder="请输入供应商注册资金(万元)" maxlength="11"/>
      </el-form-item>
      <el-form-item label="供应商来源" prop="source">
        <el-select v-model="form.source" :disabled="isDisabled" placeholder="请选择供应商来源"
                   @change="selectChange(form.source)">
          <el-option v-for="op in sourceOptions" :label="op.label" :value="op.value" :key="op.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="供应商级别" prop="level">
        <el-select v-model="form.level" :disabled="isDisabled" placeholder="请选择供应商级别" @change="levelChange(form.level)">
          <el-option v-for="op in levelOptions" :label="op.label" :value="op.value" :key="op.value"></el-option>
        </el-select>
      </el-form-item>

      <!-- 附件 -->
      <el-form-item label="供应商资质文件" prop="qualificationsFile">
        <el-upload ref="qualificationsFile" :file-list="file_qff" :action="fileAction" multiple
                   :headers="{'Authorization': token}"
                   :before-upload="qualificationsFile_Upload"
                   :on-success="handleAvatarSuccess1"
                   :before-remove="beforeRemove_File"
                   accept=".xls,.doc">
          <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
        </el-upload>
        <!-- 回显数据的附件 -->
        <div class="avatar_div" v-for="(fileItem, i) in filesUrl1" v-if="filesUrl1">
          <!-- 回显数据的图片 -->
          <a class="el-upload-list__item-name" :href="`/dev-api${fileItem}`">
            <i class="el-icon-document"></i>{{ fileItem.split('/').pop() }}  (点击文件下载)
          </a>
          <label class="el-upload-list__item-status-label">
            <i class="el-icon-close" @click="handleDel_qualificationsFile_File(fileItem, i)"></i>
            <i class="el-icon-upload-success el-icon-circle-check"></i>
          </label>
        </div>
      </el-form-item>
      <!-- 附件 -->

      <!-- 供应商营业执照 -->
      <el-form-item label="供应商营业执照" prop="businessLicense">
        <!-- 回显数据的图片 -->
        <div class="avatar1div" v-for="(imgItem1, i) in imageUrl2" v-if="imageUrl2">
          <img :src="`/dev-api${imgItem1}`" class="avatar">
          <span class="el-upload-list__item-delete">
            <i class="el-icon-delete" @click="handleDel_businessLicense_Img(imgItem1, i)"></i>
          </span>
        </div>
        <el-upload ref="qualificationsFile" :file-list="file00Upload" :action="fileAction" multiple
                   :headers="{'Authorization': token}"
                   :before-upload="imgFile_Upload"
                   :on-success="handleAvatarSuccess2"
                   list-type="picture-card" accept="image/*">
          <i class="el-icon-plus"></i>
          <div slot="tip" class="el-upload__tip">只能上传不超过 2MB 的image文件</div>
        </el-upload>
      </el-form-item>
      <!-- 供应商营业执照 -->

      <!-- 供应商银行开户证明 -->
      <el-form-item label="供应商银行开户证明" prop="accountOpeningCertificate">
        <div class="avatar2div" v-for="(imgItem2, i) in imageUrl3" v-if="imageUrl3">
          <!-- 回显数据的图片 -->
          <img :src="`/dev-api${imgItem2}`" class="avatar"/>
          <span class="el-upload-list__item-delete">
            <i class="el-icon-delete" @click="handleDel_accountOpeningCertificate_Img(imgItem2, i)"></i>
          </span>
        </div>
        <el-upload ref="qualificationsFile" :file-list="file00Upload" :action="fileAction" multiple
                   :headers="{'Authorization': token}"
                   :before-upload="imgFile_Upload"
                   :on-success="handleAvatarSuccess3"
                   list-type="picture-card" accept="image/*">
          <i class="el-icon-plus"></i>
          <div slot="tip" class="el-upload__tip">只能上传不超过 2MB 的image文件</div>
        </el-upload>
      </el-form-item>
      <!-- 供应商银行开户证明 -->

      <el-form-item label="公共邮箱地址" prop="publicMailbox">
        <el-input v-model="form.publicMailbox" :disabled="isDisabled" placeholder="请输入公共邮箱地址" maxlength="64"/>
      </el-form-item>

      <el-form-item label="供应商联系人" prop="phone1">
        <el-input v-model="form.name" :disabled="isDisabled" placeholder="请输入供应商联系人手机号" maxlength="64"/>
      </el-form-item>

      <el-form-item label="供应商联系人手机号" prop="phone">
        <el-input v-model="form.phone" :disabled="isDisabled" placeholder="请输入供应商联系人手机号" maxlength="11"/>
      </el-form-item>

      <el-form-item label="供应商联系邮箱" prop="email">
        <el-input v-model="form.email" :disabled="isDisabled" placeholder="请输入供应商联系邮箱" maxlength="64"/>
      </el-form-item>

      <el-form-item label="框架协议起始日期" prop="startDate">
        <el-date-picker clearable size="small" style="width: 200px" :disabled="isDisabled"
                        v-model="form.startDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择框架协议起始日期"
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item label="框架协议终止日期" prop="endDate">
        <el-date-picker clearable size="small" style="width: 200px" :disabled="isDisabled"
                        v-model="form.endDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择框架协议终止日期"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="form.remark" :disabled="isDisabled" type="textarea" placeholder="请输入内容"/>
      </el-form-item>
    </el-form>

    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm" v-if="!isDisabled">提 交</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </div>
</template>

<script>
import {addSupplier, updateSupplier, upload} from '@/api/system/supplier'
import {getToken} from '@/utils/auth'

export default {
  name: 'Supplier',
  props: {
    visibleAdd: {
      type: Boolean,
      default: false
    },
    getList: {
      type: Function,
      default: null
    },
    title: {
      type: String,
      default: null
    },
    echoFormData: {
      type: Object,
      default: {},
    },
    isDisabled: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      disabled: false,
      fileList1: [],
      fileList2: [],
      fileList3: [],
      file00Upload: [],
      file_qff: [],
      token: '',
      fileAction: '/dev-api/common/upload',

      dialogImageUrl: '',
      dialogVisible: false,

      sourceOptions: [{
        value: '1',
        label: '招投标'
      }, {
        value: '2',
        label: '内部推荐'
      }, {
        value: '3',
        label: '其他'
      }],
      levelOptions: [{
        value: 'A',
        label: 'A'
      }, {
        value: 'B',
        label: 'B'
      }, {
        value: 'C',
        label: 'C'
      }, {
        value: 'D',
        label: 'D'
      }],
      // 遮罩层
      loading: true,
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
      // 供应商信息表格数据
      supplierList: [],

      // 表单参数
      form: {},
      // 表单校验
      rules: {
        supplierName: [
          {required: true, message: '供应商名称不能为空', trigger: 'blur'}
        ],
        creditCode: [
          {required: true, message: '统一社会信用代码不能为空', trigger: 'blur'},
        ],
        address: [
          {required: true, message: '供应商注册地址不能为空', trigger: 'blur'}
        ],
        capital: [
          {required: true, message: '供应商注册资金不能为空', trigger: 'blur'},
          {
            validator: (rule, value, callback) => {
              const pattern = /^(-)?(0|[1-9]\d*)(\s|$|\.\d{1,2}\b)/;
              const pattern2 = /^[0-9]*$/;

              if (value.length > 8 && pattern2.test(value)) {
                callback(new Error('请输入数字，最大长度为10位数字，小数点后两位小数'))
              }
              if (!pattern.test(value)) {
                callback(new Error('请输入数字，最大长度为10位数字，小数点后两位小数'))
              } else {
                callback()
              }
            },
            trigger: 'blur'
          }
        ],
        source: [
          {required: true, message: '供应商来源不能为空', trigger: 'blur'}
        ],
        level: [
          {required: true, message: '供应商级别不能为空', trigger: 'blur'}
        ],
        publicMailbox: [
          {required: true, message: '公共邮箱不能为空', trigger: 'blur'},
          {
            validator: (rule, value, callback) => {
              const pattern = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
              if (!pattern.test(value)) {
                callback(new Error('请输入正确的邮箱地址，以@**.com结束'))
              } else {
                callback()
              }
            },
            trigger: 'blur'
          }
        ],
        name: [
          {required: true, message: '供应商联系人', trigger: 'blur'}
        ],
        phone: [
          {required: true, message: '供应商联系人手机号不能为空', trigger: 'blur'},
          {
            validator: (rule, value, callback) => {
              const pattern = /^[0-9]*$/;
              if (!pattern.test(value)) {
                callback(new Error('请输入11位数字'))
              } else {
                callback()
              }
            },
            trigger: 'blur'
          }
        ],
        email: [
          {required: true, message: '供应商联系邮箱不能为空', trigger: 'blur'},
          {
            validator: (rule, value, callback) => {
              const pattern = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
              if (!pattern.test(value)) {
                callback(new Error('请输入正确的邮箱地址，以@**.com结束'))
              } else {
                callback()
              }
            },
            trigger: 'blur'
          }
        ],
        startDate: [
          {required: true, message: '框架协议起始日期不能为空', trigger: 'blur'}
        ],
        endDate: [
          {required: true, message: '框架协议终止日期不能为空', trigger: 'blur'}
        ]
      },
      file1: [],
      file2: [],
      file3: [],
      echoFile1: '',
      echoFile2: '',
      echoFile3: ''
    }
  },
  created() {
    this.token = getToken();
    if (this.title === '修改供应商信息' || this.title === '查看供应商信息') {
      this.form = this.echoFormData;
      this.echoFile1 = this.form.qualificationsFile;
      this.echoFile2 = this.form.businessLicense;
      this.echoFile3 = this.form.accountOpeningCertificate;
    }
  },
  computed: {
    filesUrl1() {
      if (this.echoFile1) {
        let files0 = this.echoFile1.split(',')
        return files0
      }
    },
    imageUrl2() {
      if (this.echoFile2) {
        let img1 = this.echoFile2.split(',')
        return img1
      }
    },
    imageUrl3() {
      if (this.echoFile3) {
        let img2 = this.echoFile3.split(',')
        return img2
      }
    }
  },
  methods: {
    // 删除附件的钩子
    beforeRemove_File(file, fileList) {
      if (this.file1.length) {
        this.file1.forEach((item, i) => {
          if (item.includes(file.name)) {
            this.file1.splice(i,1)
          }
        })
      }
    },

    // 删除附件
    handleDel_qualificationsFile_File(val, i) {
      let del_file0 = this.form.qualificationsFile.split(',')
      del_file0.splice(del_file0.findIndex(item => item === val), 1)
      this.form.qualificationsFile = del_file0.toString()
      this.echoFile1 = del_file0.toString()
    },

    // 删除图片
    handleDel_businessLicense_Img(val, i) {
      let del_files1 = this.form.businessLicense.split(',')
      del_files1.splice(del_files1.findIndex(item => item === val), 1)
      this.form.businessLicense = del_files1.toString()
      this.echoFile2 = del_files1.toString()
    },
    handleDel_accountOpeningCertificate_Img(val, i) {
      let del_files2 = this.form.accountOpeningCertificate.split(',')
      del_files2.splice(del_files2.findIndex(item => item === val), 1)
      this.form.accountOpeningCertificate = del_files2.toString()
      this.echoFile3 = del_files2.toString()
    },


    // 上传图片
    imgFile_Upload(file) {
      let isRightSize = file.size / 1024 / 1024 < 2
      if (!isRightSize) {
        this.$message.error('文件大小超过 2MB')
      }
      let isImg = new RegExp('image/*').test(file.type);
      if (!isImg) {
        this.$message.error('应该选择图片类型的文件')
      } else {
        return isRightSize && isImg
      }
    },

    // 上传附件
    qualificationsFile_Upload(file) {
      if (file.type === 'application/msword' || file.type === 'application/vnd.ms-excel') {
      } else {
        this.$message.error('应该选择文档类型的文件')
      }
    },

    // 上传成功之后
    handleAvatarSuccess1(res) {
      let splitUrl = res.url.split('/');
      splitUrl.pop()
      splitUrl.push(res.fileName)
      // this.file1.push(res.url)
      this.file1.push(splitUrl.toString().replaceAll(',', '/'))
    },

    // 上传图片成功之后
    handleAvatarSuccess2(res) {
      this.file2.push(res.url)
    },
    handleAvatarSuccess3(res) {
      this.file3.push(res.url)
    },

    /** 提交按钮 */
    submitForm() {

      this.$refs['form'].validate(valid => {
        if (valid) {
          // 给附件赋值
          if (this.file1.length) {
            if (this.form.qualificationsFile) {
              this.form.qualificationsFile = `${this.form.qualificationsFile},${this.file1.toString()}`;
            } else {
              this.form.qualificationsFile = this.file1.toString();
            }
          }

          if (this.file2.length) {
            if (this.form.businessLicense) {
              this.form.businessLicense = `${this.form.businessLicense},${this.file2.toString()}`;
            } else {
              this.form.businessLicense = this.file2.toString();
            }
          }

          if (this.file3.length) {
            if (this.form.accountOpeningCertificate) {
              this.form.accountOpeningCertificate = `${this.form.accountOpeningCertificate},${this.file3.toString()}`;
            } else {
              this.form.accountOpeningCertificate = this.file3.toString();
            }
          }

          if (this.form.id != null) {
            updateSupplier(this.form).then(response => {
              this.msgSuccess('修改成功')
              this.cancel()
            })
          } else {
            this.form.status = '启动'
            addSupplier(this.form).then(response => {
              this.msgSuccess('新增成功')
              this.cancel()
            })
          }
        }
      })
    },

    selectChange() {
    },
    levelChange() {
    },

    // 取消按钮
    cancel() {
      this.$emit('update:visibleAdd', false)
      this.getList()
      this.reset()
    },

    // 表单重置
    reset() {
      this.form = {}
      this.resetForm('form')
    },

    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },


  }
}
</script>
<style lang="scss" scoped>
.avatar {
  width: 150px;
  height: 150px;
  display: block;
}

.avatar_div {
  position: relative;

  /deep/ .el-upload-list__item-name {
    cursor: pointer;
  }

  /deep/ .el-upload-list__item-status-label {
    display: block;
  }

  /deep/ .el-icon-circle-check {
    color: #13ce66;
  }

  /deep/ .el-icon-close {
    line-height: 35px;
    display: none;
    color: #a0a1a2;
  }

  &:hover {
    color: #1890ff;
    background-color: #F5F7FA;

    /deep/ .el-icon-close {
      display: block;
    }

    /deep/ .el-icon-circle-check {
      display: none;
    }
  }
}

.avatar1div {
  position: relative;
  width: 150px;
  height: 150px;
  float: left;
  margin-right: 5px;

  &:hover {
    /deep/ .el-upload-list__item-delete {
      display: block;
    }
  }

  /deep/ .el-upload-list__item-delete {
    position: absolute;
    top: 0;
    font-size: 16px;
    width: 100%;
    color: #ffffff;
    line-height: 150px;
    text-align: center;
    display: none;
    background-color: rgba(0, 0, 0, 0.5);
    border-radius: 4px;
    right: 0;
  }
}

.avatar2div {
  width: 150px;
  float: left;
  height: 150px;
  position: relative;
  margin-right: 5px;

  &:hover {
    /deep/ .el-upload-list__item-delete {
      display: block;
    }
  }

  /deep/ .el-upload-list__item-delete {
    position: absolute;
    top: 0;
    font-size: 16px;
    width: 100%;
    line-height: 150px;
    text-align: center;
    color: #ffffff;
    display: none;
    background-color: rgba(0, 0, 0, 0.5);
    border-radius: 4px;
    right: 0;
  }
}

/deep/ .el-select--medium {
  width: 100% !important;
}

/deep/ .el-date-editor--date {
  width: 100% !important;
}

/deep/ .dialog-footer {
  text-align: center;
}


</style>
