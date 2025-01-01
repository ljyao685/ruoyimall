import request from '@/utils/request'

// 查询商品属性信息列表
export function listProdAttr(query) {
  return request({
    url: '/mall/prodAttr/list',
    method: 'get',
    params: query
  })
}

// 根据商品分类的id获取商品属性及属性分类
export function getAttrInfo(cId) {
  return request({
    url: '/mall/prodAttr/getAttrInfo/' + cId,
    method: 'get'
  })
}

// 查询商品属性信息详细
export function getProdAttr(id) {
  return request({
    url: '/mall/prodAttr/' + id,
    method: 'get'
  })
}

// 新增商品属性信息
export function addProdAttr(data) {
  return request({
    url: '/mall/prodAttr',
    method: 'post',
    data: data
  })
}

// 修改商品属性信息
export function updateProdAttr(data) {
  return request({
    url: '/mall/prodAttr',
    method: 'put',
    data: data
  })
}

// 删除商品属性信息
export function delProdAttr(id) {
  return request({
    url: '/mall/prodAttr/' + id,
    method: 'delete'
  })
}

// 导出商品属性信息
export function exportProdAttr(query) {
  return request({
    url: '/mall/prodAttr/export',
    method: 'get',
    params: query
  })
}
