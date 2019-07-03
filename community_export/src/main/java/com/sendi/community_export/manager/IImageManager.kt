package com.sendi.community_export.manager

import android.content.Context
import android.graphics.Bitmap
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
interface IImageManager : IProvider{


    fun getBitmapWithFilter(context: Context, bitmap: Bitmap, filterType: Int): Bitmap

    fun openImagePreview(context: Context,path : String)
}