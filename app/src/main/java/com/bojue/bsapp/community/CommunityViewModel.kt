package com.bojue.bsapp.community

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.bojue.bsapp.event.BitmapEvent
import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.CommunityModel
import com.bojue.core.viewmodel.BaseViewModel
import com.bojue.core.event.EventUtil
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/10.
 * description:
 */
class CommunityViewModel @Inject constructor(application: Application,val repository: CommunityRepository)
    :BaseViewModel(application) {

    val bitmapLiveData = MutableLiveData<BitmapEvent>()

    val zanLiveData = repository.commnunityZanLiveData
    val commentLiveData = repository.commentListLiveData
    val publishCommentLiveData = repository.publishCommentListLiveData
    init {
        EventUtil.register(this)
    }

    fun getCommunityList(stuId:Int): LiveData<BaseResponse<List<CommunityModel>>>{
        return repository.getCommunityList(stuId)
    }

    fun publish(content :String,studentId :Int,pic :String,time:String):LiveData<BaseResponse<CommunityModel>>{
        return repository.publish(content, studentId, pic, time)
    }

    fun postZan(id :Int,stuId : Int){
        repository.postLike(id, stuId)
    }

    fun getCommentList(id :Int){
        repository.getCommentList(id)
    }

    fun publishComment(content:String,stuId:Int,origin:Int){
        repository.publishComment(content, stuId, origin)
    }

    @Subscribe
    fun onBitmapEvent(event : BitmapEvent){
        bitmapLiveData.postValue(event)
    }

}