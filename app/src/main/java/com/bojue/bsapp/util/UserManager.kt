package com.bojue.bsapp.util

import android.content.Context
import android.content.SharedPreferences
import com.bojue.bsapp.model.UserModel

/**
 * author: asendi.
 * data: 2019/5/24.
 * description:
 */
object UserManager {

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

    private lateinit var mUserSP: SharedPreferences

    fun init(context: Context){
        mUserSP = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    fun saveUser(userModel: UserModel) {
        val userEdit = mUserSP.edit()
        userEdit.putInt(ID,userModel.stuId)
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

    fun getUser():UserModel {
        val id = mUserSP.getInt(ID,-1)
        val username = mUserSP.getString(USERNAME,"unknown")
        val password = mUserSP.getString(PASSWORD,"unknown")
        val number = mUserSP.getString(NUMBER,"unknown")
        val classname = mUserSP.getString(CLASS_NAME,"unknown")
        val icon = mUserSP.getString(ICON,"unknown")
        val nickname = mUserSP.getString(NICKNAME,"unknown")
        val phone = mUserSP.getString(PHONE,"unknown")
        val signature = mUserSP.getString(SIGNATURE,"unknown")

        return UserModel(id,username,password, number, classname, icon, nickname, phone, signature)
    }

}