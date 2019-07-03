package com.sendi.community.manager

import android.app.Application
import android.content.Context
import com.sendi.community.inject.CommunityInjector
import com.sendi.community.util.MediaLoader
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumConfig

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
object CommunityManager : ICommunityMananger {
    override fun init(application: Application) {
        initInject(application)
        initAlbum(application)
    }

    private fun initInject(application: Application) {
        CommunityInjector.inject(application)
    }

    private fun initAlbum(context: Context) {
        Album.initialize(AlbumConfig.newBuilder(context)
                .setAlbumLoader(MediaLoader())
                .build())
    }
}