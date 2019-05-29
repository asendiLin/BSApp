package com.bojue.bsapp.order

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.CardView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bojue.bsapp.R
import com.bojue.bsapp.constance.CANCEL_ORDER
import com.bojue.bsapp.constance.COMPLETE_ORDER
import com.bojue.bsapp.constance.DOING_ORDER
import com.bojue.bsapp.constance.ORDER_DETAIL
import com.bojue.bsapp.ext.getViewModel
import com.bojue.bsapp.model.OrderModel
import com.bojue.core.common.BaseActivity
import com.bumptech.glide.Glide

class OrderHistoryDetailActivity : BaseActivity(), View.OnClickListener {

    private val myTag = "OrderHistoryDetail"

    private lateinit var mIvUserIcon: ImageView
    private lateinit var mTvNickname: TextView
    private lateinit var mTvPhone: TextView
    private lateinit var mTvEndTime: TextView
    private lateinit var mTvContent: TextView
    private lateinit var mTvPrice: TextView
    private lateinit var mCvAccept: CardView
    private lateinit var mIvAcceptUserIcon: ImageView
    private lateinit var mTvAcceptNickname: TextView
    private lateinit var mTvAcceptPhone: TextView
    private lateinit var mTvComplete: TextView
    private lateinit var mTvCancel: TextView
    private lateinit var mTvDelete: TextView

    private lateinit var mOrderDetail : OrderModel

    private val mOrderStatusChangeViewModel by lazy {
        getViewModel(OrderStatusChangeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_order_detail)
        initView()
        initData()
    }

    private fun initData() {
        mOrderDetail = intent.getParcelableExtra(ORDER_DETAIL)

        mOrderStatusChangeViewModel.changeOrderStatusLiveData.observe(this, Observer { result ->
            Log.i(myTag, "initData( change -> $result")

        })

        mOrderStatusChangeViewModel.deleteOrderLiveData.observe(this, Observer {result ->
            Log.i(myTag, "initData( delete -> $result")
        })
    }

    private fun initView() {
        mIvUserIcon = findViewById(R.id.iv_user_pic)
        mTvNickname = findViewById(R.id.tv_nickname)
        mTvPhone = findViewById(R.id.tv_phone_number)
        mTvEndTime = findViewById(R.id.tv_end_time)
        mTvContent = findViewById(R.id.tv_content)
        mTvPrice = findViewById(R.id.tv_price)
        mCvAccept = findViewById(R.id.cv_accept_info)
        mIvAcceptUserIcon = findViewById(R.id.iv_accept_user_icon)
        mTvAcceptNickname = findViewById(R.id.tv_accept_nickname)
        mTvAcceptPhone = findViewById(R.id.tv_accept_phone_number)
        mTvComplete = findViewById(R.id.tv_complete_order)
        mTvCancel = findViewById(R.id.tv_cancel_order)
        mTvDelete = findViewById(R.id.tv_delete_order)

        mTvComplete.setOnClickListener(this)
        mTvCancel.setOnClickListener(this)
        mTvDelete.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_complete_order ->{
                changeOrderStatus(COMPLETE_ORDER,mOrderDetail.id,0)
            }
            R.id.tv_cancel_order ->{
                changeOrderStatus(CANCEL_ORDER,mOrderDetail.id,0)
            }
            R.id.tv_delete_order ->{
                mOrderStatusChangeViewModel.deleteOrderById(mOrderDetail.id)
            }
        }
    }

    private fun showDetail(orderModel: OrderModel, status: Int) {

        Glide.with(this).load(orderModel.student?.icon).into(mIvUserIcon)
        mTvNickname.text = orderModel.student?.nickname
        mTvEndTime.text = orderModel.time
        mTvPhone.text = orderModel.phone
        mTvPrice.text = orderModel.money.toString()
        mTvContent.text = orderModel.content

        if (orderModel.studented == null){
            mCvAccept.visibility = View.GONE
        }else{
            Glide.with(this).load(orderModel.studented.icon).into(mIvAcceptUserIcon)
            mTvAcceptNickname.text=orderModel.studented.nickname
            mTvAcceptPhone.text = orderModel.studented.phone
        }
//        when (status) {
//            DOING_ORDER -> {
//
//            }
//            COMPLETE_ORDER -> {
//
//            }
//            CANCEL_ORDER -> {
//
//            }
//        }
    }

    private fun changeOrderStatus(status: Int, id: Int, studentId: Int) {
        mOrderStatusChangeViewModel.changeStatus(status, id, studentId)
    }
}
