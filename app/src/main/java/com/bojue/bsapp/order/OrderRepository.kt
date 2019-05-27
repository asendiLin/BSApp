package com.bojue.bsapp.order

import android.arch.lifecycle.MutableLiveData
import com.bojue.bsapp.http.api.OrderService
import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.OrderModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class OrderRepository @Inject constructor(val service : OrderService) {

    private val mOrderListLiveData = MutableLiveData<BaseResponse<List<OrderModel>>>()
    private val mOrderDetailLiveData = MutableLiveData<BaseResponse<OrderModel>>()
    private val mAcceptOrderLiveData = MutableLiveData<BaseResponse<Any>>()
    private val mCancelOrderLiveData = MutableLiveData<BaseResponse<Any>>()
    private val mPublishOrderLiveData = MutableLiveData<BaseResponse<Any>>()

    fun getOrderList(){

    }

    fun getOrderDetail(){}

    fun acceptOrder(){}

    fun cancelOrder(){}

    fun publishOrder(){}
}