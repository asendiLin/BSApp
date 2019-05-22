package com.bojue.bsapp.http.api

import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.CommunityModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
interface CommunityService {
//    Treehole:

    @FormUrlEncoded
    @POST("Treehole/publish.action")
    fun publish(@Field("content")content :String,@Field("studentId")studentId :Int,
                @Field("pic")pic :String,@Field("time") time:String):Call<BaseResponse<CommunityModel>>

    @POST("Treehole/findTreehole.action")
    fun getCommunityList():Call<BaseResponse<List<CommunityModel>>>
}