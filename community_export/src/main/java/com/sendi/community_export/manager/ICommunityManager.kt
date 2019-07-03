package com.sendi.community_export.manager

import android.arch.lifecycle.LiveData
import com.sendi.base.data.BaseResponse
import com.sendi.community_export.model.CommunityModel

/**
 * create data : 2019/7/3
 * author : sendi
 * description :
 */
interface ICommunityManager {

    fun  getCommunityList(username : String) : LiveData<BaseResponse<List<CommunityModel>>>

}