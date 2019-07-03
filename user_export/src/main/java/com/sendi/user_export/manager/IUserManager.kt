package com.sendi.user_export.manager

import com.alibaba.android.arouter.facade.template.IProvider
import com.sendi.user_export.model.UserModel

/**
 * create data : 2019/7/2
 * author : sendi
 * description :
 */
interface IUserManager : IProvider {
    companion object {
        const val NAME = "user_info"

        const val ID = "id"
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val NUMBER = "number"
        const val CLASS_NAME = "classname"
        const val ICON = "icon"
        const val NICKNAME = "nickname"
        const val PHONE = "phone"
        const val SIGNATURE = "signature"
    }

    fun getUser(): UserModel

    fun clearUser()

    fun saveUser(user: UserModel)
}