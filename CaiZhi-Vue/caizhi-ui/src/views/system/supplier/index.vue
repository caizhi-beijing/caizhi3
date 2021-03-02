<template>
  <div class="app-container" height="100%">

    <!--  列表页  -->
    <div v-if="!visibleAdd">
      <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="140px">
        <el-form-item label="供应商名称" prop="supplierName">
          <el-input
            v-model="queryParams.supplierName"
            placeholder="请输入供应商名称"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="框架协议起始日期" prop="startDate">
          <el-date-picker clearable size="small" style="width: 200px"
                          v-model="queryParams.startDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择框架协议起始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="框架协议终止日期" prop="endDate">
          <el-date-picker clearable size="small" style="width: 200px"
                          v-model="queryParams.endDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择框架协议终止日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="供应商状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="请选择供应商状态" clearable size="small">
            <el-option label="全部" value="" />
            <el-option label="启动" value="启动" />
            <el-option label="停止" value="停止" />
          </el-select>
        </el-form-item>
        <el-form-item label="供应商联系人手机号" prop="phone">
          <el-input
            v-model="queryParams.phone"
            placeholder="请输入供应商联系人手机号"
            clearable
            size="small"
          />
        </el-form-item>

        <el-form-item>
          <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['system:supplier:add']"
          >新增
          </el-button>
        </el-col>

        <el-col :span="1.5">
          <el-button
            type="danger"
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['system:supplier:remove']"
          >删除
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:supplier:export']"
          >导出
          </el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" :data="supplierList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"/>
        <el-table-column label="ID" align="center" prop="id"/>
        <el-table-column label="供应商名称" width="100" align="center" prop="supplierName"/>
        <el-table-column label="统一社会信用代码" width="150" align="center" prop="creditCode"/>
        <el-table-column label="供应商注册地址" width="150" align="center" prop="address"/>
        <el-table-column label="供应商注册资金" width="150" align="center" prop="capital"/>
        <el-table-column label="供应商来源" width="100" align="center" prop="source" :formatter="filterSource"/>
        <el-table-column label="供应商等级" width="100" align="center" prop="level"/>
        <el-table-column label="供应商联系人手机号" width="150" align="center" prop="phone"/>
        <el-table-column label="供应商联系邮箱" width="120" align="center" prop="email"/>
        <el-table-column label="框架协议起始日期" align="center" prop="startDate" width="130">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="框架协议终止日期" align="center" prop="endDate" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="供应商状态" width="100" align="center" prop="status"/>
        <el-table-column label="备注" width="180" align="center" prop="remark"/>

        <el-table-column label="操作" width="150" align="center" fixed="right" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              @click="handleSee(scope.row)"
            >查看
            </el-button>
            <el-button
              size="mini"
              type="text"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:supplier:edit']"
            >编辑
            </el-button>
            <el-button
              size="mini"
              type="text"
              @click="handleOffOn(scope.row)"
              v-hasPermi="['system:supplier:remove']"
            >{{ scope.row.status==='启动' ? '停止' : '启动'}}
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </div>

    <!-- 添加或修改供应商信息 -->
    <add-supplier-info ref="add"
                       v-if="visibleAdd"
                       :visible-add.sync="visibleAdd"
                       :get-list="getList"
                       :title="title"
                       :echo-form-data="form"
                       :is-disabled="isDisabled"
    />

  </div>
</template>

<script>
import {
  listSupplier,
  getSupplier,
  delSupplier,
  addSupplier,
  updateSupplier,
  exportSupplier
} from '@/api/system/supplier'
import AdvancedSearch from '@/components/CaiZhi-AdvancedSearch'
import addSupplierInfo from '@/views/system/supplier/addSupplierInfo'

