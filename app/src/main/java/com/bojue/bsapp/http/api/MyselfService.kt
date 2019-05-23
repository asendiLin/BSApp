package com.bojue.bsapp.http.api

import com.bojue.bsapp.model.BaseResponse
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
interface MyselfService {

    @FormUrlEncoded
    @POST("")
    fun editInfo() :Call<BaseResponse<Any?>>

}