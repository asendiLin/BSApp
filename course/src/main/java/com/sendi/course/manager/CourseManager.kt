package com.sendi.course.manager

import android.app.Application
import android.content.Context
import com.sendi.course.inject.CourseInjector

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
object CourseManager {

    fun init(application: Application){
        CourseInjector.inject(application)
        CourseUtil.init(application)
    }

}