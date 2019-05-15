package com.bojue.bsapp.util

import android.widget.ImageView
import com.bojue.bsapp.R
import com.bumptech.glide.Glide
import com.yanzhenjie.album.AlbumFile
import com.yanzhenjie.album.AlbumLoader

/**
 * author: asendi.
 * data: 2019/5/14.
 * description:
 */
class MediaLoader : AlbumLoader{
    override fun load(imageView: ImageView, albumFile: AlbumFile) {
        load(imageView, albumFile.path)
    }

    override fun load(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
                .load(url)
                .error(R.mipmap.jaygee)
                .placeholder(R.mipmap.jaygee)
                .crossFade()
                .into(imageView)
    }
}