package com.bojue.bsapp.app

import android.app.Application
import com.bojue.bsapp.constance.BASE_URL
import com.bojue.bsapp.constance.BASE_URL_KEY
import com.bojue.bsapp.constance.DEFAULT_URL
import com.bojue.bsapp.inject.DaggerAppComponent
import com.bojue.bsapp.inject.Injector
import com.bojue.bsapp.util.CourseUtil
import com.bojue.bsapp.util.MediaLoader
import com.bojue.bsapp.util.SPUtils
import com.bojue.bsapp.util.UserManager
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumConfig

/**
 * author: asendi.
 * data: 2019/5/12.
 * description:
 */
class BSApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Injector.init(this)
        UserManager.init(this)
        BASE_URL = SPUtils.getString(this, BASE_URL_KEY, DEFAULT_URL) ?: DEFAULT_URL
        initAlbum()
        CourseUtil.init(this)
    }

    private fun initAlbum() {
        Album.initialize(AlbumConfig.newBuilder(this)
                .setAlbumLoader(MediaLoader())
                .build())
    }
}