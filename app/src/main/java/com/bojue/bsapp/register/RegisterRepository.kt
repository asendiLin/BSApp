package com.bojue.bsapp.register

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.sendi.base.constance.FAIL_STATU
import com.bojue.bsapp.http.api.LoginAndRegisterService
import com.sendi.base.data.BaseResponse
import com.bojue.bsapp.model.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class RegisterRepository @Inject constructor(val service : LoginAndRegisterService){
    private val myTag = "RegisterRepository"
    private val mRegisterLiveData = MutableLiveData<BaseResponse<RegisterResponse>>()

    fun register(username : String, password : String): LiveData<BaseResponse<RegisterResponse>> {

        service.register(username, password).enqueue(object : Callback<BaseResponse<RegisterResponse>>{
            override fun onResponse(call: Call<BaseResponse<RegisterResponse>>?, response: Response<BaseResponse<RegisterResponse>>?) {
                Log.i(myTag,"onResponse ${response?.body().toString()}")
                if (response?.isSuccessful==true){
                    response.body()?.let { data ->
                        mRegisterLiveData.postValue(data)
                    }}else{
                    mRegisterLiveData.postValue(BaseResponse(null, FAIL_STATU, "注册失败", 0))
                }

            }

            override fun onFailure(call: Call<BaseResponse<RegisterResponse>>?, t: Throwable?) {
                Log.i(myTag,"onFailure ${t?.message}")
                mRegisterLiveData.postValue(BaseResponse(null, FAIL_STATU, "网络出错", 0))
            }
        })
        return  mRegisterLiveData
    }

}