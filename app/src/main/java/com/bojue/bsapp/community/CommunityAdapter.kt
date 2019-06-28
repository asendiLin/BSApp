package com.bojue.bsapp.community

import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bojue.bsapp.R
import com.bojue.bsapp.model.CommunityModel
import com.bojue.bsapp.util.ShowImageUtil
import com.sackcentury.shinebuttonlib.ShineButton

/**
 * author: asendi.
 * data: 2019/5/11.
 * description:
 */
class CommunityAdapter(private val mActivity: FragmentActivity, private val mCommunityList: ArrayList<CommunityModel>)
    : BaseAdapter() {

    private var mClickLitener : OnClickItemListener? =null

    fun setOnClickListener(listener : OnClickItemListener){
        this.mClickLitener = listener
    }

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
        ShowImageUtil.showImage(mActivity,vh.ivCommunityItem!!,mCommunityList[position].pic)
        ShowImageUtil.showImage(mActivity,vh.ivUserImage!!,mCommunityList[position].icon)

        if (mCommunityList[position].isLike == null){
            vh.btnLike?.setOnClickListener {
                mClickLitener?.like(position)
            }
        }else{
            vh.btnLike?.isEnabled = false
//            vh.btnLike?.setBtnColor(R.color.colorRed)
            vh.btnLike?.isChecked = true
        }

        vh.btnComment?.setOnClickListener {
            mClickLitener?.comment(position)
        }
        vh.btnShare?.setOnClickListener {
            mClickLitener?.share(position)
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

    interface OnClickItemListener {
        fun like(position : Int)
        fun share(position :Int)
        fun comment(position:Int)
    }
}