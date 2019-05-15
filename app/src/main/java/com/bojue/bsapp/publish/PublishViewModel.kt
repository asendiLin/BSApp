package com.bojue.bsapp.publish

import android.app.Application
import android.arch.lifecycle.LiveData
import com.bojue.bsapp.model.PublishModel
import com.bojue.bsapp.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * Created by lizheng on 2019/5/13.
 */
class PublishViewModel @Inject constructor(val repository: PublishRepository,application: Application): BaseViewModel(application) {

    fun publishData(): LiveData<PublishModel> {
        return repository.publishData()
    }
}