package com.bojue.bsapp.community

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.bojue.bsapp.http.api.CommunityService
import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.CommunityModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/12.
 * description:
 */
class CommunityRepository @Inject constructor(val service :CommunityService) {

    private val myTag = "CommunityRepository"

    private val mCommunityListLiveData = MutableLiveData<List<CommunityModel>>()
    private val mCommnunityZanLiveData =  MutableLiveData<List<CommunityModel>>()
    private val mPublishLiveData = MutableLiveData<BaseResponse<CommunityModel>>()
    fun getCommunityList(): LiveData<List<CommunityModel>>{

        service.getCommunityList().enqueue(object : Callback<BaseResponse<List<CommunityModel>>>{
            override fun onFailure(call: Call<BaseResponse<List<CommunityModel>>>?, t: Throwable?) {
                Log.i(myTag,"onFailure -> message = ${t?.message}")
            }

            override fun onResponse(call: Call<BaseResponse<List<CommunityModel>>>?, response: Response<BaseResponse<List<CommunityModel>>>?) {
                Log.i(myTag,"onResponse -> size = ${response?.body()?.data?.size}")
            }
        })

        return mCommunityListLiveData
    }

    fun getSelfCommunityList(){}

    fun publish(content :String,studentId :Int,pic :String,time:String):LiveData<BaseResponse<CommunityModel>>{
        service.publish(content, studentId, pic, time).enqueue(object : Callback<BaseResponse<CommunityModel>>{
            override fun onResponse(call: Call<BaseResponse<CommunityModel>>?, response: Response<BaseResponse<CommunityModel>>?) {
                Log.i(myTag,"onResponse -> ${response?.body()?.status}")
            }

            override fun onFailure(call: Call<BaseResponse<CommunityModel>>?, t: Throwable?) {
                Log.i(myTag,"onFailure -> ${t?.message}")
            }
        })

        return mPublishLiveData
    }
}