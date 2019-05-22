package com.bojue.bsapp.community

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.bojue.bsapp.R
import com.bojue.bsapp.filter.FilterNameAdapter
import com.bojue.bsapp.util.GPUImageUtil
import com.bojue.core.common.BaseFragment
import com.bumptech.glide.Glide
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

/**
 * author: asendi.
 * data: 2019/5/15.
 * description:
 */
class ImagePreviewFragment : BaseFragment() {

    private val myTag = "ImagePreviewFragment"
    private lateinit var mRootView: View
    private lateinit var mIvPreview: ImageView
    private lateinit var mRvFilters: RecyclerView
    private lateinit var mOriginBitmap: Bitmap
    private lateinit var mBtnSaveImage : Button
    private val filterType = mapOf(0 to 0,
            1 to GPUImageUtil.SEPIA_FILTER,
            2 to GPUImageUtil.GRAYSCALE_FILTER,
            3 to GPUImageUtil.GAUSSIAN_BLUR_FILTER,
            4 to GPUImageUtil.TOON_FILTER,
            5 to GPUImageUtil.DISSOLVE_BLEN_FILTER,
            6 to GPUImageUtil.HAZE_FILTER,
            7 to GPUImageUtil.SHARPEN_FILTER,
            8 to GPUImageUtil.GAMMA_FILTER,
            9 to GPUImageUtil.SKETCH_FILTER)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mRootView = LayoutInflater.from(context).inflate(R.layout.fragment_image_preview, null, false)
        mIvPreview = mRootView.findViewById(R.id.iv_preview)
        mRvFilters = mRootView.findViewById(R.id.rv_filter_name)
        mBtnSaveImage = mRootView.findViewById(R.id.btn_save_image)
        mBtnSaveImage.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.remove(this@ImagePreviewFragment)
            transaction.commit()
        }
        initPreviewImage()
        initFilterNameList()
        return mRootView
    }

    private fun initFilterNameList() {
        mRvFilters.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val nameList = requireActivity().resources.getStringArray(R.array.filter_name_arr).asList()
        val filtersAdapter = FilterNameAdapter(nameList)
        filtersAdapter.setOnClickItemListener(object : FilterNameAdapter.OnClickFilterItemListener {
            override fun onItemClick(position: Int) {
                val bitmapWithFilter = GPUImageUtil.getBitmapWithFilter(requireContext(), mOriginBitmap, filterType[position]!!)
                mIvPreview.setImageBitmap(bitmapWithFilter)
            }
        })
        mRvFilters.adapter = filtersAdapter
    }

    private fun initPreviewImage() {
        arguments?.let {
            val path = it[PATH]

            launch(newFixedThreadPoolContext(3,"asBitmap")) {
                val asBitmapJob = async {
                    mOriginBitmap =  Glide.with(requireActivity())
                            .load(path)
                            .asBitmap()
                            .into(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL, com.bumptech.glide.request.target.Target.SIZE_ORIGINAL)
                            .get()
                }
                asBitmapJob.await()
                launch(UI){
                    mIvPreview.setImageBitmap(mOriginBitmap)
                }
            }

            Log.i(myTag, "onCreateView path = $path")

        }
    }

}