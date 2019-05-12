package com.bojue.bsapp.community

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.bojue.bsapp.model.CommunityModel
import com.bojue.bsapp.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/10.
 * description:
 */
class CommunityViewModel @Inject constructor(application: Application,val repository: CommunityRepository)
    :BaseViewModel(application) {

    fun getCommunityList(/*待传参数*/): LiveData<List<CommunityModel>> {
        return repository.getCommunityList()
    }
}