import request from '@/utils/request'

// 查询商品信息列表
export function listProduct(query) {
  return request({
    url: '/mall/product/list',
    method: 'get',
    params: query
  })
}

// 查询商品信息详细
export function getProduct(id) {
  return request({
    url: '/mall/product/' + id,
    method: 'get'
  })
}

// 新增商品信息
export function addProduct(data) {
  return request({
    url: '/mall/product',
    method: 'post',
    data: data
  })
}

// 修改商品信息
export function updateProduct(data) {
  return request({
    url: '/mall/product',
    method: 'put',
    data: data
  })
}

// 修改商品状态信息
export function updateProductStatus(data) {
  return request({
    url: '/mall/product/updateStatus',
    method: 'post',
    data: data
  })
}

// 修改商品sku库存信息
export function updateProductSku(data) {
  return request({
    url: '/mall/product/updateSku',
    method: 'post',
    data: data
  })
}

// 删除商品信息
export function delProduct(id) {
  return request({
    url: '/mall/product/' + id,
    method: 'delete'
  })
}

// 导出商品信息
export function exportProduct(query) {
  return request({
    url: '/mall/product/export',
    method: 'get',
    params: query
  })
}
