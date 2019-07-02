package com.sendi.order.inject

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * create data : 2019/7/2
 * author : sendi
 * description :
 */
@Module
class OrderServiceModule {

    @Provides
    @Singleton
    fun provideOrderService(retrofit: Retrofit): com.sendi.order.repository.OrderService {
        return retrofit.create( com.sendi.order.repository.OrderService::class.java)
    }

}