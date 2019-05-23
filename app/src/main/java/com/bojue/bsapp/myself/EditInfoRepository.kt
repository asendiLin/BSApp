package com.bojue.bsapp.myself

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.bojue.bsapp.http.api.MyselfService
import com.bojue.bsapp.model.BaseResponse
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/23.
 * description:
 */
class EditInfoRepository @Inject constructor(val service : MyselfService) {

    val mEditInfoLiveData = MutableLiveData<BaseResponse<Any?>>()

    fun editInfo(): LiveData<BaseResponse<Any?>> {

        return mEditInfoLiveData
    }

}