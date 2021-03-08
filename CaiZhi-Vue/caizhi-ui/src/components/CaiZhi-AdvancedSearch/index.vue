<!-- 搜索表单 -->
<template>
  <div class="advanced-search">
    <el-form :size="size" inline :label-width="labelWidth">
      <el-form-item v-for="item in searchForm" :label="item.label" :key="item.prop">
        <!-- 输入框 -->
        <el-input v-if="item.type==='input'" v-model="searchData[item.prop]" :placeholder="item.placeholder" clearable class="_as_w240"></el-input>

        <!-- 下拉框 -->
        <el-select class="_as_w240" v-if="item.type==='select'" v-model="searchData[item.prop]"
                   :placeholder="item.placeholder"
                   @change="item.change(searchData[item.prop])"
        >
          <el-option v-for="op in item.options" :label="op.label" :value="op.value" :key="op.value"></el-option>
        </el-select>

        <!-- 单选 -->
        <el-radio-group class="_as_w240" v-if="item.type==='radio'" v-model="searchData[item.prop]">
          <el-radio v-for="ra in item.radios" :label="ra.value" :key="ra.value">{{ ra.label }}</el-radio>
        </el-radio-group>

        <!-- 单选按钮 -->
        <el-radio-group class="_as_w240" v-if="item.type==='radioButton'" v-model="searchData[item.prop]"
                        @change="item.change && item.change(searchData[item.prop])"
        >
          <el-radio-button v-for="ra in item.radios" :label="ra.value" :key="ra.value">{{ ra.label }}</el-radio-button>
        </el-radio-group>

        <!-- 复选框 -->
        <el-checkbox-group class="_as_w240" v-if="item.type==='checkbox'" v-model="searchData[item.prop]">
          <el-checkbox v-for="ch in item.checkboxs" :label="ch.value" :key="ch.value">{{ ch.label }}</el-checkbox>
        </el-checkbox-group>

        <!-- 日期 -->
        <el-date-picker class="_as_w240" v-if="item.type==='date'" v-model="searchData[item.prop]"></el-date-picker>

        <!-- 时间 -->
        <el-time-select class="_as_w240" v-if="item.type==='time'" v-model="searchData[item.prop]"
        ></el-time-select>

        <!-- 日期时间 -->
        <el-date-picker class="_as_w240" v-if="item.type==='dateTime'" type="datetime" v-model="searchData[item.prop]"
                        :disabled="item.disable && item.disable(searchData[item.prop])"
        ></el-date-picker>

        <!-- 滑块 -->
        <el-slider class="_as_w240" v-if="item.type==='slider'" v-model="searchData[item.prop]"></el-slider>

        <!-- 开关 -->
        <el-switch class="_as_w240" v-if="item.type==='switch'" v-model="searchData[item.prop]"></el-switch>

        <!-- 开始日期 - 结束日期 -->
        <el-date-picker
          class="_as_w240"
          v-if="item.type==='daterangeData'"
          v-model="searchData[item.prop]"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>


      </el-form-item>

      <!-- 搜索按钮 -->
      <el-form-item v-for="item in buttons" :key="item.label">
        <el-button :type="item.type" :icon="item.icon || ''" :size="item.size || size" @click="item.handle()">
          {{ item.label }}
        </el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
export default {
  props: {
    labelWidth: {
      type: String,
      default: '100px'
    },
    size: {
      type: String,
      default: 'mini'
    },
    searchForm: {
      type: Array,
      default: []
    },
    buttons: {
      type: Array,
      default: () => []
    },
    searchData: {
      type: Object,
      default: {}
    }
  },
  data() {
    return {}
  }

}
</script>

<style lang="scss" scoped>
._as_w240 {
  width: 240px;
}

.tar {
  text-align: right;
}
</style>
