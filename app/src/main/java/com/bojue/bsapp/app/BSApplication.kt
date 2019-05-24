package com.bojue.bsapp.app

import android.app.Application
import com.bojue.bsapp.inject.DaggerAppComponent
import com.bojue.bsapp.inject.Injector
import com.bojue.bsapp.util.UserManager

/**
 * author: asendi.
 * data: 2019/5/12.
 * description:
 */
class BSApplication :Application() {

    override fun onCreate() {
        super.onCreate()

       Injector.init(this)
        UserManager.init(this)
    }
}