package com.sendi.myself.app

import android.app.Application
import com.sendi.myself.manager.MyselfManager

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
class MyselfApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MyselfManager.init(this)
    }

}