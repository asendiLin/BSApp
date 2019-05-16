package com.bojue.bsapp.order

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bojue.bsapp.R
import com.bojue.bsapp.model.OrderModel
import com.bojue.core.common.BaseFragment

/**
 * author: asendi.
 * data: 2019/5/16.
 * description:
 */
class OrderListFrgment : BaseFragment() {

    private lateinit var mRootView : View
    private lateinit var mOrderListAdapter : OrderListAdapter
    private lateinit var mFabOrderType : FloatingActionButton
    private lateinit var mRvOrderList : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_order_list,null,false)
        mRvOrderList = mRootView.findViewById(R.id.rv_order_list)
        mFabOrderType = mRootView.findViewById(R.id.fab_order_type)
        mOrderListAdapter = OrderListAdapter(ArrayList())
        mRvOrderList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        mRvOrderList.adapter = mOrderListAdapter
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}