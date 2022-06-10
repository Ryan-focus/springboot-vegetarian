import request from '@/utils/request'

export function userLogin(data) {
    return request({
        url: '/users/login',
        method: 'post',
        data
    })
}