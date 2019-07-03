package com.bojue.bsapp.myself

import android.app.Application
import android.arch.lifecycle.LiveData
import com.sendi.community.repository.CommunityRepository
import com.sendi.base.data.BaseResponse
import com.sendi.community.model.CommunityModel
import com.bojue.core.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/10.
 * description:
 */
class MyselfViewModel @Inject constructor(application: Application,val repository: CommunityRepository)
    : BaseViewModel(application){

    val myselfCommunityLiveData = repository.selfCommunityListLiveData

    fun getCommunityList(username : String) : LiveData<BaseResponse<List<com.sendi.community.model.CommunityModel>>> {
        return repository.getSelfCommunityList(username)
    }

}