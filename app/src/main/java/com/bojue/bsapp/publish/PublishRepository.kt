package com.bojue.bsapp.publish

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.bojue.bsapp.model.PublishModel
import javax.inject.Inject

/**
 * Created by lizheng on 2019/5/13.
 */
class PublishRepository @Inject constructor() {

    fun publishData():LiveData<PublishModel>{
        val liveData = MutableLiveData<PublishModel>()

        return liveData
    }
}