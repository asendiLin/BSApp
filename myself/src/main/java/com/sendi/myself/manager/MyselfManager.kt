package com.sendi.myself.manager

import android.app.Application
import com.sendi.myself.inject.MyselfInjector

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
object MyselfManager {

    fun init(application: Application){
        MyselfInjector.inject(application)
    }

}