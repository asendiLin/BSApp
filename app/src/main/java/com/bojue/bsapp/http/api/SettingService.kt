package com.bojue.bsapp.http.api

import com.sendi.base.data.BaseResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
interface SettingService {

    @FormUrlEncoded
    @POST("Student/valid.action")
    fun indentyfy(@Field("username") username: String,
                  @Field("number") number: String,
                  @Field("password") password: String): Call<BaseResponse<String>>

}