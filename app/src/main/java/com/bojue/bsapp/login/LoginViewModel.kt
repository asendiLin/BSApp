package com.bojue.bsapp.login

import android.app.Application
import com.bojue.bsapp.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class LoginViewModel @Inject constructor(application: Application, val repository: LoginRepository)
    : BaseViewModel(application) {

    fun login(username: String, password: String) {
        repository.register(username, password)
    }

}