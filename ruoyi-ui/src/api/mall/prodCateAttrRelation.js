import request from '@/utils/request'

// 查询商品分类属性关联信息列表
export function listProdCateAttrRelation(query) {
  return request({
    url: '/mall/prodCateAttrRelation/list',
    method: 'get',
    params: query
  })
}

// 查询商品分类属性关联信息详细
export function getProdCateAttrRelation(id) {
  return request({
    url: '/mall/prodCateAttrRelation/' + id,
    method: 'get'
  })
}

// 新增商品分类属性关联信息
export function addProdCateAttrRelation(data) {
  return request({
    url: '/mall/prodCateAttrRelation',
    method: 'post',
    data: data
  })
}

// 修改商品分类属性关联信息
export function updateProdCateAttrRelation(data) {
  return request({
    url: '/mall/prodCateAttrRelation',
    method: 'put',
    data: data
  })
}

// 删除商品分类属性关联信息
export function delProdCateAttrRelation(id) {
  return request({
    url: '/mall/prodCateAttrRelation/' + id,
    method: 'delete'
  })
}

// 导出商品分类属性关联信息
export function exportProdCateAttrRelation(query) {
  return request({
    url: '/mall/prodCateAttrRelation/export',
    method: 'get',
    params: query
  })
}
