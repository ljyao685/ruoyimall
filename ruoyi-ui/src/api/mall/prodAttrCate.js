import request from '@/utils/request'

// 查询商品属性分类信息列表
export function listProdAttrCate(query) {
  return request({
    url: '/mall/prodAttrCate/list',
    method: 'get',
    params: query
  })
}

// 获取所有商品属性分类及其下属性
export function listProdAttrCateWithAttr() {
  return request({
    url: '/mall/prodAttrCate/withAttr',
    method: 'get'
  })
}

// 查询商品属性分类信息详细
export function getProdAttrCate(id) {
  return request({
    url: '/mall/prodAttrCate/' + id,
    method: 'get'
  })
}

// 新增商品属性分类信息
export function addProdAttrCate(data) {
  return request({
    url: '/mall/prodAttrCate',
    method: 'post',
    data: data
  })
}

// 修改商品属性分类信息
export function updateProdAttrCate(data) {
  return request({
    url: '/mall/prodAttrCate',
    method: 'put',
    data: data
  })
}

// 删除商品属性分类信息
export function delProdAttrCate(id) {
  return request({
    url: '/mall/prodAttrCate/' + id,
    method: 'delete'
  })
}

// 导出商品属性分类信息
export function exportProdAttrCate(query) {
  return request({
    url: '/mall/prodAttrCate/export',
    method: 'get',
    params: query
  })
}
