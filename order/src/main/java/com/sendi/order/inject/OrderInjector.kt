package com.sendi.order.inject

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import com.bojue.core.component.BaseComponent

/**
 * create data : 2019/7/2
 * author : sendi
 * description :
 */
object OrderInjector {
    private lateinit var component : OrderComponent
    fun inject(application: Application){
        component = DaggerOrderComponent
                .builder()
                .application(application)
                .build()
        component.inject(application)

        BaseComponent.instance = component
    }

    fun viewModelFactory():ViewModelProvider.Factory{
        return component.provideViewModelFactory()
    }
}