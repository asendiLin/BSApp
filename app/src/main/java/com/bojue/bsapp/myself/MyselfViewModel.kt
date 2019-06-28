package com.bojue.bsapp.myself

import android.app.Application
import android.arch.lifecycle.LiveData
import com.bojue.bsapp.community.CommunityRepository
import com.sendi.base.data.BaseResponse
import com.bojue.bsapp.model.CommunityModel
import com.bojue.core.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/10.
 * description:
 */
class MyselfViewModel @Inject constructor(application: Application,val repository:CommunityRepository)
    : BaseViewModel(application){

    val myselfCommunityLiveData = repository.selfCommunityListLiveData

    fun getCommunityList(username : String) : LiveData<BaseResponse<List<CommunityModel>>> {
        return repository.getSelfCommunityList(username)
    }

}