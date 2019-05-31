package com.bojue.bsapp.event

/**
 * author: asendi.
 * data: 2019/5/31.
 * description:
 */
data class SettingEvent(val type : Int){
    companion object {
        const val COURSE = 0x1
    }
}