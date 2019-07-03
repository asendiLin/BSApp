package com.sendi.community.inject

import android.app.Application
import com.bojue.core.component.BaseComponent

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
object CommunityInjector {


    fun inject(application: Application){
        val communityComponent = DaggerCommunityComponent.builder().application(application).build()
        BaseComponent.instance = communityComponent
    }


}