package com.bojue.bsapp.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.bojue.bsapp.constance.FAIL_STATU
import com.bojue.bsapp.http.api.LoginAndRegisterService
import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.LoginResponse
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
class LoginRepository @Inject constructor(val service : LoginAndRegisterService) {

    private val myTag = "LoginRepository"
    private val mLoginLiveData = MutableLiveData<BaseResponse<LoginResponse>>()

    fun login(username : String, password : String): LiveData<BaseResponse<LoginResponse>> {

        service.login(username, password).enqueue(object : Callback<BaseResponse<LoginResponse>> {
            override fun onResponse(call: Call<BaseResponse<LoginResponse>>?, response: Response<BaseResponse<LoginResponse>>?) {

                Log.i(myTag,"onResponse ${response?.body().toString()}")

                if (response?.isSuccessful==true){
                    response.body()?.let { data ->
                        mLoginLiveData.postValue(data)
                    }}else{
                    mLoginLiveData.postValue(BaseResponse(null, FAIL_STATU,"注册失败",0))
                }
            }

            override fun onFailure(call: Call<BaseResponse<LoginResponse>>?, t: Throwable?) {
                Log.i(myTag,"onFailure ${t?.message}")
                mLoginLiveData.postValue(BaseResponse(null, FAIL_STATU,"网络出错",0))
            }
        })

        return mLoginLiveData
    }

}