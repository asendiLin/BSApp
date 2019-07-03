package com.sendi.myself.inject

import android.app.Application
import com.bojue.core.component.BaseComponent
import com.bojue.core.module.CommonModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
@Singleton
@Component(modules = [ServiceModule::class,CommonModule::class,MyselfViewModelModule::class])
interface MyselfComponent : BaseComponent{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build():MyselfComponent
    }

}