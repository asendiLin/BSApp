package com.sendi.order.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import com.sendi.base.data.BaseResponse
import com.sendi.order.model.OrderModel
import com.bojue.core.viewmodel.BaseViewModel
import com.sendi.order.repository.OrderRepository
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class OrderViewModel @Inject constructor(application: Application,val repository : OrderRepository)
    : BaseViewModel(application) {

    fun getOrderList(type :Int):LiveData<BaseResponse<List<com.sendi.order.model.OrderModel>>>{
        return repository.getOrderList(type)
    }
}