package com.sendi.community.fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.bojue.core.common.BaseFragment
import com.sendi.community.R
import com.sendi.community.activity.ImagePreviewActivity
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.api.widget.Widget

/**
 * author: asendi.
 * data: 2019/5/12.
 * description:
 */
class PublishCommunityFragment : BaseFragment() {

    private val myTag = "PublishCommunityFra"
    private lateinit var mRootView: View
    private lateinit var mBtnPhotoSelect: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mRootView = LayoutInflater.from(context).inflate(R.layout.fragment_publish_community, null, false)
        mBtnPhotoSelect = mRootView.findViewById<Button>(R.id.btn_photo_select)
        mBtnPhotoSelect.setOnClickListener {
            showBottomDialog()
        }
        return mRootView
    }

    private fun showBottomDialog() {
        val bottomDialog = Dialog(context, R.style.BottomDialog)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_select_bottom, null, false)
        val tvAlbum = view.findViewById<TextView>(R.id.tv_album)
        val tvCamera = view.findViewById<TextView>(R.id.tv_camera)
        val tvCancel = view.findViewById<TextView>(R.id.tv_cancel)
        val titleWidget = Widget.newDarkBuilder(context)
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
//        val previewFragment = ImagePreviewFragment()
//        val args = Bundle()
//        args.putString(PATH, path)
//        previewFragment.arguments = args
//        val transaction = requireActivity().supportFragmentManager.beginTransaction()
//        transaction.add(R.id.fl_edit_content,previewFragment)
//        transaction.hide(this)
//        transaction.addToBackStack(null)
//        transaction.commitAllowingStateLoss()
        val intent = Intent(requireActivity(), ImagePreviewActivity::class.java)
        intent.putExtra(PATH, path)
        startActivity(intent)
    }

}

const val PATH = "path"