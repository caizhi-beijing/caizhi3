import request from '@/utils/request'
let requestTips = '/basics/supplier'
// 查询供应商列表
export function listSupplier(query) {
  return request({
    url: `${requestTips}/list`,
    method: 'get',
    params: query
  })
}

export function upload(data) {
  return request({
    url: '/common/upload',
    method: 'post',
    data: data
  })
}

// 查询供应商信息详细
export function getSupplier(id) {
  return request({
    url: `${requestTips}/${id}`,
    method: 'get'
  })
}

// 新增供应商信息
export function addSupplier(data) {
  return request({
    url: `${requestTips}`,
    method: 'post',
    data: data
  })
}

// 修改供应商信息-update
export function updateSupplier(data) {
  return request({
    url: `${requestTips}`,
    method: 'put',
    data: data
  })
}

// 删除供应商信息
export function delSupplier(id) {
  return request({
    url: `${requestTips}/${id}` ,
    method: 'delete'
  })
}

// 导出供应商信息
export function exportSupplier(query) {
  return request({
    url: `${requestTips}/export`,
    method: 'get',
    params: query
  })
}

