package com.sendi.user_export.manager

import com.alibaba.android.arouter.facade.template.IProvider
import com.sendi.user_export.model.UserModel

/**
 * create data : 2019/7/2
 * author : sendi
 * description :
 */
interface IUserManager :IProvider {

    fun getUser(): UserModel

    fun clearUser()

    fun saveUser(user : UserModel)
}