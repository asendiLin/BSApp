package com.sendi.order.viewmodel

import com.bojue.core.viewmodel.IViewModel
import com.bojue.core.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * create data : 2019/7/1
 * author : sendi
 * description :
 */
@Module
interface OrderViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel::class)
    fun orderViewModel(viewModel: OrderViewModel): IViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderDetailViewModel::class)
    fun orderDetailViewModel(viewModel: OrderDetailViewModel):IViewModel

}