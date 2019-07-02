package com.sendi.user.manager

import android.content.Context
import android.content.SharedPreferences
import com.alibaba.android.arouter.facade.annotation.Route
import com.sendi.base.constance.UNKNOW_USER
import com.sendi.user_export.constance.USER_MANAGER
import com.sendi.user_export.manager.IUserManager
import com.sendi.user_export.model.UserModel

/**
 * create data : 2019/7/2
 * author : sendi
 * description :
 */
@Route(path = USER_MANAGER)
class UserManager : IUserManager {

    companion object{
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

    private lateinit var mUserSP: SharedPreferences

    override fun init(context: Context?) {
        context?.let {
            mUserSP = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        }

    }

    override fun getUser(): UserModel {
        val id = mUserSP.getInt(ID, UNKNOW_USER)
        val username = mUserSP.getString(USERNAME,"")
        val password = mUserSP.getString(PASSWORD,"")
        val number = mUserSP.getString(NUMBER,null)
        val classname = mUserSP.getString(CLASS_NAME,null)
        val icon = mUserSP.getString(ICON,null)
        val nickname = mUserSP.getString(NICKNAME,null)
        val phone = mUserSP.getString(PHONE,null)
        val signature = mUserSP.getString(SIGNATURE,null)

        return UserModel(id, username, password, number, classname, icon, nickname, phone, signature)
    }

    override fun clearUser() {
        val userEdit = mUserSP.edit()
        userEdit.putInt(ID, UNKNOW_USER)
        userEdit.apply()
    }

    override fun saveUser(userModel: UserModel) {
        val userEdit = mUserSP.edit()
        userEdit.putInt(ID,userModel.id)
        userEdit.putString(USERNAME,userModel.username)
        userEdit.putString(PASSWORD,userModel.password)
        userEdit.putString(NUMBER,userModel.number)
        userEdit.putString(CLASS_NAME,userModel.classname)
        userEdit.putString(ICON,userModel.icon)
        userEdit.putString(NICKNAME,userModel.nickname)
        userEdit.putString(PHONE,userModel.phone)
        userEdit.putString(SIGNATURE,userModel.signature)
        userEdit.apply()
    }
}