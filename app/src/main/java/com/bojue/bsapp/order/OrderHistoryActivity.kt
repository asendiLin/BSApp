package com.bojue.bsapp.order

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.bojue.bsapp.R
import com.bojue.bsapp.constance.*
import com.bojue.bsapp.ext.getViewModel
import com.bojue.bsapp.model.OrderModel
import com.bojue.bsapp.util.UserManager
import com.bojue.core.common.BaseActivity

class OrderHistoryActivity : BaseActivity() {

    private val myTag = "OrderHistoryActivity"
    private lateinit var mTvTitle: TextView
    private lateinit var mRvOrderList: RecyclerView
    private lateinit var mOrderListAdapter: OrderListAdapter
    private val mOrderList = ArrayList<OrderModel>()
    private val mHistoryViewModel by lazy {
        getViewModel(OrderHistoryViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)
        initView()
        val historyType = intent.getIntExtra(HISTORY_ORDER_TYPE, DOING_ORDER)

        initDate(historyType)
    }

    private fun initDate(historyType: Int) {
//        val stuId = UserManager.getUser().stuId
        val stuId = 4
        mHistoryViewModel.getHistoryOrderList(historyType, stuId).observe(this, Observer { result ->
            Log.i(myTag, "result -> $result")
            if (result?.status == SUCCESS_STATU) {
                mOrderList.clear()
                result.data?.let { list ->
                    mOrderList.addAll(list)
                }
                mOrderListAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, result?.message, Toast.LENGTH_SHORT).show()
            }
        })

        when(historyType){
            DOING_ORDER ->{
                mTvTitle.text = "进行中"
            }

            COMPLETE_ORDER ->{
                mTvTitle.text = "已完成"
            }

            CANCEL_ORDER ->{
                mTvTitle.text = "已取消"
            }
        }
    }

    private fun initView() {
        mRvOrderList = findViewById(R.id.rv_order_history_list)
        mOrderListAdapter = OrderListAdapter(mOrderList, this)
        mRvOrderList.adapter = mOrderListAdapter
        mRvOrderList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mTvTitle= findViewById(R.id.tv_title_content)

    }
}
