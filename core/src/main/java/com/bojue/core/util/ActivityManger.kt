package com.bojue.core.util

import com.bojue.core.common.BaseActivity

/**
 * author: asendi.
 * data: 2019/5/29.
 * description:
 */
object ActivityManger {

    private val mActivityList = ArrayList<BaseActivity>()

    fun addActivity(activity: BaseActivity){
        mActivityList.add(activity)
    }

    fun removeActivity(activity: BaseActivity){
        mActivityList.remove(activity)
    }

    fun logout(){
        mActivityList.forEach { activity ->
            activity.finish()
        }
        mActivityList.clear()
    }
}