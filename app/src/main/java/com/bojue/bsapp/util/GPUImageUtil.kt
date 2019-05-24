package com.bojue.bsapp.util

import android.content.Context
import android.graphics.Bitmap
import jp.co.cyberagent.android.gpuimage.*

/**
 * author: asendi.
 * data: 2019/5/15.
 * description:
 */
object GPUImageUtil {
    const val ORIGIN_FILTER =0
    const val SEPIA_FILTER = 1//怀旧
    const val GRAYSCALE_FILTER = 2//灰度
    const val GAUSSIAN_BLUR_FILTER = 3//高斯模糊
    const val TOON_FILTER = 4//卡通
    const val DISSOLVE_BLEN_FILTER = 5//溶解
    const val HAZE_FILTER = 6//朦胧
    const val SHARPEN_FILTER = 7//锐化
    const val GAMMA_FILTER = 8//伽马线
    const val SKETCH_FILTER = 9//素描

    fun getBitmapWithFilter(context: Context, bitmap: Bitmap, filterType: Int): Bitmap {
        val gpuImage = GPUImage(context)
        gpuImage.setImage(bitmap)
        gpuImage.setFilter(getFilter(filterType))
        val bitmapWithFilter = gpuImage.bitmapWithFilterApplied
        return bitmapWithFilter
    }

    fun getFilter(type: Int): GPUImageFilter {
        return when (type) {
            SEPIA_FILTER -> GPUImageSepiaFilter()
            GRAYSCALE_FILTER -> GPUImageGrayscaleFilter()
            GAUSSIAN_BLUR_FILTER -> GPUImageGaussianBlurFilter()
            TOON_FILTER -> GPUImageToonFilter()
            DISSOLVE_BLEN_FILTER -> GPUImageDissolveBlendFilter()
            HAZE_FILTER -> GPUImageHazeFilter()
            SHARPEN_FILTER -> GPUImageSharpenFilter()
            GAMMA_FILTER -> GPUImageGammaFilter()
            SKETCH_FILTER -> GPUImageSketchFilter()
            else -> GPUImageNormalBlendFilter()
        }
    }

    fun getCustomFilter(vertexShader: String, fragmentShader: String): GPUImageFilter {
        return GPUImageFilter(vertexShader, fragmentShader)
    }

}