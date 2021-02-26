<template>
  <div>
    <!-- 调用组件 -->
    <cz-from labelWidth="170px"
             ref="form"
             @sublime="addUser"
             :formData="formData"
             :fields="fields"
             :buttons="buttons"
             :inline="true"
    />
  </div>
</template>

<script>
import czFrom from '@/components/CaiZhi-Form'

export default {
  components: {
    czFrom
  },
  data() {

    let entrustProps = { label: 'label', value: 'value' }

    let checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('手机号不能为空'))
      } else {
        const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
        if (reg.test(value)) {
          callback()
        } else {
          return callback(new Error('请输入正确的手机号'))
        }
      }
    }
    return {
      // 如果数据要回显后不可编辑，使用JSON.parse(JSON.stringify())转换
      formData: {},
      buttons: [
        { label: '取消', icon: '', size: 'medium ', handle: () => this.resetQuery() },
        { label: '提交', icon: '', size: 'medium ', type: 'primary', handle: () => this.handleQuery() }
      ],
      fields: [
        {
          type: 'input',
          label: '供应商名称',
          prop: 'supplierName',
          placeholder: '请输入供应商名称',
          rules: [{ required: true, message: '请输入供应商名称', trigger: 'blur' }]
        },
        {
          type: 'input',
          label: '统一社会信用代码',
          prop: 'creditCode',
          placeholder: '请输入统一社会信用代码',
          rules: [{ required: true, message: '请输入统一社会信用代码', trigger: 'blur' }]
        },
        {
          type: 'input',
          label: '供应商注册地址',
          prop: 'address',
          placeholder: '请输入供应商注册地址',
          rules: [{ required: true, message: '请输入供应商注册地址', trigger: 'blur' }]
        },
        {
          type: 'input',
          label: '供应商资金(万元)',
          prop: 'capital',
          placeholder: '请输入供应商资金(万元)',
          rules: [{ required: true, message: '请输入供应商资金(万元)', trigger: 'blur' }]
        },
        {
          type: 'select',
          label: '供应商来源',
          prop: 'source',
          options: [{
            value: '1',
            label: '招投标'
          },{
            value: '2',
            label: '内部推荐'
          },{
            value: '3',
            label: '其他'
          }],
          change: row => {
            this.changeCompany(row)
          },
          placeholder: '请选择供应商来源',
          rules: [{ required: true, message: '请选择供应商来源', trigger: 'change' }]
        },
        {
          type: 'select',
          label: '供应商级别',
          prop: 'level',
          options: [{
            value: 'A',
            label: 'A'
          },{
            value: 'B',
            label: 'B'
          },{
            value: 'C',
            label: 'C'
          },{
            value: 'D',
            label: 'D'
          }],
          change: row => {
            this.changeCompany(row)
          },
          placeholder: '请选择供应商级别',
          rules: [{ required: true, message: '请选择供应商级别', trigger: 'change' }]
        },
        {
          type: 'file',
          label: '供应商资质文件',
          rules: [{ required: true, message: '请选择所属公司', trigger: 'change' }]
        },
        {
          type: 'file',
          label: '供应商营业执照',
          fileList: [],
          fileAction: 'https://jsonplaceholder.typicode.com/posts/',
          fileBeforeUpload: [],
          rules: [{ required: true, message: '请选择所属公司', trigger: 'change' }]
        },
        {
          type: 'select',
          label: '供应商银行开户证明',
          rules: [{ required: true, message: '请选择所属公司', trigger: 'change' }]
        },
        {
          type: 'input',
          label: '公共邮箱地址',
          prop: 'name',
          placeholder: '请输入公共邮箱地址',
          // 表单验证
          rules: [{ required: true, message: '请输入公共邮箱地址', trigger: 'blur' }]
        },
        {
          type: 'input',
          label: '供应商联系人',
          prop: 'supplierName2',
          placeholder: '请输入供应商联系人',
          // 表单验证
          rules: [{ required: true, message: '请输入供应商联系人', trigger: 'blur' }]
        },
        {
          type: 'input',
          label: '供应商联系人手机号码',
          prop: 'phone',
          placeholder: '请输入供应商联系人手机号码',
          // 表单验证
          rules: [{ required: true, validator: checkPhone, message: '请输入供应商联系人手机号码', trigger: 'blur' }]
        },
        {
          type: 'date',
          label: '框架协议起始日期',
          prop: 'startDate',
          placeholder: '请选择框架协议起始日期',
          // 表单验证
          rules: [{ required: true, message: '请选择框架协议起始日期', trigger: 'blur' }]
        },
        {
          type: 'date',
          label: '框架协议终止日期',
          prop: 'endDate',
          placeholder: '请选择框架协议终止日期',
          // 表单验证
          rules: [{ required: true, message: '请选择框架协议终止日期', trigger: 'blur' }]
        },
        {
          type: 'input',
          label: '供应商联系邮箱',
          prop: 'email',
          placeholder: '请输入供应商联系邮箱',
          // 表单验证
          rules: [{ required: true, message: '请输入供应商联系邮箱', trigger: 'blur' }]
        },
        {
          type: 'textarea',
          label: '备注',
          prop: 'remark',
          placeholder: '请输入备注',
          // 表单验证
          rules: [{ required: true, message: '请输入备注', trigger: 'blur' }]
        }

      ],
      ruleForm: {}
    }
  },
  methods: {
    // 取消操作
    cancel() {
      //重置表单
      this.$refs.form.reset()
      this.$message('取消操作')
    },
    // 提交操作
    notify() {
      this.$refs.form.sublime()
    },
    // 返回提交结果
    addUser(valid) {
      if (valid) {
        this.$message.success('提交成功！')
        // 重置表单,reset为子组件的方法
        this.$refs.form.reset()
      } else {
        return false
      }
    },
    // 下拉选择分公司，查询部门数据
    changeCompany(row) {
    },
    // 下拉选择部门，查询职位数据
    changeDepar(row) {
    },
    // 职位下拉事件
    changePosition(row) {
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
