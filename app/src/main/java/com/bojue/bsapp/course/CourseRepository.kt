package com.bojue.bsapp.course

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.bojue.bsapp.constance.FAIL_STATU
import com.bojue.bsapp.constance.SUCCESS_STATU
import com.bojue.bsapp.http.api.CourseService
import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.CourseModel
import com.bojue.bsapp.util.CourseUtil
import com.bojue.bsapp.util.SPUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/29.
 * description:
 */
class CourseRepository @Inject constructor(val service : CourseService,val application: Application) {

    private val myTag = "CourseRepository"

    val courseLiveData = MutableLiveData<BaseResponse<List<CourseModel>>>()

    fun getCourseList(number:String, password:String){
        if (CourseUtil.isShouldLoad()){
            getCoursesFormRemote(number, password)
        }else{
            getCoursesFormLocal()
        }
    }

    private fun getCoursesFormRemote(number:String, password:String){
        Log.i(myTag,"getCoursesFormRemote")
        service.getCourseList(number, password).enqueue(object : Callback<BaseResponse<List<CourseModel>>>{
            override fun onResponse(call: Call<BaseResponse<List<CourseModel>>>?, response: Response<BaseResponse<List<CourseModel>>>?) {
                Log.i(myTag,"onResponse -> ${response?.body()}")
                if (response?.isSuccessful == true){
                    response.body()?.let { data ->
                        courseLiveData.postValue(data)
                        data.data?.let {
                            CourseUtil.saveCourseList(it)
                        }
                    }
                }else{
                    courseLiveData.postValue(BaseResponse(null, FAIL_STATU,"数据获取失败",0))
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<CourseModel>>>?, t: Throwable?) {
                Log.i(myTag,"onFailure -> ${t?.message}")
                courseLiveData.postValue(BaseResponse(null, FAIL_STATU,"网络出错",0))
            }
        })

    }
    private fun getCoursesFormLocal() : List<CourseModel>{
        Log.i(myTag,"getCoursesFormLocal")
        val coursesList = CourseUtil.getCourseList()
        courseLiveData.postValue(BaseResponse(coursesList, SUCCESS_STATU,null,0))
        return coursesList
    }

    private fun saveCourses(courses :  List<CourseModel>){
    }
}