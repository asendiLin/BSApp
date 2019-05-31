package com.bojue.bsapp.community

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.bojue.bsapp.callback.CommonCallback
import com.bojue.bsapp.constance.FAIL_STATU
import com.bojue.bsapp.constance.SUCCESS_STATU
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

    private val mCommunityListLiveData = MutableLiveData<BaseResponse<List<CommunityModel>>>()
    val selfCommunityListLiveData = MutableLiveData<BaseResponse<List<CommunityModel>>>()
    val commnunityZanLiveData =  MutableLiveData<BaseResponse<Any>>()
    val deleteCommunityLiveData =  MutableLiveData<BaseResponse<Any>>()
    val commentListLiveData = MutableLiveData<BaseResponse<List<CommunityModel>>>()
    val publishCommentListLiveData = MutableLiveData<BaseResponse<CommunityModel>>()
    private val mPublishLiveData = MutableLiveData<BaseResponse<CommunityModel>>()
    fun getCommunityList(stuId : Int): LiveData<BaseResponse<List<CommunityModel>>>{

        service.getCommunityList(stuId).enqueue(object : Callback<BaseResponse<List<CommunityModel>>>{
            override fun onFailure(call: Call<BaseResponse<List<CommunityModel>>>?, t: Throwable?) {
                Log.i(myTag,"onFailure -> message = ${t?.message}")
                mCommunityListLiveData.postValue(BaseResponse(null, FAIL_STATU,"网络出错",100))
            }

            override fun onResponse(call: Call<BaseResponse<List<CommunityModel>>>?, response: Response<BaseResponse<List<CommunityModel>>>?) {
                Log.i(myTag,"onResponse -> size = ${response?.body()?.data}")
                response?.let {
                    if (response.isSuccessful){
                        mCommunityListLiveData.postValue(response.body())
                    }else {
                        mCommunityListLiveData.postValue(BaseResponse(null, FAIL_STATU,response.errorBody().string(),100))
                    }
                }

            }
        })

        return mCommunityListLiveData
    }

    fun getSelfCommunityList(username :String): LiveData<BaseResponse<List<CommunityModel>>>{

        service.getSelfCommunityList(username).enqueue(object :Callback<BaseResponse<List<CommunityModel>>>{
            override fun onFailure(call: Call<BaseResponse<List<CommunityModel>>>?, t: Throwable?) {
                Log.i(myTag,"onFailure -> ${t?.message}")
                selfCommunityListLiveData.postValue(BaseResponse(null, FAIL_STATU,"网络出错",100))
            }

            override fun onResponse(call: Call<BaseResponse<List<CommunityModel>>>?, response: Response<BaseResponse<List<CommunityModel>>>?) {
                Log.i(myTag,"onResponse -> ${response?.body()}")
                response?.let {
                    if (response.isSuccessful){
                        selfCommunityListLiveData.postValue(response.body())
                    }else {
                        selfCommunityListLiveData.postValue(BaseResponse(null, FAIL_STATU,response.errorBody().string(),100))
                    }
                }
            }
        }
        )

        return selfCommunityListLiveData
    }

    fun publish(content :String,studentId :Int,pic :String,time:String):LiveData<BaseResponse<CommunityModel>>{
        service.publish(content, studentId, pic, time).enqueue(object : Callback<BaseResponse<CommunityModel>>{
            override fun onResponse(call: Call<BaseResponse<CommunityModel>>?, response: Response<BaseResponse<CommunityModel>>?) {
                Log.i(myTag,"onResponse -> ${response?.body()?.status}")
                response?.let {
                    if (response.isSuccessful){
                        mPublishLiveData.postValue(response.body())
                    }else {
                        mPublishLiveData.postValue(BaseResponse(null, FAIL_STATU,response.errorBody().string(),100))
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<CommunityModel>>?, t: Throwable?) {
                Log.i(myTag,"onFailure -> ${t?.message}")
                mPublishLiveData.postValue(BaseResponse(null, FAIL_STATU,"网络出错",100))
            }
        })
        return mPublishLiveData
    }

    fun postLike(id:Int,stuId:Int){
        service.postLike(id,stuId).enqueue(object : Callback<BaseResponse<Any>>{
            override fun onResponse(call: Call<BaseResponse<Any>>?, response: Response<BaseResponse<Any>>?) {
                Log.i(myTag,"onResponse -> ${response?.body()}")

                if (response?.isSuccessful == true){
                    response.body()?.let {data ->
                        commnunityZanLiveData.postValue(data)
                    }

                }else{
                    commnunityZanLiveData.postValue(BaseResponse(null, FAIL_STATU,"数据出错",0))
                }

            }

            override fun onFailure(call: Call<BaseResponse<Any>>?, t: Throwable?) {
                Log.i(myTag,"onFailure -> ${t?.message}")
                commnunityZanLiveData.postValue(BaseResponse(null, FAIL_STATU,"${t?.message}",0))
            }
        })
    }

    fun getCommentList(id : Int){
        service.getCommentList(id).enqueue(object :CommonCallback<List<CommunityModel>>(){
            override fun onSuccess(data: List<CommunityModel>?) {
                Log.i(myTag,"onSuccess -> data = $data")
                commentListLiveData.postValue(BaseResponse(data, SUCCESS_STATU,null,0))
            }

            override fun onFail(message: String) {
                Log.i(myTag,"onFail -> message = $message")
                commentListLiveData.postValue(BaseResponse(null, FAIL_STATU,message,0))
            }
        })
    }

    fun publishComment(content : String,stuId:Int,origin:Int){
        service.publishComment(content,stuId,origin).enqueue(object : CommonCallback<CommunityModel>(){
            override fun onSuccess(data: CommunityModel?) {
                Log.i(myTag,"onSuccess -> data = $data")
                publishCommentListLiveData.postValue(BaseResponse(data, SUCCESS_STATU,null,0))
            }

            override fun onFail(message: String) {
                Log.i(myTag,"onFail -> message = $message")
                publishCommentListLiveData.postValue(BaseResponse(null, FAIL_STATU,message,0))
            }
        })
    }

    fun deleteCommunity(id :Int){
        service.deleteCommunity(id).enqueue(object  : CommonCallback<Any>(){
            override fun onSuccess(data: Any?) {
                Log.i(myTag,"onSuccess -> data = $data")
                deleteCommunityLiveData.postValue(BaseResponse(data, SUCCESS_STATU,null,0))
            }

            override fun onFail(message: String) {
                Log.i(myTag,"onFail -> message = $message")
                deleteCommunityLiveData.postValue(BaseResponse(null, FAIL_STATU,message,0))
            }
        })
    }
}