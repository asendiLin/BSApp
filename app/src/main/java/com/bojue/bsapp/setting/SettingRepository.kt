package com.bojue.bsapp.setting

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.bojue.bsapp.http.api.SettingService
import com.bojue.bsapp.model.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class SettingRepository @Inject constructor(val service: SettingService) {
    private val myTag = "SettingRepository"
    private val identifyLiveData = MutableLiveData<BaseResponse<String>>()

    fun identify(username: String, number: String, password: String): LiveData<BaseResponse<String>> {

        service.indentyfy(username, number, password).enqueue(object :Callback<BaseResponse<String>>{
            override fun onResponse(call: Call<BaseResponse<String>>?, response: Response<BaseResponse<String>>?) {
                Log.i(myTag,"onResponse -> status=${response?.body()?.status}")
            }

            override fun onFailure(call: Call<BaseResponse<String>>?, t: Throwable?) {
                Log.i(myTag,"onFailure -> ${t?.message}")
            }
        })

        return identifyLiveData
    }

}