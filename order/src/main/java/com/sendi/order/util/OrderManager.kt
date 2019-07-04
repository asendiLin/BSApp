package com.sendi.order.util

import android.app.Application
import com.sendi.order.inject.OrderInjector

/**
 * create data : 2019/7/4
 * author : sendi
 * description :
 */
object OrderManager {


    fun init(application: Application){
        OrderInjector.inject(application)
    }
}