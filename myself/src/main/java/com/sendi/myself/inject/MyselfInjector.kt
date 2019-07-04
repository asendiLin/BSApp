package com.sendi.myself.inject

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import com.bojue.core.component.BaseComponent

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
object MyselfInjector {

    private lateinit var component: BaseComponent

    fun inject(application: Application){
        component = DaggerMyselfComponent.builder().application(application).build()
        BaseComponent.instance = component
    }

    fun viewModelFactory():ViewModelProvider.Factory{
        return component.provideViewModelFactory()
    }
}