package com.bojue.bsapp.model

/**
 * author: asendi.
 * data: 2019/5/24.
 * description:
 */
data class UserModel(val stuId: Int, val username: String,
                     val password: String, var number: String?,
                     val classname: String?, var icon: String?,
                     var nickname: String?, var phone: String?,
                     var signature: String?)