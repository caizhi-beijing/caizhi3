# form 表单

#### 用法示例：

<!-- 
# 调用方式
<form-search
    :searchData="searchData"
    :searchForm="searchForm"
    :searchHandle="searchHandle"
></Advanced-search>

# 导入组件
import czform from '@/components/CaiZhi-Form'

# 注册组件
components: {
  czform
}, 
-->

### 每新加的业务组件需要进行功能上的描述

# 1. czform

### Attributes

|      参数    |      说明     |      类型     |     默认值    |     例    |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| searchData |  查询数据源  | Object  |  { } | resultData: {name: null,age: null,...}, |
| searchForm  | 高搜字段  | [{ }, { },{ },...]  |  [] |  searchFiled: { type: 'Input', label: '', prop: '', width: '', placeholder: '' },{type: 'Select', label: '',prop: '',options: [{ label: '', value: '' }, { label: '', value: '' }],props: { label: 'label', value: 'value' },change: row => ''}, |
| searchHandle | 按钮 | Array |  [ ] | btn: { label: '查询', type: 'primary', handle: () => '' }, |

