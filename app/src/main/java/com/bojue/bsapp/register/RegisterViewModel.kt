package com.bojue.bsapp.register

import android.app.Application
import com.bojue.bsapp.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class RegisterViewModel @Inject constructor(application: Application, val repository: RegisterRepository)
    : BaseViewModel(application) {

    fun register(username : String, password : String){
        repository.register(username, password)
    }

}