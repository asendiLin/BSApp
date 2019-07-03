package com.sendi.course.manager

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.bojue.bs.db.DaoSession
import com.bojue.bs.db.DaoMaster
import com.sendi.db_library.course.CourseEntity
import com.sendi.base.util.SPUtils
import com.sendi.course_export.manager.ICourseManager
import com.sendi.course_export.model.CourseModel
import com.sendi.db_library.db.CourseDB
import com.sendi.db_library.db.CourseDB.COURSE


/**
 * author: asendi.
 * data: 2019/5/29.
 * description:
 */
object CourseUtil : ICourseManager {
    private const val myTag = "CourseUtil"

    const val SHOULD_REFRESH = 1

    const val SHOULD_NOT_REFRESH = 2

    override fun init(context: Context?) {
        Log.i(myTag,"context is $context")
        context?.let {
            CourseDB.init(context)
        }
    }


    /**
     * 本周是否需要上课
     */
    override fun isCurrentWeekHas(period: String, currentWeek: Int): Boolean {
        if (period.contains("|")) {
            val weekContent = period.split("|")
            val weekPeriod = weekContent[0].substring(1, weekContent[0].length - 1)
            val weeks = weekPeriod.split("-")
            val isOdd = weekContent[1].contains("单")
            if (isOdd) {
                return (currentWeek % 2 == 1) &&
                        (currentWeek >= weeks[0].toInt() && currentWeek <= weeks[1].toInt())
            } else {
                return (currentWeek % 2 == 0) &&
                        (currentWeek >= weeks[0].toInt() && currentWeek <= weeks[1].toInt())
            }

        } else {
            val weekPeriod = period.substring(1, period.indexOf("周"))
            val weeks = weekPeriod.split("-")
            return (currentWeek >= weeks[0].toInt()) and (currentWeek <= weeks[1].toInt())
        }
    }

    override fun getCourseList(): List<CourseModel> {
        val courseList = ArrayList<CourseModel>(32)
        val gson = Gson()

        val list = CourseDB.getCourseEntityList()
        list.forEach { data ->
            val courseModel = gson.fromJson(data.courseInfo, CourseModel::class.java)
            courseList.add(courseModel)
        }

        return courseList
    }

    override fun getTodayCourseCount(week: Int): Int {
        val list = CourseDB.getCourseEntityList()
        val gson = Gson()
        var count = 0
        list.forEach { data ->
            val courseModel = gson.fromJson(data.courseInfo, CourseModel::class.java)
            if (week == courseModel.week) {
                count++
            }
        }
        return count
    }

    override fun isShouldLoad(): Boolean {
        val list = CourseDB.getCourseEntityList()
        return list.isEmpty()
    }

    override fun saveCourseList(courseList: List<CourseModel>) {
        val gson = Gson()
        courseList.forEach { data ->
            val json = gson.toJson(data, CourseModel::class.java)
            val entity = CourseEntity()
            entity.courseInfo = json
            CourseDB.saveCourse(entity)
        }

    }

    override fun shouldRefresh(context: Context): Boolean {
        val flag = SPUtils.getInt(context, COURSE, SHOULD_REFRESH) ?: SHOULD_NOT_REFRESH
        Log.i("shouldRefresh", "flag == $flag")
        return flag == SHOULD_REFRESH
    }

    override fun saveRefresh(context: Context) {
        SPUtils.saveInt(context, COURSE, SHOULD_NOT_REFRESH)
    }

    override fun getsSection(courseModel: CourseModel): Int {

        val endSection = courseModel.sectionEnd

        return endSection / 2 - 1
    }

}