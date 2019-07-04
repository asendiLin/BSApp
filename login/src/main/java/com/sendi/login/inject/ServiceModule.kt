package com.sendi.login.inject

import com.sendi.login.api.LoginAndRegisterService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * create data : 2019/7/4
 * author : sendi
 * description :
 */
@Module
class ServiceModule {


    @Provides
    @Singleton
    fun provideLoginAndRegisterService(retrofit: Retrofit): LoginAndRegisterService {
        return retrofit.create(LoginAndRegisterService::class.java)
    }

}