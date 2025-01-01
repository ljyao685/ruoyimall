import request from '@/utils/request'

// 查询优选专区信息列表
export function listPreferenceArea(query) {
  return request({
    url: '/mall/preferenceArea/list',
    method: 'get',
    params: query
  })
}

// 查询优选专区信息详细
export function getPreferenceArea(id) {
  return request({
    url: '/mall/preferenceArea/' + id,
    method: 'get'
  })
}

// 新增优选专区信息
export function addPreferenceArea(data) {
  return request({
    url: '/mall/preferenceArea',
    method: 'post',
    data: data
  })
}

// 修改优选专区信息
export function updatePreferenceArea(data) {
  return request({
    url: '/mall/preferenceArea',
    method: 'put',
    data: data
  })
}

// 删除优选专区信息
export function delPreferenceArea(id) {
  return request({
    url: '/mall/preferenceArea/' + id,
    method: 'delete'
  })
}

// 导出优选专区信息
export function exportPreferenceArea(query) {
  return request({
    url: '/mall/preferenceArea/export',
    method: 'get',
    params: query
  })
}
