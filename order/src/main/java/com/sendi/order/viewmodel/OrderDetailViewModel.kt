package com.sendi.order.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import com.sendi.base.data.BaseResponse
import com.bojue.core.viewmodel.BaseViewModel
import com.sendi.order.repository.OrderRepository
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/24.
 * description:
 */
class OrderDetailViewModel @Inject constructor(application:Application,val repository: OrderRepository)
    :BaseViewModel(application){

    fun acceptOrder(status :Int ,id:Int,stuId:Int): LiveData<BaseResponse<String>> {
      return  repository.changeOrderStatus(status, id, stuId)
    }

}