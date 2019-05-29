package com.bojue.bsapp.community

import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bojue.bsapp.R
import com.bojue.bsapp.constance.BASE_URL
import com.bojue.bsapp.constance.BASE_URL_KEY
import com.bojue.bsapp.model.CommunityModel
import com.bojue.bsapp.util.SPUtils
import com.bumptech.glide.Glide
import com.sackcentury.shinebuttonlib.ShineButton

/**
 * author: asendi.
 * data: 2019/5/11.
 * description:
 */
class CommunityAdapter(private val mActivity: FragmentActivity, private val mCommunityList: ArrayList<CommunityModel>)
    : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var vh: CommunityViewHolder
        var view: View

        if (convertView == null) {
            vh = CommunityViewHolder()
            view = LayoutInflater.from(mActivity)
                    .inflate(R.layout.community_item, parent, false)

            vh.btnLike = view.findViewById(R.id.btn_like)
            vh.btnComment = view.findViewById(R.id.btn_comment)
            vh.btnShare = view.findViewById(R.id.btn_share)
            vh.tvCommunityContent = view.findViewById(R.id.tv_content)
            vh.ivCommunityItem = view.findViewById(R.id.iv_community_item)
            vh.ivUserImage = view.findViewById(R.id.iv_user_img)
            vh.tvNickname = view.findViewById(R.id.tv_nickname)
            vh.btnLike?.init(mActivity)

            view.tag = vh
        } else {
            vh = convertView.tag as CommunityViewHolder
            view = convertView
        }
        vh.tvCommunityContent?.text = mCommunityList[position].content
        vh.tvNickname?.text = mCommunityList[position].nickname
        Glide.with(mActivity).load("${SPUtils.getString(mActivity, BASE_URL_KEY, BASE_URL)}${mCommunityList[position].pic}").into(vh.ivCommunityItem)
        Glide.with(mActivity).load(mCommunityList[position].icon).into(vh.ivUserImage)

        vh.btnLike?.setOnClickListener {
            Toast.makeText(mActivity, "like-->$position", Toast.LENGTH_SHORT).show()
        }
        vh.btnComment?.setOnClickListener {
            Toast.makeText(mActivity, "评论-->$position", Toast.LENGTH_SHORT).show()
        }
        vh.btnShare?.setOnClickListener {
            Toast.makeText(mActivity, "分享-->$position", Toast.LENGTH_SHORT).show()
        }


        return view
    }

    override fun getItem(position: Int): Any {
        return mCommunityList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mCommunityList.size
    }

    inner class CommunityViewHolder {
        var ivCommunityItem: ImageView? = null
        var ivUserImage: ImageView? = null
        var tvNickname: TextView? = null
        var tvCommunityContent: TextView? = null
        var btnLike: ShineButton? = null
        var btnComment: ImageButton? = null
        var btnShare: ImageButton? = null
    }
}