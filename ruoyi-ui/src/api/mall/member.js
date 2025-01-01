import request from '@/utils/request'

// 查询会员信息列表
export function listMember(query) {
  return request({
    url: '/mall/member/list',
    method: 'get',
    params: query
  })
}

// 查询会员信息详细
export function getMember(id) {
  return request({
    url: '/mall/member/' + id,
    method: 'get'
  })
}

// 新增会员信息
export function addMember(data) {
  return request({
    url: '/mall/member',
    method: 'post',
    data: data
  })
}

// 修改会员信息
export function updateMember(data) {
  return request({
    url: '/mall/member',
    method: 'put',
    data: data
  })
}

// 删除会员信息
export function delMember(id) {
  return request({
    url: '/mall/member/' + id,
    method: 'delete'
  })
}

// 导出会员信息
export function exportMember(query) {
  return request({
    url: '/mall/member/export',
    method: 'get',
    params: query
  })
}
