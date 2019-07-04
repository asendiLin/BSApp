package com.sendi.login.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import com.sendi.base.data.BaseResponse
import com.sendi.login.model.LoginResponse
import com.bojue.core.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class LoginViewModel @Inject constructor(application: Application, val repository: com.sendi.login.repository.LoginRepository)
    : BaseViewModel(application) {

    fun login(username: String, password: String): LiveData<BaseResponse<com.sendi.login.model.LoginResponse>> {
       return repository.login(username, password)
    }

}