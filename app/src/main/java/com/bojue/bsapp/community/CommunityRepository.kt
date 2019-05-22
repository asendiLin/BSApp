package com.bojue.bsapp.community

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.bojue.bsapp.http.api.CommunityService
import com.bojue.bsapp.model.CommunityModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/12.
 * description:
 */
class CommunityRepository @Inject constructor(val service :CommunityService) {

    fun getCommunityList(/*待传参数*/): LiveData<List<CommunityModel>>{

        val liveData = MutableLiveData<List<CommunityModel>>()

        //TODO:通过在Retrofit回调，post数据

        return liveData
    }

}