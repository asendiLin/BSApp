package com.bojue.bsapp.inject

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.bojue.bsapp.app.BSApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * author: asendi.
 * data: 2019/5/12.
 * description:
 */
@Singleton
@Component(modules = [ViewModelModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build():AppComponent
    }

    fun inject(application: BSApplication)


    fun provideViewModelFactory() : ViewModelProvider.Factory
}