package com.bojue.bsapp.community

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.bojue.bsapp.R
import com.bojue.bsapp.constance.SUCCESS_STATU
import com.bojue.core.ext.getViewModel
import com.bojue.bsapp.util.*
import com.bojue.bsapp.widget.LoadingDialog
import com.bojue.core.common.BaseActivity
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.api.widget.Widget

class PublishCommunityActivity : BaseActivity() {

    private val myTag = "PublishCommunityFra"

    private lateinit var mBtnPhotoSelect: ImageButton
    private lateinit var mTvBack: TextView
    private lateinit var mIbSelectImage: ImageButton
    private lateinit var mIvImage: ImageView
    private lateinit var mBtnCommunityPublish: Button
    private lateinit var mEtContent : EditText
    private var mBitmap:Bitmap? = null

    val mUploadPicManager = UploadPicManager()

    private val mCommunityViewModel by lazy {
        getViewModel(CommunityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish_community)
        mBtnPhotoSelect = findViewById(R.id.btn_photo_select)
        mTvBack = findViewById(R.id.tv_nav_title)
        mBtnCommunityPublish = findViewById(R.id.btn_community_publish)
        mEtContent = findViewById(R.id.et_community_content)
        mIvImage = findViewById(R.id.iv_publish_image)
        mBtnCommunityPublish.setOnClickListener {

            if (mEtContent.text.isNullOrEmpty() || mBitmap ==null){
                ToastUtil.showShort(this,"请完善动态信息")
                return@setOnClickListener
            }

            val loadingDialog= LoadingDialog(this)
            loadingDialog.show()

            val content = mEtContent.text.toString()
            val stuId = if(UserManager.getUser().id == 0) 4 else UserManager.getUser().id
            var pic = ""

            mBitmap?.let {
                mUploadPicManager.uploadPic(this,it,object : UploadPicManager.OnUploadPicListener{
                    override fun onSuccess(path: String) {
                        pic = path
                        mCommunityViewModel.publish(content, stuId, pic, DateUtils.getDate())
                                .observe(this@PublishCommunityActivity, Observer { result ->
                                    loadingDialog.dismiss()
                                    result?.let {
                                        if (result.status == SUCCESS_STATU) {
                                            finish()
                                        }else{
                                            val alertDialog = AlertDialog.Builder(this@PublishCommunityActivity).setMessage(result.message).create()
                                            alertDialog.show()
                                        }
                                    }
                                })
                    } 

                    override fun onFail(message: String) {
                        loadingDialog.dismiss()
                        ToastUtil.showShort(this@PublishCommunityActivity,message)
                    }
                })
            }



        }
        mBtnPhotoSelect.setOnClickListener {
            showBottomDialog()
        }
        mTvBack.setOnClickListener { finish() }

        mCommunityViewModel.bitmapLiveData.observe(this, Observer {
            it?.let {
                mBtnPhotoSelect.visibility = View.GONE
                mIvImage.visibility = View.VISIBLE
                mIvImage.setImageBitmap(it.bitmap)
                mBitmap = it.bitmap
            }
        })
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
        val intent = Intent(this, ImagePreviewActivity::class.java)
        intent.putExtra(PATH, path)
        startActivity(intent)
    }
}
