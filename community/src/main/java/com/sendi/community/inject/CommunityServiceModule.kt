package com.sendi.community.inject

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
class CommunityServiceModule {

    @Provides
    @Singleton
    fun provideCommunityService(retrofit: Retrofit): com.sendi.community.api.CommunityService {
        return retrofit.create(com.sendi.community.api.CommunityService::class.java)
    }


}