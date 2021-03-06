package com.sendi.community.activity

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.sendi.base.event.BitmapEvent
import com.bojue.core.event.EventUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.sendi.community.R
import com.sendi.community.adapter.FilterNameAdapter
import com.sendi.community.fragment.PATH
import com.sendi.community.util.GPUImageUtil
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newFixedThreadPoolContext

class ImagePreviewActivity : AppCompatActivity() {

    private val myTag = "ImagePreviewActivity"
    private lateinit var mIvPreview: ImageView
    private lateinit var mRvFilters: RecyclerView
    private lateinit var mOriginBitmap: Bitmap
    private lateinit var mBitmapWithFilter: Bitmap
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
            val bitmapEvent = BitmapEvent(mBitmapWithFilter)
            EventUtil.post(bitmapEvent)
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
                if (position == GPUImageUtil.ORIGIN_FILTER){
                    mIvPreview.setImageBitmap(mOriginBitmap)
                    mBitmapWithFilter = mOriginBitmap
                }else{
                   mBitmapWithFilter = GPUImageUtil.getBitmapWithFilter(this@ImagePreviewActivity, mOriginBitmap, filterType[position]!!)
                    mIvPreview.setImageBitmap(mBitmapWithFilter)
                }
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
                    mBitmapWithFilter = mOriginBitmap
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
