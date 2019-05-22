package com.bojue.bsapp.model

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
data class LoginResponse(val username: String, val password: String,
                         val number: String?, val id: Int,
                         val classname: String?, val icon: String?,
                         val phone: String?, val signature: String?)