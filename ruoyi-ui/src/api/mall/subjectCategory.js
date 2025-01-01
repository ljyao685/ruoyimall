import request from '@/utils/request'

// 查询专题分类信息列表
export function listSubjectCategory(query) {
  return request({
    url: '/mall/subjectCategory/list',
    method: 'get',
    params: query
  })
}

// 查询专题分类信息详细
export function getSubjectCategory(id) {
  return request({
    url: '/mall/subjectCategory/' + id,
    method: 'get'
  })
}

// 新增专题分类信息
export function addSubjectCategory(data) {
  return request({
    url: '/mall/subjectCategory',
    method: 'post',
    data: data
  })
}

// 修改专题分类状态
export function updateSubjectCategoryStatus(data) {
  return request({
    url: '/mall/subjectCategory/updateStatus',
    method: 'post',
    data: data
  })
}

// 修改专题分类信息
export function updateSubjectCategory(data) {
  return request({
    url: '/mall/subjectCategory',
    method: 'put',
    data: data
  })
}

// 删除专题分类信息
export function delSubjectCategory(id) {
  return request({
    url: '/mall/subjectCategory/' + id,
    method: 'delete'
  })
}

// 导出专题分类信息
export function exportSubjectCategory(query) {
  return request({
    url: '/mall/subjectCategory/export',
    method: 'get',
    params: query
  })
}
