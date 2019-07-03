package com.bojue.bsapp.inject

import android.arch.lifecycle.ViewModelProvider
import com.bojue.bsapp.course.CourseViewModel
import com.bojue.bsapp.login.LoginViewModel
import com.bojue.bsapp.myself.MyselfViewModel
import com.bojue.bsapp.register.RegisterViewModel
import com.bojue.core.viewmodel.AppViewModelFactory
import com.bojue.core.viewmodel.IViewModel
import com.bojue.core.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * create data : 2019/5/8
 * author : sendi
 * description :用于提供ViewModel。
 */
@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory



    @Binds
    @IntoMap
    @ViewModelKey(MyselfViewModel::class)
    fun myselfViewModel(viewModel: MyselfViewModel):IViewModel


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun loginViewModel(viewModel: LoginViewModel):IViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    fun registerViewModel(viewModel: RegisterViewModel):IViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CourseViewModel::class)
    fun courseViewModel(viewModel: CourseViewModel):IViewModel

}