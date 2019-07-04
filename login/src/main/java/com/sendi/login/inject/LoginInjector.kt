package com.sendi.login.inject

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import com.bojue.core.component.BaseComponent

/**
 * create data : 2019/7/4
 * author : sendi
 * description :
 */
object LoginInjector {

    private lateinit var component : BaseComponent

    fun inject(application: Application){

        component = DaggerLoginComponent.builder().application(application).build()

        BaseComponent.instance = component

    }

    fun viewModelFactory() : ViewModelProvider.Factory{
        return component.provideViewModelFactory()
    }
}