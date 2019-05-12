package com.bojue.bsapp.app

import android.app.Application
import com.bojue.bsapp.inject.DaggerAppComponent
import com.bojue.bsapp.inject.Injector

/**
 * author: asendi.
 * data: 2019/5/12.
 * description:
 */
class BSApplication :Application() {

    override fun onCreate() {
        super.onCreate()

       Injector.init(this)
    }
}