export default {
  name: 'Supplier',
  components: {
    AdvancedSearch,
    addSupplierInfo
  },
  data() {
    return {
      // 高级搜索组件参数
      searchData: {
        prop:'supplierName'
      },
      title: null,
      isDisabled: false,
      sourceValue: [{
        value: '1',
        label: '招投标'
      }, {
        value: '2',
        label: '内部推荐'
      }, {
        value: '3',
        label: '其他'
      }],
      searchForm: [
        { type: 'input', label: '供应商名称', prop: 'roleName', width: '180px', placeholder: '请输入供应商名称' },
        {
          type: 'select',
          label: '合作状态',
          prop: 'sex',
          width: '180px',
          options: this.statusOptions,
          props: { label: 'dictLabel', value: 'dictValue' },
          change: row => '',
          placeholder: '请选择合作状态'
        },
        {
          type: 'daterangeData',
          label: '合作时间',
          prop: 'dateRange',
          width: '180px'
        },
        { type: 'input', label: '联系人手机号码', prop: 'roleKey', width: '180px', placeholder: '请输入联系人手机号码' }

      ],
      buttons: [
        { label: '查询', icon: 'el-icon-search', type: 'cyan', handle: '' },
        { label: '重置', icon: 'el-icon-refresh', type: 'mini', handle: '' }
      ],

      //详情信息
      visibleAdd: false,

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

      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        supplierName: null,
        startDate: null,
        endDate: null,
        phone: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        supplierName: [
          { required: true, message: '供应商名称不能为空', trigger: 'blur' }
        ],
        creditCode: [
          { required: true, message: '统一社会信用代码不能为空', trigger: 'blur' }, {
            pattern: /[^\d]/g,
            message: '请输入数字',
            trigger: 'blur'
          }
        ],
        address: [
          { required: true, message: '供应商注册地址不能为空', trigger: 'blur' }
        ],
        capital: [
          { required: true, message: '供应商注册资金不能为空', trigger: 'blur' }
        ],
        source: [
          { required: true, message: '供应商来源不能为空', trigger: 'blur' }
        ],
        level: [
          { required: true, message: '供应商等级不能为空', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '供应商联系人手机号不能为空', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '供应商联系邮箱不能为空', trigger: 'blur' },
        ],
        startDate: [
          { required: true, message: '框架协议起始日期不能为空', trigger: 'blur' }
        ],
        endDate: [
          { required: true, message: '框架协议终止日期不能为空', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '供应商状态不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {

    // 处理值对应的name
    filterSource(row) {
      let text = this.sourceValue.filter(item => {
        if (item.value === row.source) {
          return item.label
        }
      })
      return text[0].label
    },

    /** 查询供应商信息列表 */
    getList() {
      this.loading = true
      listSupplier(this.queryParams).then(response => {
        this.supplierList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        supplierName: null,
        creditCode: null,
        address: null,
        capital: null,
        source: null,
        level: null,
        phone: null,
        email: null,
        startDate: null,
        endDate: null,
        status: '0',
        remark: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.visibleAdd = true
      this.isDisabled = false
      this.title = "添加供应商信息";
    },
    /** 查看按钮操作 */
    handleSee(row){
      this.reset()
      this.form = row
      this.visibleAdd = true
      this.isDisabled = true
      this.title = "查看供应商信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getSupplier(id).then(response => {
        this.form = response.data
        this.visibleAdd = true
        this.isDisabled = false
        this.title = '修改供应商信息'
      })
    },

    /** 启动/停止按钮 */
    handleOffOn(row){
      if(row.status==='启动'){
        row.status = '停止'
      }else {
        if(row.status==='停止'){
          row.status = '启动'
        }
      }
      updateSupplier(row).then(response => {
        this.msgSuccess(`${row.status}成功`)
        this.getList()
      })

    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$confirm('是否确认删除供应商信息编号为"' + ids + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delSupplier(ids)
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams
      this.$confirm('是否确认导出所有供应商信息数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return exportSupplier(queryParams)
      }).then(response => {
        this.download(response.msg)
      })
    }
  }
}
</script>
