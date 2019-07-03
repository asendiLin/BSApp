package com.sendi.course.viewmodel

import android.app.Application
import com.bojue.core.viewmodel.BaseViewModel
import com.sendi.course.repository.CourseRepository
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/29.
 * description:
 */
class CourseViewModel @Inject constructor(application: Application,val repository: CourseRepository)
    : BaseViewModel(application) {

    val courseLiveData = repository.courseLiveData

    fun getCourses(number:String, password:String){
        repository.getCourseList(number, password)
    }

}