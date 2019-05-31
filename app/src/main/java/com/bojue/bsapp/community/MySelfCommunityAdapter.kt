package com.bojue.bsapp.community

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bojue.bsapp.R
import com.bojue.bsapp.model.CommunityModel
import com.bojue.bsapp.util.ShowImageUtil
import com.bojue.bsapp.util.UserManager
import com.bumptech.glide.Glide

/**
 * author: asendi.
 * data: 2019/5/28.
 * description:
 */
class MySelfCommunityAdapter constructor(private val mCommunityList : ArrayList<CommunityModel>,private val mContext : Context)
    : RecyclerView.Adapter<MySelfCommunityAdapter.MySelfCommunityViewHolder>(){

    private var mDeleteCallback : OnDeleteCommunityCallback? =null

    fun setOnDeleteCallback(callback: OnDeleteCommunityCallback){
        this.mDeleteCallback =callback
    }

    override fun onBindViewHolder(viewHolder: MySelfCommunityViewHolder, position: Int) {
        val communityViewModel = mCommunityList[position]
        val icon = UserManager.getUser().icon
        val nickname = UserManager.getUser().nickname
        ShowImageUtil.showImage(mContext,viewHolder.ivComunityIcon,icon)
        ShowImageUtil.showImage(mContext,viewHolder.ivComunityImage,communityViewModel.pic)
//        Glide.with(mContext).load(communityViewModel.icon).into(viewHolder.ivComunityIcon)
//        Glide.with(mContext).load(communityViewModel.pic).into(viewHolder.ivComunityImage)
        viewHolder.tvCommunityNickname.text = nickname
        viewHolder.tvComunityContent.text = communityViewModel.content
        viewHolder.tvCommunityZan.text = "(${communityViewModel.likes})"
        viewHolder.tvDeleteCommunity.setOnClickListener {
            mDeleteCallback?.onDelete(position)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MySelfCommunityViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.community_self_item,parent,false)
        return MySelfCommunityViewHolder(itemView)
    }

    override fun getItemCount(): Int {
      return  mCommunityList.size
    }


    class MySelfCommunityViewHolder : RecyclerView.ViewHolder{
        val ivComunityImage :ImageView
        val tvComunityContent : TextView
        val tvCommunityZan :TextView
        val tvCommunityComtent :TextView
        val ivComunityIcon : ImageView
        val tvCommunityNickname :TextView
        val tvDeleteCommunity : TextView

        constructor(itemView : View):super(itemView){
            ivComunityIcon = itemView.findViewById(R.id.iv_self_community__icon)
            tvCommunityNickname = itemView.findViewById(R.id.tv_self_community_nickname)
            ivComunityImage = itemView.findViewById(R.id.iv_self_community_image)
            tvComunityContent = itemView.findViewById(R.id.tv_content_self_community_content)
            tvCommunityZan  = itemView.findViewById(R.id.tv_self_community_zan)
            tvCommunityComtent = itemView.findViewById(R.id.tv_self_community_commnent)
            tvDeleteCommunity = itemView.findViewById(R.id.tv_delete_community)
        }
    }

    interface OnDeleteCommunityCallback{
        fun onDelete(position: Int)
    }
}