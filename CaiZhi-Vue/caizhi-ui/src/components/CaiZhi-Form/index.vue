<template>
  <div class="_input">
    <el-card>
      <el-row :gutter="20" justify="center">
        <el-form
          :size="size"
          :model="formData"
          :rules="rules"
          ref="form"
          :label-width="labelWidth"
          :inline="inline"
        >
          <el-form-item
            v-for="item in fields"
            :label="item.label"
            :key="item.prop"
            :prop="item.prop"
          >
            <!-- 输入框 -->
            <el-col :span="24">
              <el-input v-if="item.type === 'input'"
                        v-model="formData[item.prop]"
                        :size="item.size"
                        :placeholder="item.placeholder"
                        clearable
              ></el-input>
            </el-col>

            <!-- textarea -->
            <el-col :span="24">
              <el-input v-if="item.type==='textarea'" type="textarea" v-model="formData[item.prop]"
                        :placeholder="item.placeholder" clearable
              ></el-input>
            </el-col>

            <!-- 上传 -->
            <el-col :span="24">
              <el-upload v-if="item.type==='file'" action="#" :http-request="requestUpload" :show-file-list="false" :before-upload="beforeUpload">
                <el-button size="small">
                  上传
                  <i class="el-icon-upload el-icon--right"></i>
                </el-button>
              </el-upload>

<!--              <el-upload v-if="item.type==='file'" ref="file" :file-list="fileList" :action="fileAction"-->
<!--                         :before-upload="fileBeforeUpload">-->
<!--                <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>-->
<!--              </el-upload>-->
            </el-col>

            <!-- 密码框 -->
            <el-col :span="24">
              <el-input
                v-if="item.type === 'password'"
                disabled
                v-model="formData[item.prop]"
                :placeholder="item.placeholder"
                auto-complete="off"
              >
                <template slot="append">
                  <span @click="item.onClick">{{ item.name }}</span>
                </template>
              </el-input>
            </el-col>

            <!-- 下拉框 -->
            <!-- $forceUpdate() 下拉刷新,修复数据改变下拉框不变的bug -->
            <el-col :span="24">
              <el-select
                v-if="item.type === 'select'"
                v-model="formData[item.prop]"
                :placeholder="item.placeholder"
                @change="item.change(formData[item.prop])"
                @visible-change="$forceUpdate()"
              >
                <el-option
                  v-for="op in item.options"
                  :label="op.label"
                  :value="op.value"
                  :key="op.value"
                >{{ op.label }}
                </el-option>
              </el-select>
            </el-col>

            <!-- 单选 -->
            <el-col :span="24">
              <el-radio-group v-if="item.type === 'Radio'" v-model="formData[item.prop]">
                <el-radio v-for="ra in item.radios" :label="ra.value" :key="ra.value">{{ ra.label }}</el-radio>
              </el-radio-group>
            </el-col>
            <!-- 组合单选按钮 -->
            <el-col :span="24">
              <el-radio-group
                v-if="item.type === 'radio'"
                v-model="formData[item.prop]"
                @change="item.change && item.change(formData[item.prop])"
              >
                <el-radio-button
                  v-for="ra in item.radios"
                  :label="ra.value"
                  :key="ra.value"
                >{{ ra.label }}
                </el-radio-button>
              </el-radio-group>
            </el-col>

            <!-- 复选框 -->
            <el-col :span="24">
              <el-checkbox-group v-if="item.type === 'Checkbox'" v-model="formData[item.prop]">
                <el-checkbox v-for="ch in item.checkboxs" :label="ch.value" :key="ch.value">{{
                    ch.label
                  }}
                </el-checkbox>
              </el-checkbox-group>
            </el-col>

            <!-- 日期 -->
            <el-col :span="24">
              <div class="block">
              <el-date-picker
                v-if="item.type === 'date'"
                v-model="formData[item.prop]"
                align="left"
                value-format="yyyy-MM-dd"
                @change="item.change(formData[item.prop])"
                :placeholder="item.placeholder"
              ></el-date-picker>
              </div>
            </el-col>

            <!-- 时间 -->
            <el-col :span="24">
              <el-time-select v-if="item.type === 'time'" v-model="formData[item.prop]"></el-time-select>
            </el-col>

            <!-- 日期时间 -->
            <el-col :span="24">
              <el-date-picker
                v-if="item.type === 'dateTime'"
                type="datetime"
                v-model="formData[item.prop]"
                :placeholder="item.placeholder"
                value-format="yyyy-MM-dd hh:mm:ss"
                :disabled="item.disable && item.disable(formData[item.prop])"
                @change="item.change(formData[item.prop])"
              ></el-date-picker>
            </el-col>

            <!-- 起止时间 -->
            <el-col :span="24">
              <el-date-picker
                v-if="item.type === 'daterange'"
                v-model="formData[item.prop]"
                type="daterange"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"
                @change="item.change(formData[item.prop])"
              ></el-date-picker>
            </el-col>


            <!-- 滑块 -->
            <el-col :span="24">
              <el-slider v-if="item.type === 'Slider'" v-model="formData[item.prop]"></el-slider>
            </el-col>
            <!-- 开关 -->
            <el-col :span="24">
              <el-switch v-if="item.type === 'Switch'" v-model="formData[item.prop]"></el-switch>
            </el-col>

            <!-- 三级联动 cascader -->
            <el-col :span="24">
              <el-cascader
                v-if="item.type === 'cascader'"
                :options="item.props.options"
                v-model="formData[item.prop]"
                :placeholder="item.props.placeholder || '请选择'"
                :disabled="item.props.disabled || false"
                style="width: 100%;"
                :props="item.props.defaultParams"
                @change="item.change(formData[item.prop], item)"
              ></el-cascader>
            </el-col>
          </el-form-item>
        </el-form>
      </el-row>


      <!-- button 操作按钮 -->
      <div class="_btn">
        <el-button
          v-for="item in buttons"
          :icon="item.icon || ''"
          :size="item.size || size"
          :key="item.label"
          :type="item.type"
          @click="item.handle()"
        >
          <span>{{ item.label }}</span>
        </el-button>
      </div>
    </el-card>

  </div>
