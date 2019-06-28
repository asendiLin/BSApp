package com.bojue.bsapp.order

import android.app.Application
import com.bojue.core.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/28.
 * description:
 */
class OrderStatusChangeViewModel @Inject constructor(application: Application, val repository: com.sendi.order.repository.OrderRepository)
    : BaseViewModel(application) {

    val changeOrderStatusLiveData = repository.changeOrderStatusLiveData

    val deleteOrderLiveData = repository.deleteOrderLiveData

    fun changeStatus(status: Int, id: Int, studentId: Int) {
        repository.changeOrderStatus(status, id, studentId)
    }

    fun deleteOrderById(id:Int){
        repository.deleteOrder(id)
    }
}