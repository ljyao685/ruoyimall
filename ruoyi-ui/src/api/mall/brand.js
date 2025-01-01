import request from '@/utils/request'

// 查询商品品牌信息列表
export function listBrand(query) {
  return request({
    url: '/mall/brand/list',
    method: 'get',
    params: query
  })
}

// 查询商品品牌信息详细
export function getBrand(id) {
  return request({
    url: '/mall/brand/' + id,
    method: 'get'
  })
}

// 新增商品品牌信息
export function addBrand(data) {
  return request({
    url: '/mall/brand',
    method: 'post',
    data: data
  })
}

// 修改商品品牌状态
export function updateBrandStatus(data) {
  return request({
    url: '/mall/brand/updateStatus',
    method: 'post',
    data: data
  })
}

// 修改商品品牌信息
export function updateBrand(data) {
  return request({
    url: '/mall/brand',
    method: 'put',
    data: data
  })
}

// 删除商品品牌信息
export function delBrand(id) {
  return request({
    url: '/mall/brand/' + id,
    method: 'delete'
  })
}

// 导出商品品牌信息
export function exportBrand(query) {
  return request({
    url: '/mall/brand/export',
    method: 'get',
    params: query
  })
}
