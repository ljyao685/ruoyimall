import request from '@/utils/request'

// 查询商品sku库存信息列表
export function listProdSkuStock(query) {
  return request({
    url: '/mall/prodSkuStock/list',
    method: 'get',
    params: query
  })
}

// 查询商品sku库存信息详细
export function getProdSkuStock(id) {
  return request({
    url: '/mall/prodSkuStock/' + id,
    method: 'get'
  })
}

// 新增商品sku库存信息
export function addProdSkuStock(data) {
  return request({
    url: '/mall/prodSkuStock',
    method: 'post',
    data: data
  })
}

// 修改商品sku库存信息
export function updateProdSkuStock(data) {
  return request({
    url: '/mall/prodSkuStock',
    method: 'put',
    data: data
  })
}

// 删除商品sku库存信息
export function delProdSkuStock(id) {
  return request({
    url: '/mall/prodSkuStock/' + id,
    method: 'delete'
  })
}

// 导出商品sku库存信息
export function exportProdSkuStock(query) {
  return request({
    url: '/mall/prodSkuStock/export',
    method: 'get',
    params: query
  })
}
