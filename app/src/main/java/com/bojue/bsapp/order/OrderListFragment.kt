package com.bojue.bsapp.order

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bojue.bsapp.R
import com.bojue.bsapp.constance.*
import com.bojue.core.ext.getViewModel
import com.bojue.bsapp.model.OrderModel
import com.bojue.core.common.BaseFragment

/**
 * author: asendi.
 * data: 2019/5/16.
 * description:
 */
class OrderListFragment : BaseFragment() ,View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{
    private val myTag = "OrderListFragment"

    private var mCurrentType = originType

    private lateinit var mRootView : View
    private lateinit var mOrderListAdapter : OrderListAdapter
    private lateinit var mFabOrderType : FloatingActionButton
    private lateinit var mRvOrderList : RecyclerView
    private lateinit var mSrlOrderList:SwipeRefreshLayout
    private lateinit var mBtnReload : Button
    private val mOrderList = ArrayList<OrderModel>()
    private val mOrderViewModel by lazy {
        getViewModel(OrderViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_order_list,null,false)
        mRvOrderList = mRootView.findViewById(R.id.rv_order_list)
        mSrlOrderList = mRootView.findViewById(R.id.srl_order_list)
        mFabOrderType = mRootView.findViewById(R.id.fab_order_type)
        mBtnReload = mRootView.findViewById(R.id.btn_order_reload)
        mSrlOrderList.setColorSchemeResources(R.color.colorTheme)
        mSrlOrderList.setProgressViewOffset(true, 0, 10)
        mSrlOrderList.setOnRefreshListener(this)
        mFabOrderType.setOnClickListener(this)
        mBtnReload.setOnClickListener(this)
        mOrderListAdapter = OrderListAdapter(mOrderList,requireActivity())
        mRvOrderList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        mRvOrderList.adapter = mOrderListAdapter
        mOrderListAdapter.setOnOrderIntemClickListener(object : OrderListAdapter.OnOrderIntemClickListener{
            override fun onOrderItemClick(position: Int) {
                val intent = Intent(requireActivity(),OrderDetailActivity::class.java)
                intent.putExtra(ORDER_DETAIL,mOrderList[position])
                requireContext().startActivity(intent)
            }
        })
        return mRootView
    }


    override fun onClick(v: View?) {

        when(v?.id){
            R.id.fab_order_type ->{
                showSelectTypeDialog()
            }
            R.id.btn_order_reload ->{
                getOrderList(mCurrentType)
            }
        }
    }

    private fun showSelectTypeDialog(){
        val bottomDialog = Dialog(context, R.style.BottomDialog)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_order_type, null, false)

        view.findViewById<TextView>(R.id.tv_order_type1).setOnClickListener {
            getOrderList(paotui)
            bottomDialog.dismiss()
        }
        view.findViewById<TextView>(R.id.tv_order_type2).setOnClickListener {
            getOrderList(daina)
            bottomDialog.dismiss()
        }
        view.findViewById<TextView>(R.id.tv_order_type3).setOnClickListener {
            getOrderList(pinche)
            bottomDialog.dismiss()
        }
       view.findViewById<TextView>(R.id.tv_order_type4).setOnClickListener {
            getOrderList(shunfengche)
           bottomDialog.dismiss()
        }
        view.findViewById<TextView>(R.id.tv_order_type5).setOnClickListener {
            getOrderList(huhuan)
            bottomDialog.dismiss()
        }
        view.findViewById<TextView>(R.id.tv_order_type6).setOnClickListener {
            getOrderList(peiban)
            bottomDialog.dismiss()
        }
        view.findViewById<TextView>(R.id.tv_order_type7).setOnClickListener {
            getOrderList(other)
            bottomDialog.dismiss()
        }
        view.findViewById<TextView>(R.id.tv_order_all).setOnClickListener {
            getOrderList(originType)
            bottomDialog.dismiss()
        }

        bottomDialog.setContentView(view)
        val window = bottomDialog.window
        window.setGravity(Gravity.BOTTOM)
        val params = window.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = params
        bottomDialog.show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mSrlOrderList.isRefreshing =true

        mOrderViewModel.getOrderList(0).observe(this, Observer {result ->
            Log.i(myTag,"result-> $result")
            mSrlOrderList.isRefreshing =false
            mFabOrderType.isEnabled = true
            if (result?.status == SUCCESS_STATU){
                result.data?.let { data ->
                    mBtnReload.visibility = View.GONE
                    mOrderList.clear()
                    mOrderList.addAll(data)
                    mOrderListAdapter.notifyDataSetChanged()
                }
            }else{
                mSrlOrderList.visibility = View.GONE
                mBtnReload.visibility = View.VISIBLE
                Toast.makeText(requireContext(),result?.message,Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onRefresh() {
        getOrderList(mCurrentType)
        Log.i(myTag,"onRefresh ->")
    }

    private fun getOrderList(type :Int){
        mBtnReload.visibility = View.GONE
        mSrlOrderList.visibility = View.VISIBLE
        mCurrentType = type
        mFabOrderType.isEnabled = false
        if (!mSrlOrderList.isRefreshing){
            mSrlOrderList.isRefreshing = true
        }
        mOrderViewModel.getOrderList(type)
    }
}