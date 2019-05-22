package com.bojue.bsapp.inject

import com.bojue.bsapp.constance.BASE_URL
import com.bojue.bsapp.http.api.CommunityService
import com.bojue.bsapp.http.api.LoginAndRegisterService
import com.bojue.bsapp.http.api.MyselfService
import com.bojue.bsapp.http.api.OrderService
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
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                .build()
    }

    @Provides
    @Singleton
    fun provideLoginAndRegisterService(retrofit: Retrofit): LoginAndRegisterService {
        return retrofit.create(LoginAndRegisterService::class.java)
    }

    @Provides
    @Singleton
    fun provideOrderService(retrofit: Retrofit): OrderService {
        return retrofit.create( OrderService::class.java)
    }

    @Provides
    @Singleton
    fun provideCommunityService(retrofit: Retrofit): CommunityService {
        return retrofit.create(CommunityService::class.java)
    }

    @Provides
    @Singleton
    fun provideMyselfService(retrofit: Retrofit): MyselfService {
        return retrofit.create(MyselfService::class.java)
    }
}