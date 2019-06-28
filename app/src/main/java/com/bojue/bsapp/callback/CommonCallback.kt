package com.bojue.bsapp.callback

import android.util.Log
import com.sendi.base.constance.SUCCESS_STATU
import com.sendi.base.data.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * author: asendi.
 * data: 2019/5/29.
 * description:
 */
abstract class CommonCallback<T> : Callback<BaseResponse<T>> {

    private val myTag ="CommonCallback"

    override fun onFailure(call: Call<BaseResponse<T>>?, t: Throwable?) {
        onFail("${t?.message}")
        Log.i(myTag,"onFailure message ->${t?.message}")
    }

    override fun onResponse(call: Call<BaseResponse<T>>?, response: Response<BaseResponse<T>>?) {
        Log.i(myTag,"onResponse response ->${response?.body()}")
        if (response?.isSuccessful == true){
            val body = response.body()
            if (body != null){
                if (body.status == SUCCESS_STATU || body.status == 0){
                    val data = body.data
                    onSuccess(data)
                }else{
                    onFail("${body.message}")
                }
            }else{
                onFail("获取数据失败")
            }
        }
    }

    abstract fun onSuccess(data : T?)
    abstract fun onFail(message : String)

}