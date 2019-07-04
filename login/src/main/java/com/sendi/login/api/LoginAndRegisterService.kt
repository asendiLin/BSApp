package com.sendi.login.api

import com.sendi.base.data.BaseResponse
import com.sendi.login.model.LoginResponse
import com.sendi.login.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
interface LoginAndRegisterService {

    @FormUrlEncoded
    @POST("Student/register.action")
    fun register(@Field("username") username: String, @Field("password") password: String) : Call<BaseResponse<RegisterResponse>>

    @FormUrlEncoded
    @POST("Student/login.action")
    fun login(@Field("username") username: String, @Field("password") password: String): Call<BaseResponse<LoginResponse>>
}