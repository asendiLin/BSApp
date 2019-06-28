package com.sendi.order.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.sendi.base.constance.DOING_ORDER
import com.sendi.base.constance.FAIL_STATU
import com.sendi.base.data.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class OrderRepository @Inject constructor(val service : com.sendi.order.repository.OrderService) {

    private val myTag = "OrderRepository"

    private val mOrderListLiveData = MutableLiveData<BaseResponse<List<com.sendi.order.model.OrderModel>>>()
    private val mHistoryOrderListLiveData = MutableLiveData<BaseResponse<List<com.sendi.order.model.OrderModel>>>()
    private val mOrderDetailLiveData = MutableLiveData<BaseResponse<com.sendi.order.model.OrderModel>>()
    private val mAcceptOrderLiveData = MutableLiveData<BaseResponse<Any>>()
    private val mCancelOrderLiveData = MutableLiveData<BaseResponse<Any>>()

    val changeOrderStatusLiveData = MutableLiveData<BaseResponse<String>>()
    val publishOrderLiveData = MutableLiveData<BaseResponse<Any>>()
    val deleteOrderLiveData = MutableLiveData<BaseResponse<String>>()
    fun getOrderList(type : Int): LiveData<BaseResponse<List<com.sendi.order.model.OrderModel>>> {

        service.getOrderList(type).enqueue(object : Callback<BaseResponse<List<com.sendi.order.model.OrderModel>>>{
            override fun onFailure(call: Call<BaseResponse<List<com.sendi.order.model.OrderModel>>>?, t: Throwable?) {
                Log.i(myTag,"onFailure -> ${t?.message}")
                mOrderListLiveData.postValue(BaseResponse(null, FAIL_STATU, "网络出错", 0))
            }

            override fun onResponse(call: Call<BaseResponse<List<com.sendi.order.model.OrderModel>>>?,
                                    response: Response<BaseResponse<List<com.sendi.order.model.OrderModel>>>?) {
                Log.i(myTag,"onResponse -> ${response?.body()}")
                if (response?.isSuccessful == true){
                    val data = response.body()
                    data?.let {
                        mOrderListLiveData.postValue(data)
                    }
                }else{
                    mOrderListLiveData.postValue(BaseResponse(null, FAIL_STATU, "获取数据失败", 0))
                }
            }
        })

        return mOrderListLiveData
    }

    fun changeOrderStatus( status :Int ,id:Int,stuId:Int):LiveData<BaseResponse<String>>{

        service.changeOrderStatus(status,id,stuId).enqueue(object : Callback<BaseResponse<String>>{
            override fun onFailure(call: Call<BaseResponse<String>>?, t: Throwable?) {
                Log.i(myTag,"onFailure -> ${t?.message}")
                changeOrderStatusLiveData.postValue(BaseResponse(null, FAIL_STATU, "网络出错", 0))
            }

            override fun onResponse(call: Call<BaseResponse<String>>?, response: Response<BaseResponse<String>>?) {
                Log.i(myTag,"onResponse -> ${response?.body()}")
                if (response?.isSuccessful == true){
                    changeOrderStatusLiveData.postValue(response.body())
                }else{
                    changeOrderStatusLiveData.postValue(BaseResponse(null, FAIL_STATU, "数据获取失败", 0))
                }
            }
        })

        return changeOrderStatusLiveData
    }

    fun getHistoryOrderList(orderType : Int,stuId :Int):LiveData<BaseResponse<List<com.sendi.order.model.OrderModel>>>{

        service.getOrderHistoryList(stuId,orderType).enqueue(object : Callback<BaseResponse<List<com.sendi.order.model.OrderModel>>>{
            override fun onFailure(call: Call<BaseResponse<List<com.sendi.order.model.OrderModel>>>?, t: Throwable?) {
                Log.i(myTag,"onFailure -> ${t?.message}")
                mHistoryOrderListLiveData.postValue(BaseResponse(null, FAIL_STATU, "网络出错", 0))
            }

            override fun onResponse(call: Call<BaseResponse<List<com.sendi.order.model.OrderModel>>>?,
                                    response: Response<BaseResponse<List<com.sendi.order.model.OrderModel>>>?) {
                Log.i(myTag,"onResponse -> $response")
                if (response?.isSuccessful == true){
                    val data = response.body()
                    data?.let {
                        mHistoryOrderListLiveData.postValue(data)
                    }
                }else{
                    mHistoryOrderListLiveData.postValue(BaseResponse(null, FAIL_STATU, "获取数据失败", 0))
                }

            }
        })

        return mHistoryOrderListLiveData
    }

    fun acceptOrder(){
    }

    fun deleteOrder(id :Int):LiveData<BaseResponse<String>>{
        service.deleteOrder(id).enqueue(object : Callback<BaseResponse<String>>{
            override fun onFailure(call: Call<BaseResponse<String>>?, t: Throwable?) {
                Log.i(myTag,"onFailure -> ${t?.message}")
                deleteOrderLiveData.postValue(BaseResponse(null, FAIL_STATU, "网络出错", 0))
            }

            override fun onResponse(call: Call<BaseResponse<String>>?, response: Response<BaseResponse<String>>?) {
                Log.i(myTag,"onResponse-> ${response?.body()}")
                if (response?.isSuccessful == true){
                    deleteOrderLiveData.postValue(response.body())
                }else{
                    deleteOrderLiveData.postValue(BaseResponse(null, FAIL_STATU, "数据请求出错", 0))
                }

            }
        })

        return deleteOrderLiveData
    }

    fun publishOrder(content :String,stuId:Int,type:Int,phone:String,
                     price:Int,endDate : String,address:String){

        service.publishOrder(stuId,type,endDate, DOING_ORDER,price,address,phone,content)
                .enqueue(object : Callback<BaseResponse<Any>>{
                    override fun onFailure(call: Call<BaseResponse<Any>>?, t: Throwable?) {
                        Log.i(myTag,"onFailure -> ${t?.message}")
                        publishOrderLiveData.postValue(BaseResponse(null, FAIL_STATU, "网络出错", 0))
                    }

                    override fun onResponse(call: Call<BaseResponse<Any>>?, response: Response<BaseResponse<Any>>?) {
                        Log.i(myTag,"onResponse-> ${response?.body()?.data}")
                        if (response?.isSuccessful == true){
                            if (response.body() == null){
                                publishOrderLiveData.postValue(BaseResponse(null, FAIL_STATU, "数据获取失败", 0))
                            }else{
                                publishOrderLiveData.postValue(response.body())
                            }
                        }else{
                            publishOrderLiveData.postValue(BaseResponse(null, FAIL_STATU, "网络出错", 0))
                        }

                    }
                })

    }
}