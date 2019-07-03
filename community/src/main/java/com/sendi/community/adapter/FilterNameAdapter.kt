package com.sendi.community.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sendi.community.R

/**
 * author: asendi.
 * data: 2019/5/15.
 * description:
 */
class FilterNameAdapter(private val filterNameList: List<String>)
    : RecyclerView.Adapter<FilterNameAdapter.FilterNameViewHolder>() {

    private var mItemListener : OnClickFilterItemListener? = null

    fun setOnClickItemListener(listener : OnClickFilterItemListener){
        this.mItemListener = listener
    }

    override fun getItemCount(): Int {
        return filterNameList.size
    }

    override fun onBindViewHolder(viewHolder: FilterNameViewHolder, position: Int) {
        viewHolder.tvFilterName.text = filterNameList[position]
        viewHolder.tvFilterName.setOnClickListener {
            mItemListener?.onItemClick(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FilterNameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.filter_name_item, parent, false)
        return FilterNameViewHolder(view)
    }


    class FilterNameViewHolder : RecyclerView.ViewHolder {

        val tvFilterName: TextView
        constructor(itemView: View) : super(itemView) {
            tvFilterName = itemView.findViewById(R.id.tv_filter_name)
        }
    }

    interface OnClickFilterItemListener{
        fun onItemClick(position : Int)
    }
}