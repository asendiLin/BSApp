package com.bojue.bsapp.publish

import android.app.Application
import com.bojue.bsapp.order.OrderRepository
import com.bojue.bsapp.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/24.
 * description:
 */
class PublishOrderViewModel @Inject constructor(application: Application,val repository: OrderRepository)
    :BaseViewModel(application) {
    fun publishOrder(){}
}