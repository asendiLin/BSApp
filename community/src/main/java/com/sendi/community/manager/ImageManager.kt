package com.sendi.community.manager

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.sendi.community.activity.ImagePreviewActivity
import com.sendi.community.fragment.PATH
import com.sendi.community.util.GPUImageUtil
import com.sendi.community_export.constance.IMAGE_MANAGER
import com.sendi.community_export.manager.IImageManager
import com.sendi.user_export.constance.USER_MANAGER

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
@Route(path = IMAGE_MANAGER)
class ImageManager : IImageManager {
    private val myTag = "ImageManager"
    override fun init(context: Context?) {
        Log.i(myTag,"init")
    }

    override fun getBitmapWithFilter(context: Context, bitmap: Bitmap, filterType: Int): Bitmap {
       return GPUImageUtil.getBitmapWithFilter(context, bitmap, filterType)
    }

    override fun openImagePreview(context: Context,path :String) {
        val intent = Intent(context, ImagePreviewActivity::class.java)
        intent.putExtra(PATH, path)
        context.startActivity(intent)
    }
}