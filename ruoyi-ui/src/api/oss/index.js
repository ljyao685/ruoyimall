import request from '@/utils/request'

export function policy(data) {
  return request({
    url: '/aliyun/oss/policy',
    method: 'Get',
    params: data
  })
}

export function deleteOss(data) {
  return request({
    url: '/aliyun/oss/delete',
    method: 'Get',
    params: data
  })
}
