package com.sendi.order.inject

import android.app.Application
import com.bojue.core.component.BaseComponent

/**
 * create data : 2019/7/2
 * author : sendi
 * description :
 */
object OrderInjector {

    fun inject(application: Application){
        val component = DaggerOrderComponent
                .builder()
                .application(application)
                .build()
        component.inject(application)

        BaseComponent.instance = component
    }

}