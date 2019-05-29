package com.bojue.bsapp.order

import android.app.Application
import android.arch.lifecycle.LiveData
import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.viewmodel.BaseViewModel
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