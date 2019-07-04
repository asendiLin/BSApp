package com.sendi.login.inject

import android.app.Application
import com.bojue.core.component.BaseComponent
import com.bojue.core.module.CommonModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * create data : 2019/7/4
 * author : sendi
 * description :
 */
@Singleton
@Component(modules = [LoginViewModelModule::class,ServiceModule::class,CommonModule::class])
interface LoginComponent : BaseComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build():LoginComponent
    }

}