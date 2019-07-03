package com.sendi.course.inject

import android.app.Application
import com.bojue.core.component.BaseComponent

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
object CourseInjector {

    fun inject(application: Application){
        val component = DaggerCourseComponent.builder().application(application).build()
        BaseComponent.instance = component
    }

}