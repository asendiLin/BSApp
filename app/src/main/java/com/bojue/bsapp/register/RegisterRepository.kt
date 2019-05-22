package com.bojue.bsapp.register

import com.bojue.bsapp.http.api.LoginAndRegisterService
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class RegisterRepository @Inject constructor(service : LoginAndRegisterService){

    fun register(username : String, password : String){}

}