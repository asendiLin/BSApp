package com.sendi.db_library.db

import android.content.Context
import com.bojue.bs.db.DaoMaster
import com.bojue.bs.db.DaoSession
import com.sendi.db_library.course.CourseEntity

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
object CourseDB {

    const val DB_NAME = "bs.db"
    const val COURSE = "course"
    const val SHOULD_REFRESH = 1
    const val SHOULD_NOT_REFRESH = 2
    private lateinit var mDevOpenHelper: DaoMaster.DevOpenHelper
    private lateinit var mDaoMaster: DaoMaster
    private lateinit var mDaoSession: DaoSession

    fun init(context: Context){
        mDevOpenHelper = DaoMaster.DevOpenHelper(context,DB_NAME)
        mDaoMaster = DaoMaster(mDevOpenHelper.writableDatabase)
        mDaoSession = mDaoMaster.newSession()
    }

    fun getCourseEntityList() : List<CourseEntity>{

        val list = mDaoSession.courseEntityDao.loadAll()

        return list
    }

    fun saveCourseList(courseList : List<CourseEntity>){
        courseList.forEach {data->
            mDaoSession.courseEntityDao.insert(data)
        }
    }

    fun saveCourse(course : CourseEntity){
        mDaoSession.courseEntityDao.insert(course)
    }

    fun deleteCourse(course : CourseEntity){
        mDaoSession.courseEntityDao.delete(course)
    }
}