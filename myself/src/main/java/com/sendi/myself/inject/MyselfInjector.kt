package com.sendi.myself.inject

import android.app.Application
import com.bojue.core.component.BaseComponent

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
object MyselfInjector {

    fun inject(application: Application){
       val component = DaggerMyselfComponent.builder().application(application).build()
        BaseComponent.instance = component
    }

}