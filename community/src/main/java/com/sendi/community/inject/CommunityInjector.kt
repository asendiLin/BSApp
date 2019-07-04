package com.sendi.community.inject

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import com.bojue.core.component.BaseComponent

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
object CommunityInjector {

    private lateinit var communityComponent : BaseComponent

    fun inject(application: Application){
        communityComponent = DaggerCommunityComponent.builder().application(application).build()
        BaseComponent.instance = communityComponent
    }

    fun viewModelFactory():ViewModelProvider.Factory{
        return communityComponent.provideViewModelFactory()
    }

}