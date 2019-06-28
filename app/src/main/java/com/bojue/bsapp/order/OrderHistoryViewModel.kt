package com.bojue.bsapp.order

import android.app.Application
import android.arch.lifecycle.LiveData
import com.sendi.base.data.BaseResponse
import com.sendi.order.model.OrderModel
import com.bojue.core.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/28.
 * description:
 */
class OrderHistoryViewModel @Inject constructor(application: Application,val repository: com.sendi.order.repository.OrderRepository)
    : BaseViewModel(application) {

    fun getHistoryOrderList(orderType : Int,stuId :Int): LiveData<BaseResponse<List<com.sendi.order.model.OrderModel>>> {
        return repository.getHistoryOrderList(orderType, stuId)
    }
}