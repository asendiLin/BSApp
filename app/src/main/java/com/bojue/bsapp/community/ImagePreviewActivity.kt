package com.bojue.bsapp.community

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.bojue.bsapp.R
import com.bojue.bsapp.filter.FilterNameAdapter
import com.bojue.bsapp.util.GPUImageUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newFixedThreadPoolContext

class ImagePreviewActivity : AppCompatActivity() {

    private val myTag = "ImagePreviewActivity"
    private lateinit var mIvPreview: ImageView
    private lateinit var mRvFilters: RecyclerView
    private lateinit var mOriginBitmap: Bitmap
    private lateinit var mBtnSaveImage: Button
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_preview)
        mIvPreview = findViewById(R.id.iv_preview)
        mRvFilters = findViewById(R.id.rv_filter_name)
        mBtnSaveImage = findViewById(R.id.btn_save_image)
        mBtnSaveImage.setOnClickListener {
            finish()
        }
        initPreviewImage()
        initFilterNameList()
    }

    private fun initFilterNameList() {
        mRvFilters.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val nameList = resources.getStringArray(R.array.filter_name_arr).asList()
        val filtersAdapter = FilterNameAdapter(nameList)
        filtersAdapter.setOnClickItemListener(object : FilterNameAdapter.OnClickFilterItemListener {
            override fun onItemClick(position: Int) {
                val bitmapWithFilter = GPUImageUtil.getBitmapWithFilter(this@ImagePreviewActivity, mOriginBitmap, filterType[position]!!)
                mIvPreview.setImageBitmap(bitmapWithFilter)
            }
        })
        mRvFilters.adapter = filtersAdapter
    }

    private fun initPreviewImage() {
        intent.getStringExtra(PATH).let { path ->

            launch(newFixedThreadPoolContext(3, "asBitmap")) {
                val asBitmapJob = async {
                    mOriginBitmap = Glide.with(this@ImagePreviewActivity)
                            .load(path)
                            .asBitmap()
                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get()
                }
                asBitmapJob.await()
                launch(UI) {
                    mIvPreview.setImageBitmap(mOriginBitmap)
                }
            }

            Log.i(myTag, "onCreateView path = $path")

        }
    }
}
