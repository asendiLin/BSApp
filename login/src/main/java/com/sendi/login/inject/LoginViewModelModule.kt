package com.sendi.login.inject

import android.arch.lifecycle.ViewModelProvider
import com.bojue.core.viewmodel.AppViewModelFactory
import com.bojue.core.viewmodel.IViewModel
import com.bojue.core.viewmodel.ViewModelKey
import com.sendi.login.viewmodel.LoginViewModel
import com.sendi.login.viewmodel.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * create data : 2019/7/4
 * author : sendi
 * description :
 */
@Module
interface LoginViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun loginViewModel(viewModel:LoginViewModel): IViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    fun registerViewModel(viewModel: RegisterViewModel): IViewModel

}