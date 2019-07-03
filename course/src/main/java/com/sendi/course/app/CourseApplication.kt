package com.sendi.course.app

import android.app.Application
import com.sendi.course.manager.CourseManager

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
class CourseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        CourseManager.init(this)
    }


}