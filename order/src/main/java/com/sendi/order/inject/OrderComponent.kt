package com.sendi.order.inject

import android.app.Application
import com.bojue.core.component.BaseComponent
import com.bojue.core.module.CommonModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * create data : 2019/7/2
 * author : sendi
 * description :订单组件注入的Component
 */

@Singleton
@Component(modules = [OrderViewModelModule::class , OrderServiceModule::class,CommonModule::class])
interface OrderComponent : BaseComponent{
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build():OrderComponent
    }

    fun inject(application: Application)//此方法暂时没什么作用
}