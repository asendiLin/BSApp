package com.sendi.course.api

import com.sendi.base.data.BaseResponse
import com.sendi.course_export.model.CourseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * author: asendi.
 * data: 2019/5/29.
 * description:
 */
interface CourseService {

    @FormUrlEncoded
    @POST("Student/getCourse.action")
    fun getCourseList(@Field("number")number : String,@Field("password") password :String):Call<BaseResponse<List<CourseModel>>>

}