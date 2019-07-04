package com.sendi.login.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.sendi.base.constance.FAIL_STATU
import com.sendi.base.data.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class RegisterRepository @Inject constructor(val service : com.sendi.login.api.LoginAndRegisterService){
    private val myTag = "RegisterRepository"
    private val mRegisterLiveData = MutableLiveData<BaseResponse<com.sendi.login.model.RegisterResponse>>()

    fun register(username : String, password : String): LiveData<BaseResponse<com.sendi.login.model.RegisterResponse>> {

        service.register(username, password).enqueue(object : Callback<BaseResponse<com.sendi.login.model.RegisterResponse>>{
            override fun onResponse(call: Call<BaseResponse<com.sendi.login.model.RegisterResponse>>?, response: Response<BaseResponse<com.sendi.login.model.RegisterResponse>>?) {
                Log.i(myTag,"onResponse ${response?.body().toString()}")
                if (response?.isSuccessful==true){
                    response.body()?.let { data ->
                        mRegisterLiveData.postValue(data)
                    }}else{
                    mRegisterLiveData.postValue(BaseResponse(null, FAIL_STATU, "注册失败", 0))
                }

            }

            override fun onFailure(call: Call<BaseResponse<com.sendi.login.model.RegisterResponse>>?, t: Throwable?) {
                Log.i(myTag,"onFailure ${t?.message}")
                mRegisterLiveData.postValue(BaseResponse(null, FAIL_STATU, "网络出错", 0))
            }
        })
        return  mRegisterLiveData
    }

}