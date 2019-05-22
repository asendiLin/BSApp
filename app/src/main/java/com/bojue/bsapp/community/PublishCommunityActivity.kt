package com.bojue.bsapp.community

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bojue.bsapp.R
import com.bojue.bsapp.ext.getViewModel
import com.bojue.bsapp.util.MediaLoader
import com.bojue.core.common.BaseActivity
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumConfig
import com.yanzhenjie.album.api.widget.Widget

class PublishCommunityActivity : BaseActivity() {

    private val myTag = "PublishCommunityFra"

    private lateinit var mBtnPhotoSelect: ImageButton
    private lateinit var mTvBack : TextView
    private lateinit var mIbSelectImage : ImageButton
    private lateinit var mIvImage : ImageView
    private lateinit var mBtnCommunityPublish : Button

    private val mCommunityViewModel by lazy {
        getViewModel(CommunityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish_community)
        mBtnPhotoSelect = findViewById(R.id.btn_photo_select)
        mTvBack = findViewById(R.id.tv_nav_title)
        mBtnCommunityPublish = findViewById(R.id.btn_community_publish)
        mBtnCommunityPublish.setOnClickListener {
            mCommunityViewModel.publish("have a nice day",4,"https://ss0.baidu.com/73x1bjeh1BF3odCf/it/u=1221417934,4154057143%26fm=85%26s=B5D34A32594366D6061B91FB0300B02A","1月1日")
                    .observe(this,Observer{

                    })
        }
        mBtnPhotoSelect.setOnClickListener {
            showBottomDialog()
        }
        mTvBack.setOnClickListener { finish() }

        mCommunityViewModel.bitmapLiveData.observe(this, Observer {
            it?.let {
                mIbSelectImage.visibility=View.GONE
                mIvImage.visibility=View.VISIBLE
                mIvImage.setImageBitmap(it.bitmap)
            }
        })

        initAlbum()
    }

    private fun initAlbum() {
        Album.initialize(AlbumConfig.newBuilder(this)
                .setAlbumLoader(MediaLoader())
                .build())
    }

    private fun showBottomDialog() {
        val bottomDialog = Dialog(this, R.style.BottomDialog)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_select_bottom, null, false)
        val tvAlbum = view.findViewById<TextView>(R.id.tv_album)
        val tvCamera = view.findViewById<TextView>(R.id.tv_camera)
        val tvCancel = view.findViewById<TextView>(R.id.tv_cancel)
        val titleWidget = Widget.newDarkBuilder(this)
                .title("相册")
                .build()
        tvAlbum.setOnClickListener {
            Album.image(this) // Image selection.
                    .singleChoice()
                    .widget(titleWidget)
                    .camera(true)
                    .columnCount(4)
                    .onResult { albumFileList ->
                        albumFileList.listIterator().forEach {
                            Log.i(myTag, "path : ${it.path}")
                            startImagePreview(it.path)
                        }
                    }
                    .onCancel { cancel ->
                    }
                    .start()
            if (bottomDialog.isShowing) {
                bottomDialog.dismiss()
            }
        }

        tvCamera.setOnClickListener {
            Album.camera(this)
                    .image()
                    .onResult { path ->
                        Log.i(myTag, "path : $path")
                        startImagePreview(path)
                    }
                    .start()
            if (bottomDialog.isShowing) {
                bottomDialog.dismiss()
            }
        }

        tvCancel.setOnClickListener {
            if (bottomDialog.isShowing) {
                bottomDialog.dismiss()
            }
        }

        bottomDialog.setContentView(view)
        val window = bottomDialog.window
        window.setGravity(Gravity.BOTTOM)
        val params = window.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = params
        bottomDialog.show()
    }

    private fun startImagePreview(path: String) {
        val intent = Intent(this,ImagePreviewActivity::class.java)
        intent.putExtra(PATH, path)
        startActivity(intent)
    }
}
