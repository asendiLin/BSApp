package com.bojue.bsapp.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
object RetrofitFactory {

    private lateinit var mRetrofit: Retrofit

    fun <T> getService(clazz : Class<T>): T{

        if (mRetrofit == null){
            val client = OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5,TimeUnit.SECONDS)
                    .writeTimeout(5,TimeUnit.SECONDS)
                    .build()
            mRetrofit = Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
//                    .baseUrl(GlobalContent.BASEURL)
                    .build()
        }

        return mRetrofit.create(clazz)
    }

}