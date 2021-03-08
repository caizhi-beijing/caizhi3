# 高级搜索 公共组件介绍

#### 用法示例：

<!-- 
# 调用方式
<Advanced-search
    :searchData="searchData"
    :searchForm="searchForm"
    :searchHandle="searchHandle"
></Advanced-search>

# 导入组件
import AdvancedSearch from '@/components/AdvancedSearch'

# 注册组件
components: {
  AdvancedSearch
}, 
-->

### 每新加的业务组件需要进行功能上的描述

# 1. AdvancedSearch

### Attributes

|      参数    |      说明     |      类型     |     默认值    |     例    |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| searchData |  查询数据源  | Object  |  { } | resultData: {name: null,age: null,...}, |
| searchForm  | 高搜字段  | [{ }, { },{ },...]  |  [] |  searchFiled: { type: 'Input', label: '', prop: '', width: '', placeholder: '' },{type: 'Select', label: '',prop: '',options: [{ label: '', value: '' }, { label: '', value: '' }],props: { label: 'label', value: 'value' },change: row => ''}, |
| searchHandle | 按钮 | Array |  [ ] | btn: { label: '查询', type: 'primary', handle: () => '' }, |

