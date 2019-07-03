package com.sendi.community.app

import android.app.Application
import com.sendi.community.manager.CommunityManager

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
class CommunityApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CommunityManager.init(this)
    }

}