//package com.bojue.bsapp.inject
//
//import android.app.Application
//import com.bojue.bsapp.app.BSApplication
//import com.bojue.core.component.BaseComponent
//
///**
// * author: asendi.
// * data: 2019/5/12.
// * description:
// */
//object Injector {
//
//    private lateinit var mAppComponent : AppComponent
//
//    fun init(application: BSApplication){
//        mAppComponent = DaggerAppComponent
//                .builder()
//                .application(application)
//                .build()
//        mAppComponent.inject(application)
//
//        BaseComponent.instance = mAppComponent
//    }
//
//}