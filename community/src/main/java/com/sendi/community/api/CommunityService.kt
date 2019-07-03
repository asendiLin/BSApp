package com.sendi.community.api

import com.sendi.base.data.BaseResponse
import com.sendi.community_export.model.CommunityModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
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
    fun publish(@Field("content") content: String, @Field("studentId") studentId: Int,
                @Field("pic") pic: String, @Field("time") time: String): Call<BaseResponse<CommunityModel>>

    @FormUrlEncoded
    @POST("Treehole/findTreehole.action")
    fun getCommunityList(@Field("studentId") stuId : Int): Call<BaseResponse<List<CommunityModel>>>

    @FormUrlEncoded
    @POST("Student/getMyTreehole.action")
    fun getSelfCommunityList(@Field("username") username: String): Call<BaseResponse<List<CommunityModel>>>

    @FormUrlEncoded
    @POST("Support/like.action")
    fun postLike(@Field("id") id: Int, @Field("studentId") studentId: Int):Call<BaseResponse<Any>>

    @FormUrlEncoded
    @POST("Treehole/findReply.action")
    fun getCommentList(@Field("id") id :Int): Call<BaseResponse<List<CommunityModel>>>

    @FormUrlEncoded
    @POST("Treehole/publish.action")
    fun publishComment(@Field("content") content: String, @Field("studentId") studentId: Int,
                       @Field("origin")origin:Int)
            : Call<BaseResponse<CommunityModel>>

    @FormUrlEncoded
    @POST("Treehole/deleteById.action")
    fun deleteCommunity(@Field("id")id :Int)  : Call<BaseResponse<Any>>
}