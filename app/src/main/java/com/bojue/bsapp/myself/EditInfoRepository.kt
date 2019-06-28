package com.bojue.bsapp.myself

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.sendi.base.constance.FAIL_STATU
import com.bojue.bsapp.http.api.MyselfService
import com.sendi.base.data.BaseResponse
import com.bojue.bsapp.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/23.
 * description:
 */
class EditInfoRepository @Inject constructor(val service : MyselfService) {
    private val myTag = "EditInfoRepository"
    val editInfoLiveData = MutableLiveData<BaseResponse<UserModel>>()

    fun editInfo( stuId: Int,  username: String,
                  password: String,  number: String?,
                  classname: String?,  icon: String?,
                  nickname: String?,  phone: String?,
                  signature: String?): LiveData<BaseResponse<UserModel>> {
        service.editInfo(stuId,username,password,number,classname,icon,nickname,phone,signature)
                .enqueue(object : Callback<BaseResponse<UserModel>>{
                    override fun onResponse(call: Call<BaseResponse<UserModel>>?, response: Response<BaseResponse<UserModel>>?) {
                        Log.i(myTag,"response -> ${response?.body()}")
                        if (response?.isSuccessful == true){
                            editInfoLiveData.postValue(response.body())
                        }else{
                            editInfoLiveData.postValue(BaseResponse(null, FAIL_STATU, "修改失败", 0))
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<UserModel>>?, t: Throwable?) {
                        Log.i(myTag,"onFailure -> ${t?.message}")
                        editInfoLiveData.postValue(BaseResponse(null, FAIL_STATU, "网络出错", 0))
                    }
                })


        return editInfoLiveData
    }

}