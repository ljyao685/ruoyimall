import request from '@/utils/request'

// 查询商品属性值信息列表
export function listProdAttrValue(query) {
  return request({
    url: '/mall/prodAttrValue/list',
    method: 'get',
    params: query
  })
}

// 查询商品属性值信息详细
export function getProdAttrValue(id) {
  return request({
    url: '/mall/prodAttrValue/' + id,
    method: 'get'
  })
}

// 新增商品属性值信息
export function addProdAttrValue(data) {
  return request({
    url: '/mall/prodAttrValue',
    method: 'post',
    data: data
  })
}

// 修改商品属性值信息
export function updateProdAttrValue(data) {
  return request({
    url: '/mall/prodAttrValue',
    method: 'put',
    data: data
  })
}

// 删除商品属性值信息
export function delProdAttrValue(id) {
  return request({
    url: '/mall/prodAttrValue/' + id,
    method: 'delete'
  })
}

// 导出商品属性值信息
export function exportProdAttrValue(query) {
  return request({
    url: '/mall/prodAttrValue/export',
    method: 'get',
    params: query
  })
}
