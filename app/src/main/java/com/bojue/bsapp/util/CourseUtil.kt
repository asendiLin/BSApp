package com.bojue.bsapp.util

import android.content.Context
import android.util.Log
import com.bojue.bsapp.model.CourseModel
import com.google.gson.Gson
import com.bojue.bs.db.DaoSession
import com.bojue.bs.db.DaoMaster
import com.bojue.bsapp.course.db.CourseEntity


/**
 * author: asendi.
 * data: 2019/5/29.
 * description:
 */
object CourseUtil {


    const val DB_NAME = "bs.db"
    const val COURSE = "course"
    const val SHOULD_REFRESH = 1
    const val SHOULD_NOT_REFRESH = 2
    private lateinit var mDevOpenHelper: DaoMaster.DevOpenHelper
    private lateinit var mDaoMaster: DaoMaster
    private lateinit var mDaoSession: DaoSession

    fun init(context:Context){
        mDevOpenHelper = DaoMaster.DevOpenHelper(context,DB_NAME)
        mDaoMaster = DaoMaster(mDevOpenHelper.writableDatabase)
        mDaoSession = mDaoMaster.newSession()
    }

    /**
     * 本周是否需要上课
     */
    fun isCurrentWeekHas(period : String ,currentWeek : Int):Boolean{
        if (period.contains("|")){
            val weekContent = period.split("|")
            val weekPeriod = weekContent[0].substring(1, weekContent[0].length-1)
            val weeks = weekPeriod.split("-")
            val isOdd = weekContent[1].contains("单")
            if (isOdd){
                return (currentWeek%2==1) &&
                        (currentWeek >= weeks[0].toInt() && currentWeek<= weeks[1].toInt())
            }else{
                return (currentWeek%2==0) &&
                        (currentWeek >= weeks[0].toInt() && currentWeek<= weeks[1].toInt())
            }

        }else{
            val weekPeriod = period.substring(1, period.indexOf("周"))
            val weeks = weekPeriod.split("-")
            return (currentWeek >= weeks[0].toInt()) and (currentWeek<= weeks[1].toInt())
        }
    }

    fun getCourseList():List<CourseModel>{
        val courseList = ArrayList<CourseModel>(32)
        val gson = Gson()

        val list = mDaoSession.courseEntityDao.loadAll()
        list.forEach {data->
            val courseModel = gson.fromJson(data.courseInfo, CourseModel::class.java)
            courseList.add(courseModel)
        }

        return courseList
    }

    fun getTodayCourseCount(week : Int):Int{
        val list = mDaoSession.courseEntityDao.loadAll()
        val gson = Gson()
        var count = 0
        list.forEach {data->
            val courseModel = gson.fromJson(data.courseInfo, CourseModel::class.java)
            if (week == courseModel.week){
                count++
            }
        }
        return count
    }

    fun isShouldLoad():Boolean{
        val list = mDaoSession.courseEntityDao.loadAll()
        return list.size == 0
    }

    fun saveCourseList(courseList:List<CourseModel>){
        val gson = Gson()
        courseList.forEach {data ->
            val json = gson.toJson(data, CourseModel::class.java)
            val entity = CourseEntity()
            entity.courseInfo = json
            mDaoSession.courseEntityDao.insert(entity)
        }

    }

    fun shouldRefresh(context: Context):Boolean{
       val flag = SPUtils.getInt(context, COURSE, SHOULD_REFRESH)?: SHOULD_NOT_REFRESH
        Log.i("shouldRefresh","flag == $flag")
        return flag == SHOULD_REFRESH
    }
    fun saveRefresh(context: Context){
        SPUtils.saveInt(context, COURSE, SHOULD_NOT_REFRESH)
    }

    fun getsSection(courseModel: CourseModel):Int{

        val endSection =courseModel.sectionEnd

        return endSection/2 - 1
    }

}