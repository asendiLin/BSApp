package com.sendi.login.inject

import android.app.Application
import com.bojue.core.component.BaseComponent

/**
 * create data : 2019/7/4
 * author : sendi
 * description :
 */
object LoginInjector {

    fun inject(application: Application){

        val component = DaggerLoginComponent.builder().application(application).build()

        BaseComponent.instance = component

    }

}