package com.bojue.bsapp.util

import android.content.Context
import android.content.SharedPreferences

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
object SPUtils {

    private var mSharedPreference: SharedPreferences? = null
    private const val NAME = "common_info"
    fun saveString(context: Context, key: String, value: String) {
        if (mSharedPreference == null) {
            mSharedPreference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        }
        mSharedPreference?.edit()?.putString(key, value)?.apply()
    }

    fun saveInt(context: Context, key: String, value: Int) {
        if (mSharedPreference == null) {
            mSharedPreference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        }
        mSharedPreference?.edit()?.putInt(key, value)?.apply()
    }

    fun getInt(context: Context, key: String, defaultVal: Int): Int? {
        if (mSharedPreference == null) {
            mSharedPreference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        }
        return mSharedPreference?.getInt(key, defaultVal)
    }

    fun getString(context: Context, key: String, defaultVal: String): String? {
        if (mSharedPreference == null) {
            mSharedPreference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        }
        return mSharedPreference?.getString(key, defaultVal)
    }

}