package com.sendi.community.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import com.sendi.base.data.BaseResponse
import com.sendi.community_export.model.CommunityModel
import com.bojue.core.viewmodel.BaseViewModel
import com.sendi.community.repository.CommunityRepository
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/28.
 * description:
 */
class MyselfCommunityViewModel @Inject constructor(application: Application,val repository: CommunityRepository)
    : BaseViewModel(application) {

    val deleteCommunityLiveData = repository.deleteCommunityLiveData

    fun getCommunityList(username : String) : LiveData<BaseResponse<List<CommunityModel>>>{
        return repository.getSelfCommunityList(username)
    }

    fun deleteCommunity(id : Int){
        repository.deleteCommunity(id)
    }

}