package com.sendi.course_export.manager

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider
import com.sendi.course_export.model.CourseModel

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
interface ICourseManager : IProvider {

    fun isCurrentWeekHas(period : String ,currentWeek : Int):Boolean

    fun getCourseList():List<CourseModel>

    fun getTodayCourseCount(week : Int):Int

    fun isShouldLoad():Boolean

    fun saveCourseList(courseList:List<CourseModel>)

    fun shouldRefresh(context: Context):Boolean

    fun saveRefresh(context: Context)

    fun getsSection(courseModel: CourseModel):Int
}