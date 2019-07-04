package com.sendi.login.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import com.sendi.base.data.BaseResponse
import com.sendi.login.model.RegisterResponse
import com.bojue.core.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class RegisterViewModel @Inject constructor(application: Application, val repository: com.sendi.login.repository.RegisterRepository)
    : BaseViewModel(application) {

    fun register(username : String, password : String): LiveData<BaseResponse<com.sendi.login.model.RegisterResponse>> {
       return repository.register(username, password)
    }

}