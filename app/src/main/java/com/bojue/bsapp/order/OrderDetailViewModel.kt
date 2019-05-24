package com.bojue.bsapp.order

import android.app.Application
import com.bojue.bsapp.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/24.
 * description:
 */
class OrderDetailViewModel @Inject constructor(application:Application,val repository: OrderRepository)
    :BaseViewModel(application){

    fun getOrderDetail(){}

    fun acceptOrder(){}

    fun cancelOrder(){}

}