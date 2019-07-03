package com.sendi.myself.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import com.sendi.base.data.BaseResponse
import com.bojue.core.viewmodel.BaseViewModel
import com.sendi.myself.repository.SettingRepository
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class SettingViewModel @Inject constructor(application: Application,val repository: SettingRepository)
    :BaseViewModel(application) {

    fun identify(username: String, number: String, password: String): LiveData<BaseResponse<String>> {
       return repository.identify(username, number, password)
    }

}