package com.sendi.user.util

import android.content.Context
import android.content.SharedPreferences
import com.sendi.base.constance.UNKNOW_USER
import com.sendi.user_export.manager.IUserManager
import com.sendi.user_export.model.UserModel

/**
 * create data : 2019/7/4
 * author : sendi
 * description :
 */
object UserUtil {

    private lateinit var mUserSP: SharedPreferences

    fun init(context: Context) {
            mUserSP = context.getSharedPreferences(IUserManager.NAME, Context.MODE_PRIVATE)
    }

    fun getUser(): UserModel {
        val id = mUserSP.getInt(IUserManager.ID, UNKNOW_USER)
        val username = mUserSP.getString(IUserManager.USERNAME,"")
        val password = mUserSP.getString(IUserManager.PASSWORD,"")
        val number = mUserSP.getString(IUserManager.NUMBER,null)
        val classname = mUserSP.getString(IUserManager.CLASS_NAME,null)
        val icon = mUserSP.getString(IUserManager.ICON,null)
        val nickname = mUserSP.getString(IUserManager.NICKNAME,null)
        val phone = mUserSP.getString(IUserManager.PHONE,null)
        val signature = mUserSP.getString(IUserManager.SIGNATURE,null)

        return UserModel(id, username, password, number, classname, icon, nickname, phone, signature)
    }

    fun clearUser() {
        val userEdit = mUserSP.edit()
        userEdit.putInt(IUserManager.ID, UNKNOW_USER)
        userEdit.apply()
    }

    fun saveUser(user: UserModel) {
        val userEdit = mUserSP.edit()
        userEdit.putInt(IUserManager.ID,user.id)
        userEdit.putString(IUserManager.USERNAME,user.username)
        userEdit.putString(IUserManager.PASSWORD,user.password)
        userEdit.putString(IUserManager.NUMBER,user.number)
        userEdit.putString(IUserManager.CLASS_NAME,user.classname)
        userEdit.putString(IUserManager.ICON,user.icon)
        userEdit.putString(IUserManager.NICKNAME,user.nickname)
        userEdit.putString(IUserManager.PHONE,user.phone)
        userEdit.putString(IUserManager.SIGNATURE,user.signature)
        userEdit.apply()
    }


}