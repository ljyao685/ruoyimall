import request from '@/utils/request'

// 查询会员等级信息列表
export function listMemberLevel(query) {
  return request({
    url: '/mall/memberLevel/list',
    method: 'get',
    params: query
  })
}

// 查询会员等级信息详细
export function getMemberLevel(id) {
  return request({
    url: '/mall/memberLevel/' + id,
    method: 'get'
  })
}

// 新增会员等级信息
export function addMemberLevel(data) {
  return request({
    url: '/mall/memberLevel',
    method: 'post',
    data: data
  })
}

// 修改会员等级信息
export function updateMemberLevel(data) {
  return request({
    url: '/mall/memberLevel',
    method: 'put',
    data: data
  })
}

// 删除会员等级信息
export function delMemberLevel(id) {
  return request({
    url: '/mall/memberLevel/' + id,
    method: 'delete'
  })
}

// 导出会员等级信息
export function exportMemberLevel(query) {
  return request({
    url: '/mall/memberLevel/export',
    method: 'get',
    params: query
  })
}
