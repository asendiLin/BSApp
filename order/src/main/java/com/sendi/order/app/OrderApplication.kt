package com.sendi.order.app

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.sendi.order.inject.OrderInjector

/**
 * create data : 2019/7/2
 * author : sendi
 * description :
 */
class OrderApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initARouter(this)

        inject(this)
    }

    private fun inject(orderApplication: OrderApplication) {
        OrderInjector.inject(orderApplication)
    }

    private fun initARouter(app : Application) {
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.printStackTrace()
        ARouter.init(app)
    }

}