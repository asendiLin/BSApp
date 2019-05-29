package com.bojue.bsapp.community

import android.app.Application
import android.arch.lifecycle.LiveData
import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.CommunityModel
import com.bojue.bsapp.viewmodel.BaseViewModel
import com.yanzhenjie.album.mvp.BaseView
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/28.
 * description:
 */
class MyselfCommunityViewModel @Inject constructor(application: Application,val repository: CommunityRepository)
    : BaseViewModel(application) {

    fun getCommunityList(username : String) : LiveData<BaseResponse<List<CommunityModel>>>{
        return repository.getSelfCommunityList(username)
    }

}