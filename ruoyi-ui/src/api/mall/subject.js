import request from '@/utils/request'

// 查询专题信息列表
export function listSubject(query) {
  return request({
    url: '/mall/subject/list',
    method: 'get',
    params: query
  })
}

// 查询专题信息详细
export function getSubject(id) {
  return request({
    url: '/mall/subject/' + id,
    method: 'get'
  })
}

// 新增专题信息
export function addSubject(data) {
  return request({
    url: '/mall/subject',
    method: 'post',
    data: data
  })
}

// 修改专题状态
export function updateSubjectStatus(data) {
  return request({
    url: '/mall/subject/updateStatus',
    method: 'post',
    data: data
  })
}

// 修改专题信息
export function updateSubject(data) {
  return request({
    url: '/mall/subject',
    method: 'put',
    data: data
  })
}

// 删除专题信息
export function delSubject(id) {
  return request({
    url: '/mall/subject/' + id,
    method: 'delete'
  })
}

// 导出专题信息
export function exportSubject(query) {
  return request({
    url: '/mall/subject/export',
    method: 'get',
    params: query
  })
}
