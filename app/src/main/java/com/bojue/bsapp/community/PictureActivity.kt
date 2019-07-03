package com.bojue.bsapp.community

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bojue.bsapp.R
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumConfig
import android.util.Log
import com.yanzhenjie.album.AlbumFile


class PictureActivity : AppCompatActivity() {

    private val myTag = "PictureActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        Album.initialize(AlbumConfig.newBuilder(this)
                .setAlbumLoader(MediaLoader())
                .build())
    }

    fun onSelect(view :View){
        Album.image(this) // Image selection.
                .singleChoice()
                .camera(true)
                .columnCount(3)
//                .filterSize() // Filter the file size.
//                .filterMimeType() // Filter file format.
//                .afterFilterVisibility() // Show the filtered files, but they are not available.
                .onResult { albumFileList ->
                    albumFileList.listIterator().forEach {
                        Log.i(myTag,"path : ${it.path}")
                    }
                }
                .onCancel{ cancel ->
                }
                .start()
    }
}
