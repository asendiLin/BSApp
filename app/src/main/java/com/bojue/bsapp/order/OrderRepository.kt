package com.bojue.bsapp.order

import com.bojue.bsapp.http.api.OrderService
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class OrderRepository @Inject constructor(val service : OrderService) {
    fun getOrderList(){}

    fun getOrderDetail(){}

    fun acceptOrder(){}

    fun cancelOrder(){}

    fun publishOrder(){}
}