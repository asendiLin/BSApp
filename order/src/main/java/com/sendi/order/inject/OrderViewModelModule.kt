package com.sendi.order.inject

import com.bojue.core.viewmodel.IViewModel
import com.bojue.core.viewmodel.ViewModelKey
import com.sendi.order.viewmodel.OrderDetailViewModel
import com.sendi.order.viewmodel.OrderHistoryViewModel
import com.sendi.order.viewmodel.OrderStatusChangeViewModel
import com.sendi.order.viewmodel.OrderViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(OrderHistoryViewModel::class)
    fun orderHistoryViewModel(viewModel: OrderHistoryViewModel):IViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderStatusChangeViewModel::class)
    fun orderStatusChangeViewModel(viewModel: OrderStatusChangeViewModel):IViewModel
}