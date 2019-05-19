package com.bojue.bsapp.order

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.bojue.bsapp.R
import com.bojue.bsapp.model.OrderModel
import com.bojue.core.common.BaseFragment

/**
 * author: asendi.
 * data: 2019/5/16.
 * description:
 */
class OrderListFrgment : BaseFragment() ,View.OnClickListener{
    private lateinit var mRootView : View

    private lateinit var mOrderListAdapter : OrderListAdapter
    private lateinit var mFabOrderType : FloatingActionButton
    private lateinit var mRvOrderList : RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_order_list,null,false)
        mRvOrderList = mRootView.findViewById(R.id.rv_order_list)
        mFabOrderType = mRootView.findViewById(R.id.fab_order_type)
        mFabOrderType.setOnClickListener(this)
        mOrderListAdapter = OrderListAdapter(ArrayList())
        mRvOrderList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        mRvOrderList.adapter = mOrderListAdapter
        mOrderListAdapter.setOnOrderIntemClickListener(object : OrderListAdapter.OnOrderIntemClickListener{
            override fun onOrderItemClick(position: Int) {
                val intent = Intent(requireActivity(),OrderDetailActivity::class.java)
                startActivity(intent)
            }
        })
        return mRootView
    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.fab_order_type ->{
                showSelectTypeDialog()
            }
        }

    }

    private fun showSelectTypeDialog(){
        val bottomDialog = Dialog(context, R.style.BottomDialog)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_order_type, null, false)
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
    }
}