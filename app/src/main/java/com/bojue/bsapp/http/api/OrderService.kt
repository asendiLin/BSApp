package com.bojue.bsapp.http.api

import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.OrderModel
import retrofit2.Call

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
interface OrderService {

    fun getOrderList():Call<BaseResponse<List<OrderModel>>>

    fun getOrderDetail()

    fun acceptOrder():Call<BaseResponse<Any>>

    fun cancelOrder():Call<BaseResponse<Any>>

    fun publishOrder():Call<BaseResponse<Any>>

}