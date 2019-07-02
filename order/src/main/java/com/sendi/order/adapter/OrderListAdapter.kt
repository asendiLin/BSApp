package com.sendi.order.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sendi.base.util.ShowImageUtil
import com.sendi.order.R

/**
 * author: asendi.
 * data: 2019/5/16.
 * description:
 */
class OrderListAdapter(private val mOrderList : List<com.sendi.order.model.OrderModel>, private val mContext:Context)
    : RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder>() {

    private var mOnOrderIntemClickListener : OnOrderIntemClickListener? =null

    fun setOnOrderIntemClickListener(listner : OnOrderIntemClickListener){
        this.mOnOrderIntemClickListener = listner
    }

    override fun onBindViewHolder(viewHolder: OrderListViewHolder, position: Int) {
        val orderModel = mOrderList[position]

        viewHolder.tvUserNickname.text = orderModel.student?.nickname
        ShowImageUtil.showImage(mContext,viewHolder.ivUserIcon,orderModel.student?.icon)
        viewHolder.tvEndDate.text = orderModel.time
        viewHolder.tvContent.text = orderModel.content
        viewHolder.tvEndDate.text = orderModel.time
        viewHolder.tvAddress.text = orderModel.address
        viewHolder.tvPrice.text = orderModel.money.toString()
        viewHolder.itemView.setOnClickListener {
            mOnOrderIntemClickListener?.onOrderItemClick(position)
        }

    }

    override fun getItemCount(): Int {
       return mOrderList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): OrderListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_order_list_item,parent,false)
        return OrderListViewHolder(itemView)
    }


    class OrderListViewHolder : RecyclerView.ViewHolder{

        val ivUserIcon : ImageView
        val tvUserNickname :TextView
        val tvEndDate : TextView
        val tvContent : TextView
        val tvAddress : TextView
        val tvPrice : TextView
        constructor(itemView:View):super(itemView){
            ivUserIcon = itemView.findViewById(R.id.img_user_pic)
            tvUserNickname = itemView.findViewById(R.id.tv_nickname)
            tvEndDate = itemView.findViewById(R.id.txt_date)
            tvContent = itemView.findViewById(R.id.txt_content)
            tvAddress = itemView.findViewById(R.id.txt_address)
            tvPrice = itemView.findViewById(R.id.txt_price)
        }

    }

    interface OnOrderIntemClickListener {
        fun onOrderItemClick(position: Int)
    }
}