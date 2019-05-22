package com.bojue.bsapp.order

import android.app.Application
import com.bojue.bsapp.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class OrderViewModel @Inject constructor(application: Application,val repository : OrderRepository)
    : BaseViewModel(application) {

    fun getOrderList(){}

    fun getOrderDetail(){}

    fun acceptOrder(){}

    fun cancelOrder(){}

    fun publishOrder(){}
}