package com.bojue.bsapp.order

import android.app.Application
import android.arch.lifecycle.LiveData
import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.OrderModel
import com.bojue.core.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class OrderViewModel @Inject constructor(application: Application,val repository : OrderRepository)
    : BaseViewModel(application) {

    fun getOrderList(type :Int):LiveData<BaseResponse<List<OrderModel>>>{
        return repository.getOrderList(type)
    }
}