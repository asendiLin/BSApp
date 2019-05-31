package com.bojue.bsapp.util

import android.content.Context
import android.widget.Toast

/**
 * author: asendi.
 * data: 2019/5/29.
 * description:
 */
object ToastUtil {

    fun showShort(context: Context,message:String?){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }
    fun showLong(context: Context,message:String?){
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }
}