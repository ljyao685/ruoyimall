import request from '@/utils/request'

// 查询商品分类信息列表
export function listProdCate(query) {
  return request({
    url: '/mall/prodCate/list',
    method: 'get',
    params: query
  })
}

// 查询商品分类下拉树列表
export function getProdCateTreeList(query) {
  return request({
    url: '/mall/prodCate/treeList',
    method: 'get',
    params: query
  })
}

// 查询商品分类级联选择列表
export function getProdCateCascaderList(query) {
  return request({
    url: '/mall/prodCate/cascaderList',
    method: 'get',
    params: query
  })
}

// 查询商品分类信息详细
export function getProdCate(id) {
  return request({
    url: '/mall/prodCate/' + id,
    method: 'get'
  })
}

// 新增商品分类信息
export function addProdCate(data) {
  return request({
    url: '/mall/prodCate',
    method: 'post',
    data: data
  })
}

// 修改商品分类状态
export function updateProdCateStatus(data) {
  return request({
    url: '/mall/prodCate/updateStatus',
    method: 'post',
    data: data
  })
}

// 修改商品分类信息
export function updateProdCate(data) {
  return request({
    url: '/mall/prodCate',
    method: 'put',
    data: data
  })
}

// 删除商品分类信息
export function delProdCate(id) {
  return request({
    url: '/mall/prodCate/' + id,
    method: 'delete'
  })
}

// 导出商品分类信息
export function exportProdCate(query) {
  return request({
    url: '/mall/prodCate/export',
    method: 'get',
    params: query
  })
}
