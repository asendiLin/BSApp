package com.bojue.bsapp.order

import android.app.Application
import android.arch.lifecycle.LiveData
import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.OrderModel
import com.bojue.bsapp.viewmodel.BaseViewModel
import com.yanzhenjie.album.mvp.BaseView
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/28.
 * description:
 */
class OrderHistoryViewModel @Inject constructor(application: Application,val repository: OrderRepository)
    : BaseViewModel(application) {

    fun getHistoryOrderList(orderType : Int,stuId :Int): LiveData<BaseResponse<List<OrderModel>>> {
        return repository.getHistoryOrderList(orderType, stuId)
    }
}