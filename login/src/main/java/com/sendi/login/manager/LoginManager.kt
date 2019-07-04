package com.sendi.login.manager

import android.app.Application
import com.sendi.login.inject.LoginInjector

/**
 * create data : 2019/7/4
 * author : sendi
 * description :
 */
object LoginManager {

    fun init(application: Application){

        LoginInjector.inject(application)

    }

}