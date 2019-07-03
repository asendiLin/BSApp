package com.sendi.course.inject

import android.arch.lifecycle.ViewModelProvider
import com.bojue.core.viewmodel.AppViewModelFactory
import com.bojue.core.viewmodel.IViewModel
import com.bojue.core.viewmodel.ViewModelKey
import com.sendi.course.viewmodel.CourseViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
@Module
interface CourseViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CourseViewModel::class)
    fun courseViewModel(viewModel: CourseViewModel): IViewModel

}