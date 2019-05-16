package com.bojue.bsapp.order

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bojue.bsapp.R
import com.bojue.bsapp.model.OrderModel

/**
 * author: asendi.
 * data: 2019/5/16.
 * description:
 */
class OrderListAdapter(private val mOrderList : List<OrderModel>) : RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder>() {
    override fun onBindViewHolder(viewHolder: OrderListViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 12
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): OrderListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_order_list_item,parent,false)
        return OrderListViewHolder(itemView)
    }


    class OrderListViewHolder : RecyclerView.ViewHolder{

        constructor(itemView:View):super(itemView){

        }

    }
}