package com.bojue.bsapp.community

import android.app.Application
import android.arch.lifecycle.LiveData
import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.CommunityModel
import com.bojue.core.viewmodel.BaseViewModel
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