package com.sendi.order.app

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

/**
 * create data : 2019/7/2
 * author : sendi
 * description :
 */
class OrderApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initARouter(this)
    }

    private fun initARouter(app : Application) {
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.printStackTrace()
        ARouter.init(app)
    }

}