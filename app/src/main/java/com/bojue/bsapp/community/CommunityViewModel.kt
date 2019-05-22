package com.bojue.bsapp.community

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.bojue.bsapp.event.BitmapEvent
import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.CommunityModel
import com.bojue.bsapp.viewmodel.BaseViewModel
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

    init {
        EventUtil.register(this)
    }

    fun getCommunityList(): LiveData<List<CommunityModel>> {
        return repository.getCommunityList()
    }

    fun publish(content :String,studentId :Int,pic :String,time:String):LiveData<BaseResponse<CommunityModel>>{
        return repository.publish(content, studentId, pic, time)
    }

    @Subscribe
    fun onBitmapEvent(event : BitmapEvent){
        bitmapLiveData.postValue(event)
    }

}