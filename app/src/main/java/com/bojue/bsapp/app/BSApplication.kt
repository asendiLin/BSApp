package com.bojue.bsapp.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.sendi.community.manager.CommunityManager
import com.sendi.course.manager.CourseManager
import com.sendi.login.manager.LoginManager
import com.sendi.myself.manager.MyselfManager
import com.sendi.order.util.OrderManager

/**
 * author: asendi.
 * data: 2019/5/12.
 * description:
 */
class BSApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initARouter(this)
        OrderManager.init(this)
        CommunityManager.init(this)
        MyselfManager.init(this)
        CourseManager.init(this)
        LoginManager.init(this)
    }

    private fun initARouter(app : Application) {
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.printStackTrace()
        ARouter.init(app)
    }
}