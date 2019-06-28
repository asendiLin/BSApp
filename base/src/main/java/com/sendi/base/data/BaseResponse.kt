package com.sendi.base.data

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
data class  BaseResponse<T>(val data:T?,val status:Int,val message : String?,val code : Int?)