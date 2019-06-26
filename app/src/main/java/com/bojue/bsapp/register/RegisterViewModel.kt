package com.bojue.bsapp.register

import android.app.Application
import android.arch.lifecycle.LiveData
import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.RegisterResponse
import com.bojue.core.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class RegisterViewModel @Inject constructor(application: Application, val repository: RegisterRepository)
    : BaseViewModel(application) {

    fun register(username : String, password : String): LiveData<BaseResponse<RegisterResponse>> {
       return repository.register(username, password)
    }

}