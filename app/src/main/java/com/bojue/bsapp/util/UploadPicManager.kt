package com.bojue.bsapp.util

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.sendi.base.constance.BASE_URL
import com.bojue.bsapp.http.api.CommonService
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

/**
 * author: asendi.
 * data: 2019/5/23.
 * description:
 */
class UploadPicManager {

    private val myTag = "UploadPicManager"

    private val client = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

    private val mRetrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    fun uploadPic(contxet: Context, bitmap: Bitmap, listener: OnUploadPicListener) {
        val service = mRetrofit.create(CommonService::class.java)
        val cacheDir = contxet.cacheDir
        val tempFile = File(cacheDir, "${System.currentTimeMillis()}.jpg")
        launch(newFixedThreadPoolContext(3, "asFile")) {
            val asFileJob = async {
                val bos = BufferedOutputStream(FileOutputStream(tempFile))
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos)
                bos.flush()
                bos.close()
            }
            asFileJob.await()

            val uploadJob = async {
                val requestBody = RequestBody.create(MediaType.parse("image/jpeg"), tempFile)

                val picPart = MultipartBody.Part.createFormData("file", tempFile.name, requestBody)
                try {
                    val execute = service.uploadPic(picPart).execute()
                    Log.i(myTag, "data = ${execute.body()?.data}")
                    execute.body()?.data?.let {url->
                        launch(UI){
                            listener.onSuccess(url)
                        }
                    }
                } catch (e: Exception) {
                    Log.i(myTag,"uploadPic ${e.message}")
                    launch(UI){
                        listener.onFail("上传图片失败")
                    }
                }
            }
            uploadJob.await()
        }
    }

    interface OnUploadPicListener {
        fun onSuccess(path: String)
        fun onFail(message: String)
    }
}