package com.bojue.bsapp.http.api

import com.sendi.base.data.BaseResponse
import com.sendi.user.UserModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
interface MyselfService {

    @FormUrlEncoded
    @POST("Student/saveInfo.action")
    fun editInfo(@Field("id") stuId: Int, @Field("username") username: String,
                 @Field("password") password: String, @Field("number") number: String?,
                 @Field("classname") classname: String?, @Field("icon") icon: String?,
                 @Field("nickname") nickname: String?, @Field("phone") phone: String?,
                 @Field("signature") signature:String?): Call<BaseResponse<com.sendi.user.UserModel>>

}