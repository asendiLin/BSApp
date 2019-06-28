package com.bojue.bsapp.http.api

import com.sendi.base.data.BaseResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

/**
 * author: asendi.
 * data: 2019/5/23.
 * description:
 */
interface CommonService {

    @Multipart
    @POST("")
    fun uploadPicWithParam(@PartMap params : Map<String,String>, @Part imageFile : MultipartBody.Part)

    @Multipart
    @POST("Student/uploadFile.action")
    fun uploadPic(@Part imageFile : MultipartBody.Part) : Call<BaseResponse<String>>
}