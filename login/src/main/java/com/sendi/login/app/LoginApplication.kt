package com.sendi.login.app

import android.app.Application
import com.sendi.login.manager.LoginManager

/**
 * create data : 2019/7/4
 * author : sendi
 * description :
 */
class LoginApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LoginManager.init(this)
    }

}