</template>

<script>
import { uploadAvatar } from '@/api/system/user'
import store from '@/store'

export default {
  props: {
    labelWidth: {
      type: String,
      default: '120px'
    },
    size: {
      type: String,
      default: 'medium'
    },
    fields: {
      type: Array,
      default: () => []
    },
    buttons: {
      type: Array,
      default: () => []
    },
    formData: {
      type: Object,
      default: () => {
      }
    },
    inline: {
      type: Boolean,
      dedfault: false
    }
  },
  computed: {
    /** 解析表单的正则验证***/
    rules() {
      let rules = this.fields.reduce((map, i) => {
        if (i.rules) {
          map[i.prop] = i.rules
        }
        return map
      }, {})
      return rules
    }
  },
  mounted() {
  },
  methods: {
    // 覆盖默认的上传行为
    requestUpload() {
    },
    // 上传预处理
    beforeUpload(file) {
      if (file.type.indexOf("image/") == -1) {
        this.msgError("文件格式错误，请上传图片类型,如：JPG，PNG后缀的文件。");
      } else {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
          console.log( reader.result )
          // this.options.img = reader.result;
        };
      }
    },
    // 上传图片
    uploadImg() {
      this.$refs.cropper.getCropBlob(data => {
        let formData = new FormData();
        formData.append("avatarfile", data);
        uploadAvatar(formData).then(response => {
          this.open = false;
          this.options.img = process.env.VUE_APP_BASE_API + response.imgUrl;
          store.commit('SET_AVATAR', this.options.img);
          this.msgSuccess("修改成功");
          this.visible = false;
        });
      });
    },
    // 验证表单
    sublime() {
      // 父组件的点击事件
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.$emit('sublime', valid)
        } else {
          return false
        }
      })
    },
    // 重置表单
    reset() {
      this.$refs['form'].resetFields()
    }
  }
}
</script>
<style type="scss" scoped>

/deep/ .el-card__body {
  text-align: center;
}
/deep/ .el-select{
  width: 100%;
}
/*计算起止时间外层div的宽度*/
._cal-width{
  width: calc(100% - 187.5px)
}
/deep/ .el-date-editor--daterange{
  width: 100%;
}
/*日期宽度*/
/deep/ .el-date-editor--date{
  width: calc(100% - 20px);
}

</style>
<!--<style type="scss" scoped>-->
<!--._input {-->
<!--  padding: 0px 20%;-->
<!--}-->

<!--._btn {-->
<!--  /*float: right;*/-->
<!--  text-align: center;-->
<!--}-->
<!--/deep/ .el-input__inner{-->
<!--  width: 240px;-->
<!--}-->
<!--/deep/ .el-textarea__inner{-->
<!--  width: 660px;-->
<!--}-->
<!--</style>-->

