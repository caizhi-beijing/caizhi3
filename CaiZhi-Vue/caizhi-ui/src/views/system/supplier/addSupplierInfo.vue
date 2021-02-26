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
        <i class="el-icon-arrow-left" @click="cancel" >返回</i>
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
        <el-select v-model="form.source" :disabled="isDisabled" placeholder="请选择供应商来源" @change="selectChange(form.source)">
          <el-option v-for="op in sourceOptions" :label="op.label" :value="op.value" :key="op.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="供应商级别" prop="level">
        <el-select v-model="form.level" :disabled="isDisabled" placeholder="请选择供应商级别" @change="levelChange(form.level)">
          <el-option v-for="op in levelOptions" :label="op.label" :value="op.value" :key="op.value"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="供应商资质文件" prop="qualificationsFile">
        <el-upload
          action="#"
          list-type="picture-card"
          :on-success="handleSuccess"
          :file-list="fileList1"
          :on-change="
                  (file, files) => {
                    return getFile(file, files,1, 'qualificationsFile')
                  }
                "
          :auto-upload="false"
        >
          <i slot="default" class="el-icon-plus"></i>
          <div slot="file" slot-scope="{file}">
            <img
              class="el-upload-list__item-thumbnail"
              :src="file.url" alt=""
            >
            <span class="el-upload-list__item-actions">
        <span
          class="el-upload-list__item-preview"
          @click="handlePictureCardPreview(file)"
        >
          <i class="el-icon-zoom-in"></i>
        </span>
        <span
          v-if="!disabled"
          class="el-upload-list__item-delete"
          @click="handleRemove(file)"
        >
          <i class="el-icon-delete"></i>
        </span>
      </span>
          </div>
        </el-upload>
        <!-- 放大图片 -->
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
      </el-form-item>


      <el-form-item label="供应商营业执照" prop="businessLicense">
        <el-upload
          action="#"
          list-type="picture-card"
          :on-success="handleSuccess"
          :file-list="fileList2"
          :on-change="
                  (file, files) => {
                    return getFile(file,files, 2,'businessLicense')
                  }
                "
          :auto-upload="false"
        >
          <i slot="default" class="el-icon-plus"></i>
          <div slot="file" slot-scope="{file}">
            <img
              class="el-upload-list__item-thumbnail"
              :src="file.url" alt=""
            >
            <span class="el-upload-list__item-actions">
        <span
          class="el-upload-list__item-preview"
          @click="handlePictureCardPreview(file)"
        >
          <i class="el-icon-zoom-in"></i>
        </span>
        <span
          v-if="!disabled"
          class="el-upload-list__item-delete"
          @click="handleRemove(file)"
        >
          <i class="el-icon-delete"></i>
        </span>
      </span>
          </div>
        </el-upload>
        <!-- 放大图片 -->
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
      </el-form-item>


      <el-form-item label="供应商银行开户证明" prop="accountOpeningCertificate">
        <el-upload
          action="#"
          list-type="picture-card"
          :on-success="handleSuccess"
          :file-list="fileList3"
          :on-change="
                  (file, files) => {
                    return getFile(file,files, 3,'accountOpeningCertificate')
                  }
                "
          :auto-upload="false"
        >
          <i slot="default" class="el-icon-plus"></i>
          <div slot="file" slot-scope="{file}">
            <img
              class="el-upload-list__item-thumbnail"
              :src="file.url" alt=""
            >
            <span class="el-upload-list__item-actions">
        <span
          class="el-upload-list__item-preview"
          @click="handlePictureCardPreview(file)"
        >
          <i class="el-icon-zoom-in"></i>
        </span>
        <span
          v-if="!disabled"
          class="el-upload-list__item-delete"
          @click="handleRemove(file)"
        >
          <i class="el-icon-delete"></i>
        </span>
      </span>
          </div>
        </el-upload>
        <!-- 放大图片 -->
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
      </el-form-item>

      <el-form-item label="公共邮箱地址" prop="publicMailbox">
        <el-input v-model="form.publicMailbox" :disabled="isDisabled" placeholder="请输入公共邮箱地址" maxlength="64"/>
      </el-form-item>

      <el-form-item label="供应商联系人" prop="phone1">
        <el-input v-model="form.phone1" :disabled="isDisabled" placeholder="请输入供应商联系人手机号" maxlength="64"/>
      </el-form-item>

      <el-form-item label="供应商联系人手机号" prop="phone">
        <el-input v-model="form.phone" :disabled="isDisabled" placeholder="请输入供应商联系人手机号" maxlength="11"/>
      </el-form-item>

      <el-form-item label="供应商联系邮箱" prop="email">
        <el-input v-model="form.email" :disabled="isDisabled" placeholder="请输入供应商联系邮箱" maxlength="64"/>
      </el-form-item>

      <el-form-item label="框架协议起始日期" prop="startDate">
        <el-date-picker clearable size="small" style="width: 200px"  :disabled="isDisabled"
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
import { addSupplier, updateSupplier, upload } from '@/api/system/supplier'

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
    formData:{
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
          { required: true, message: '供应商名称不能为空', trigger: 'blur' }
        ],
        creditCode: [
          { required: true, message: '统一社会信用代码不能为空', trigger: 'blur' },
          {
            validator: (rule, value, callback) => {
              const pattern = /^[0-9]*$/;
              if(!pattern.test(value)){
                callback(new Error('请输入19位数字'))
              }else {
                callback()
              }
            },
            trigger: 'blur'
          }
        ],
        address: [
          { required: true, message: '供应商注册地址不能为空', trigger: 'blur' }
        ],
        capital: [
          { required: true, message: '供应商注册资金不能为空', trigger: 'blur' },
          {
            validator: (rule, value, callback) => {
              const pattern = /^(-)?(0|[1-9]\d*)(\s|$|\.\d{1,2}\b)/;
              const pattern2 = /^[0-9]*$/;

              if(value.length > 8 && pattern2.test(value)){
                callback(new Error('请输入数字，最大长度为10位数字，小数点后两位小数'))
              }
              if(!pattern.test(value)){
                callback(new Error('请输入数字，最大长度为10位数字，小数点后两位小数'))
              }else {
                callback()
              }
            },
            trigger: 'blur'
          }
        ],
        source: [
          { required: true, message: '供应商来源不能为空', trigger: 'blur' }
        ],
        level: [
          { required: true, message: '供应商级别不能为空', trigger: 'blur' }
        ],
        publicMailbox: [
          { required: true, message: '公共邮箱不能为空', trigger: 'blur' },
          {
            validator: (rule, value, callback) => {
              const pattern = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
              if(!pattern.test(value)){
                callback(new Error('请输入正确的邮箱地址，以@**.com结束'))
              }else {
                callback()
              }
            },
            trigger: 'blur'
          }
        ],
        phone1: [
          { required: true, message: '供应商联系人', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '供应商联系人手机号不能为空', trigger: 'blur' },
          {
            validator: (rule, value, callback) => {
              const pattern = /^[0-9]*$/;
              if(!pattern.test(value)){
                callback(new Error('请输入11位数字'))
              }else {
                callback()
              }
            },
            trigger: 'blur'
          }
        ],
        email: [
          { required: true, message: '供应商联系邮箱不能为空', trigger: 'blur' },
          {
            validator: (rule, value, callback) => {
              const pattern = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
              if(!pattern.test(value)){
                callback(new Error('请输入正确的邮箱地址，以@**.com结束'))
              }else {
                callback()
              }
            },
            trigger: 'blur'
          }
        ],
        startDate: [
          { required: true, message: '框架协议起始日期不能为空', trigger: 'blur' }
        ],
        endDate: [
          { required: true, message: '框架协议终止日期不能为空', trigger: 'blur' }
        ]
      },
      tempUrl: '',
      imageUrl: ''
    }
  },
  created() {
    if(this.title === '修改供应商信息'){
      this.form = this.formData;
      console.log(this.formData,' this.formData ')
    }
  },
  methods: {

    // 手动上传图片附件
    getBase64(file) {
      return new Promise(function(resolve, reject) {
        let reader = new FileReader()
        let imgResult = ''
        reader.readAsDataURL(file)
        reader.onload = function() {
          imgResult = reader.result
        }
        reader.onerror = function(error) {
          reject(error)
        }
        reader.onloadend = function() {
          resolve(imgResult)
        }
      })
    },

    getFile(file, files, flag, paras) {
      if (file && file.raw) {
        this.getBase64(file.raw).then(res => {
          // 图片内容过大，需要限制
          if (this._fileSizeValidata(file)) {
            this.form[paras] = res
            this.$refs.form.clearValidate(paras)
            if(flag === 1){
              this.fileList1.push({
                url: this.form[paras]
              })
            }
            if(flag === 2){
              this.fileList2.push({
                url: this.form[paras]
              })
            }
            if(flag === 3){
              this.fileList3.push({
                url: this.form[paras]
              })
            }
          }
        })
      }
    },

    _fileSizeValidata(file) {
      const testmsg = file.name.substring(file.name.lastIndexOf('.') + 1)
      if (!['png', 'jpg', 'jpeg', 'bmp', 'svg'].includes(testmsg)) {
        this.$message.warning('上传文件格式不符合!')
        // 移除图片
        this.$refs.upload.clearFiles()
        return false
      }
      const isLt = file.size / 1024 / 1024 < 1
      if (!isLt) {
        this.$message.warning('上传头像图片大小不能超过 1MB!')
        // 移除图片
        this.$refs.upload.clearFiles()
        return false
      }
      return isLt
    },

    handleSuccess(response, file, fileList) {

    },

    selectChange() {
    },
    levelChange() {
    },

    handleRemove(file,flag) {
      console.log(file)
      if(flag ===1 ){
        this.fileList1 = []
      }
      if(flag ===2 ){
        this.fileList2 = []
      }
      if(flag ===3 ){
        this.fileList3 = []
      }

    },
    handleChange(file) {
      this.tempUrl = URL.createObjectURL(file.raw)
    },

    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
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

    /** 提交按钮 */
    submitForm() {
      if(this.fileList1.length){

        upload(this.fileList1).then(res => {
          console.log(res)
          this.form.qualificationsFile = res.data
        })
      }
      if(this.fileList2.length){
        upload(this.fileList2).then(res => {
          console.log(res)
          this.form.businessLicense = res.data
        })
      }
      if(this.fileList3.length){
        upload(this.fileList3).then(res => {
          console.log(res)
          this.form.accountOpeningCertificate = res.data
        })
      }
      this.$refs['form'].validate(valid => {
        if (valid) {
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
    }

  }
}
</script>
<style lang="scss" scoped>
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
