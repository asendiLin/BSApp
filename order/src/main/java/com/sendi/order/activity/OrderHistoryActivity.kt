package com.sendi.order.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bojue.core.ext.getViewModel
import com.sendi.order.model.OrderModel
import com.bojue.core.common.BaseActivity
import com.bojue.core.event.EventUtil
import com.example.order.R
import com.sendi.base.constance.*
import com.sendi.base.event.RefreshEvent
import com.sendi.base.widget.LoadingDialog
import com.sendi.order.adapter.OrderListAdapter
import com.sendi.order.viewmodel.OrderHistoryViewModel
import org.greenrobot.eventbus.Subscribe

class OrderHistoryActivity : BaseActivity() {

    private val myTag = "OrderHistoryActivity"
    private lateinit var mTvTitle: TextView
    private lateinit var mRvOrderList: RecyclerView
    private lateinit var mBtnReload : Button
    private lateinit var mOrderListAdapter: OrderListAdapter
    private val mOrderList = ArrayList<com.sendi.order.model.OrderModel>()
    private val mLoadingDialog by lazy {
        LoadingDialog(this)
    }
    private val mHistoryViewModel by lazy {
        getViewModel(OrderHistoryViewModel::class.java)
    }

    private var mOrderType = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)
        initView()
        val historyType = intent.getIntExtra(HISTORY_ORDER_TYPE, DOING_ORDER)
        mOrderType = historyType
        initDate(historyType)
        EventUtil.register(this)
    }

    private fun initDate(historyType: Int) {
        mLoadingDialog.show()
        val stuId = UserManager.getUser().id
        mHistoryViewModel.getHistoryOrderList(historyType, stuId).observe(this, Observer { result ->
            Log.i(myTag, "result -> $result")
            mLoadingDialog.dismiss()
            if (result?.status == SUCCESS_STATU) {
                mOrderList.clear()
                result.data?.let { list ->
                    mOrderList.addAll(list)
                }
                mOrderListAdapter.notifyDataSetChanged()
                if (mOrderList.size == 0){
                    mBtnReload.visibility = View.VISIBLE
                    mBtnReload.text = "没有订单啦"
                }
            } else {
                mBtnReload.visibility = View.VISIBLE
                mRvOrderList.visibility = View.GONE
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
        mOrderListAdapter.setOnOrderIntemClickListener(object : OrderListAdapter.OnOrderIntemClickListener{
            override fun onOrderItemClick(position: Int) {
                val intent = Intent(this@OrderHistoryActivity,OrderHistoryDetailActivity::class.java)
                intent.putExtra(ORDER_DETAIL,mOrderList[position])
                intent.putExtra(HISTORY_ORDER_TYPE,mOrderType)
                intent.putExtra(POSITION, position)
                startActivity(intent)
            }
        })
        mRvOrderList.adapter = mOrderListAdapter
        mRvOrderList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mTvTitle= findViewById(R.id.tv_title_content)
        mBtnReload =findViewById(R.id.btn_order_reload)
        mBtnReload.setOnClickListener {
            mBtnReload.visibility = View.GONE
            mRvOrderList.visibility = View.VISIBLE
            mLoadingDialog.show()
            val type = intent.getIntExtra(HISTORY_ORDER_TYPE, DOING_ORDER)
            val stuId = UserManager.getUser().id
            mHistoryViewModel.getHistoryOrderList(type, stuId)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventUtil.unregister(this)
    }

    @Subscribe
    fun onRefreshEvent(event : RefreshEvent){
        val position = event.position
        mOrderList.removeAt(position)
        mOrderListAdapter.notifyDataSetChanged()

        if (mOrderList.size == 0){
            mBtnReload.visibility = View.VISIBLE
            mBtnReload.text = "没有订单啦"
        }
    }

}
