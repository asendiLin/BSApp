package com.bojue.bsapp.util

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.bojue.bsapp.R
import com.sendi.base.constance.BASE_URL
import com.bumptech.glide.Glide

/**
 * author: asendi.
 * data: 2019/5/28.
 * description:
 */
object ShowImageUtil {

    fun showImage(context: Context,imageView: ImageView,url:String?){
        Glide.with(context)
                .load("$BASE_URL$url")
                .error(R.mipmap.ic_app_logo)
                .into(imageView)
    }

    fun showImage(activity: Activity,imageView: ImageView,url:String?){
        Glide.with(activity).load("$BASE_URL$url").error(R.mipmap.ic_app_logo).into(imageView)
    }

    fun showImage(fragment: Fragment,imageView: ImageView,url:String?){
        Glide.with(fragment).load("$BASE_URL$url").error(R.mipmap.ic_app_logo).into(imageView)
    }

}