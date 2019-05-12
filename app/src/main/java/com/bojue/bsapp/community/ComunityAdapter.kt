package com.bojue.bsapp.community

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bojue.bsapp.R

/**
 * author: asendi.
 * data: 2019/5/11.
 * description:
 */
class ComunityAdapter(private val mContext : Context?,private val mCommunityList: ArrayList<CommunityViewModel>)
    : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.community_item,parent,false)
        return view
    }

    override fun getItem(position: Int): Any {
        return mCommunityList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mCommunityList.size+10
    }
}