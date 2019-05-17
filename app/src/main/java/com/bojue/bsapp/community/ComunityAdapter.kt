package com.bojue.bsapp.community

import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bojue.bsapp.R
import com.sackcentury.shinebuttonlib.ShineButton

/**
 * author: asendi.
 * data: 2019/5/11.
 * description:
 */
class ComunityAdapter(private val mActivity: FragmentActivity, private val mCommunityList: ArrayList<CommunityViewModel>)
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

            vh.btnLike?.init(mActivity)

            view.tag = vh
        } else {
            vh = convertView.tag as CommunityViewHolder
            view = convertView
        }

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
        return mCommunityList.size + 10
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