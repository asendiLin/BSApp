package com.sendi.order.viewmodel

import android.app.Application
import com.sendi.order.repository.OrderRepository
import com.bojue.core.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/24.
 * description:
 */
class PublishOrderViewModel @Inject constructor(application: Application,val repository: OrderRepository)
    :BaseViewModel(application) {

    val publishLiveData = repository.publishOrderLiveData

    fun publishOrder(content :String,stuId:Int,type:Int,phone:String,
                     price:Int,endDate : String,address:String){
        repository.publishOrder(content, stuId, type, phone, price, endDate, address)
    }
}