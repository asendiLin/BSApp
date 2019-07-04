package com.sendi.user.manager

import android.content.Context
import android.content.SharedPreferences
import com.alibaba.android.arouter.facade.annotation.Route
import com.sendi.base.constance.UNKNOW_USER
import com.sendi.user.util.UserUtil
import com.sendi.user_export.constance.USER_MANAGER
import com.sendi.user_export.manager.IUserManager
import com.sendi.user_export.manager.IUserManager.Companion.CLASS_NAME
import com.sendi.user_export.manager.IUserManager.Companion.ICON
import com.sendi.user_export.manager.IUserManager.Companion.ID
import com.sendi.user_export.manager.IUserManager.Companion.NAME
import com.sendi.user_export.manager.IUserManager.Companion.NICKNAME
import com.sendi.user_export.manager.IUserManager.Companion.NUMBER
import com.sendi.user_export.manager.IUserManager.Companion.PASSWORD
import com.sendi.user_export.manager.IUserManager.Companion.PHONE
import com.sendi.user_export.manager.IUserManager.Companion.SIGNATURE
import com.sendi.user_export.manager.IUserManager.Companion.USERNAME
import com.sendi.user_export.model.UserModel
import java.lang.IllegalArgumentException

/**
 * create data : 2019/7/2
 * author : sendi
 * description :
 */
@Route(path = USER_MANAGER)
class UserManager : IUserManager {

    override fun init(context: Context?) {

        if (context == null){
            throw IllegalArgumentException("the context must be not null.")
        }
        UserUtil.init(context)
    }

    override fun getUser(): UserModel {
        return UserUtil.getUser()
    }

    override fun clearUser() {
        UserUtil.clearUser()
    }

    override fun saveUser(user: UserModel) {
       UserUtil.saveUser(user)
    }

}