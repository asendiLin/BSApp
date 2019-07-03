package com.bojue.bsapp.inject

import android.app.Application
import com.sendi.base.constance.BASE_URL
import com.bojue.bsapp.http.api.*
import com.sendi.base.api.CommonService
import com.sendi.base.util.SPUtils
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(25, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient,application : Application): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(SPUtils.getString(application,"base_url", BASE_URL))
                .build()
    }

    @Provides
    @Singleton
    fun provideLoginAndRegisterService(retrofit: Retrofit): LoginAndRegisterService {
        return retrofit.create(LoginAndRegisterService::class.java)
    }


    @Provides
    @Singleton
    fun provideCommonService(retrofit: Retrofit): CommonService {
        return retrofit.create(CommonService::class.java)
    }

    @Provides
    @Singleton
    fun provideCourseService(retrofit: Retrofit): CourseService {
        return retrofit.create(CourseService::class.java)
    }
}