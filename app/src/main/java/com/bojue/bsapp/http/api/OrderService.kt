package com.bojue.bsapp.http.api

import com.bojue.bsapp.model.BaseResponse
import com.bojue.bsapp.model.OrderModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
interface OrderService {

    @FormUrlEncoded
    @POST("Orders/getOrderByType.action")
    fun getOrderList(@Field("type")type : Int):Call<BaseResponse<List<OrderModel>>>

    @FormUrlEncoded
    @POST("Orders/getOrderBystatus.action")
    fun getOrderHistoryList(@Field("studentId")stuId:Int,@Field("status")type:Int):Call<BaseResponse<List<OrderModel>>>

    fun getOrderDetail() :Call<BaseResponse<List<OrderModel>>>


    @FormUrlEncoded
    @POST("Orders/deleteById.action")
    fun deleteOrder(@Field("id") id : Int):Call<BaseResponse<Any>>

    /**
     * status=1&id=10&studentId=3 接单
     * status=2&id=10&studentId=0 取消
     * status=3&id=14&studentId=0 完成
     */
    @FormUrlEncoded
    @POST("Orders/setStatus.action")
    fun changeOrderStatus(@Field("status") status :Int ,@Field("id") id:Int,@Field("studentId") stuId:Int):Call<BaseResponse<Any>>

    @FormUrlEncoded
    @POST("Orders/publish.action")
    fun publishOrder(@Field("studentId") studentId :Int, @Field("type") type :Int,
                     @Field("time") time :String,@Field("status")status:Int,
                     @Field("money")money: Int,@Field("address")address:String,
                     @Field("phone")phone: String,@Field("content")content:String
    ):Call<BaseResponse<Any>>

}