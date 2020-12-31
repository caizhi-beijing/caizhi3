<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="供应商名称" prop="supplierName">
        <el-input
          v-model="queryParams.supplierName"
          placeholder="请输入供应商名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="统一社会信用代码" prop="creditCode">
        <el-input
          v-model="queryParams.creditCode"
          placeholder="请输入统一社会信用代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商注册地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入供应商注册地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商注册资金" prop="capital">
        <el-input
          v-model="queryParams.capital"
          placeholder="请输入供应商注册资金"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商来源" prop="source">
        <el-input
          v-model="queryParams.source"
          placeholder="请输入供应商来源"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商等级" prop="level">
        <el-input
          v-model="queryParams.level"
          placeholder="请输入供应商等级"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商联系人手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入供应商联系人手机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商联系邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入供应商联系邮箱"
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
          <el-option label="请选择字典生成" value="" />
        </el-select>
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
          v-hasPermi="['basics:supplier:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['basics:supplier:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['basics:supplier:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basics:supplier:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="supplierList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="供应商名称" align="center" prop="supplierName" />
      <el-table-column label="统一社会信用代码" align="center" prop="creditCode" />
      <el-table-column label="供应商注册地址" align="center" prop="address" />
      <el-table-column label="供应商注册资金" align="center" prop="capital" />
      <el-table-column label="供应商来源" align="center" prop="source" />
      <el-table-column label="供应商等级" align="center" prop="level" />
      <el-table-column label="供应商联系人手机号" align="center" prop="phone" />
      <el-table-column label="供应商联系邮箱" align="center" prop="email" />
      <el-table-column label="框架协议起始日期" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="框架协议终止日期" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="供应商状态" align="center" prop="status" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basics:supplier:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basics:supplier:remove']"
          >删除</el-button>
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

    <!-- 添加或修改供应商信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="供应商名称" prop="supplierName">
          <el-input v-model="form.supplierName" placeholder="请输入供应商名称" />
        </el-form-item>
        <el-form-item label="统一社会信用代码" prop="creditCode">
          <el-input v-model="form.creditCode" placeholder="请输入统一社会信用代码" />
        </el-form-item>
        <el-form-item label="供应商注册地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入供应商注册地址" />
        </el-form-item>
        <el-form-item label="供应商注册资金" prop="capital">
          <el-input v-model="form.capital" placeholder="请输入供应商注册资金" />
        </el-form-item>
        <el-form-item label="供应商来源" prop="source">
          <el-input v-model="form.source" placeholder="请输入供应商来源" />
        </el-form-item>
        <el-form-item label="供应商等级" prop="level">
          <el-input v-model="form.level" placeholder="请输入供应商等级" />
        </el-form-item>
        <el-form-item label="供应商联系人手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入供应商联系人手机号" />
        </el-form-item>
        <el-form-item label="供应商联系邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入供应商联系邮箱" />
        </el-form-item>
        <el-form-item label="框架协议起始日期" prop="startDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.startDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择框架协议起始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="框架协议终止日期" prop="endDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.endDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择框架协议终止日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="供应商状态">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSupplier, getSupplier, delSupplier, addSupplier, updateSupplier, exportSupplier } from "@/api/basics/supplier";

export default {
  name: "Supplier",
  data() {
    return {
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
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
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
        status: null,
        remark: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        supplierName: [
          { required: true, message: "供应商名称不能为空", trigger: "blur" }
        ],
        creditCode: [
          { required: true, message: "统一社会信用代码不能为空", trigger: "blur" }
        ],
        address: [
          { required: true, message: "供应商注册地址不能为空", trigger: "blur" }
        ],
        capital: [
          { required: true, message: "供应商注册资金不能为空", trigger: "blur" }
        ],
        source: [
          { required: true, message: "供应商来源不能为空", trigger: "blur" }
        ],
        level: [
          { required: true, message: "供应商等级不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "供应商联系人手机号不能为空", trigger: "blur" }
        ],
        email: [
          { required: true, message: "供应商联系邮箱不能为空", trigger: "blur" }
        ],
        startDate: [
          { required: true, message: "框架协议起始日期不能为空", trigger: "blur" }
        ],
        endDate: [
          { required: true, message: "框架协议终止日期不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "供应商状态不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询供应商信息列表 */
    getList() {
      this.loading = true;
      listSupplier(this.queryParams).then(response => {
        this.supplierList = response.rows;
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
        status: "0",
        remark: null
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加供应商信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSupplier(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改供应商信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSupplier(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSupplier(this.form).then(response => {
              this.msgSuccess("新增成功");
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
      this.$confirm('是否确认删除供应商信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSupplier(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有供应商信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportSupplier(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
