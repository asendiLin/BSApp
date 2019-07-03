package com.sendi.myself.inject

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
@Module
class ServiceModule {
    @Provides
    @Singleton
    fun provideMyselfService(retrofit: Retrofit): com.sendi.myself.api.MyselfService {
        return retrofit.create(com.sendi.myself.api.MyselfService::class.java)
    }

    @Provides
    @Singleton
    fun provideSettingService(retrofit: Retrofit): com.sendi.myself.api.SettingService {
        return retrofit.create(com.sendi.myself.api.SettingService::class.java)
    }
}