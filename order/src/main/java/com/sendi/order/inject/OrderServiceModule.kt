package com.sendi.order.inject

import com.sendi.order.repository.OrderService
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
    fun provideOrderService(retrofit: Retrofit): OrderService {
        return retrofit.create( OrderService::class.java)
    }

}