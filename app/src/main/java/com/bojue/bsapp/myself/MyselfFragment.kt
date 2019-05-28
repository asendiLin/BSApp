package com.bojue.bsapp.myself

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import com.bojue.bsapp.R
import com.bojue.core.common.BaseFragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bojue.bsapp.constance.CANCEL_ORDER
import com.bojue.bsapp.constance.COMPLETE_ORDER
import com.bojue.bsapp.constance.DOING_ORDER
import com.bojue.bsapp.constance.HISTORY_ORDER_TYPE
import com.bojue.bsapp.course.CourseActivity
import com.bojue.bsapp.order.OrderHistoryActivity
import com.bojue.bsapp.setting.SettingActivity
import com.bojue.bsapp.util.UserManager
import com.bumptech.glide.Glide


/**
 * author: asendi.
 * data: 2019/5/10.
 * description:
 */
class MyselfFragment : BaseFragment(), View.OnClickListener {
    private lateinit var mRootView: View

    private lateinit var mTbTop: Toolbar
    private lateinit var mAblTop: AppBarLayout
    private lateinit var mCtlTop: CollapsingToolbarLayout
    private lateinit var mLLCourse: LinearLayout
    private lateinit var mTvEditInfo: TextView
    private lateinit var mIbSetting: ImageButton
    private lateinit var mIvUserIcon: ImageView
    private lateinit var mTvNickname: TextView
    private lateinit var mTvSignature: TextView
    private lateinit var mTvDoingCount :TextView
    private lateinit var mTvCancelCount :TextView
    private lateinit var mTvCompleteCount :TextView
    private lateinit var mLlDoingCount : LinearLayout
    private lateinit var mLlCancelCount : LinearLayout
    private lateinit var mLlCompleteCount : LinearLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = LayoutInflater.from(context).inflate(R.layout.fragment_myself_layout, null, false)
        mTbTop = mRootView.findViewById(R.id.tb_top)
        mAblTop = mRootView.findViewById(R.id.abl_self_top)
        mCtlTop = mRootView.findViewById(R.id.ctbl_self_top)
        mLLCourse = mRootView.findViewById(R.id.ll_course)
        mTvEditInfo = mRootView.findViewById(R.id.tv_edit_info)
        mIbSetting = mRootView.findViewById(R.id.ib_setting)
        mIvUserIcon = mRootView.findViewById(R.id.civ_myself_user)
        mTvNickname = mRootView.findViewById(R.id.tv_myself_nickname)
        mTvSignature = mRootView.findViewById(R.id.tv_myself_signature)
        mTvDoingCount = mRootView.findViewById(R.id.tv_doing_count)
        mTvCompleteCount = mRootView.findViewById(R.id.tv_complete_count)
        mTvCancelCount = mRootView.findViewById(R.id.tv_cancel_count)
        mLlDoingCount = mRootView.findViewById(R.id.ll_doing_count)
        mLlCompleteCount = mRootView.findViewById(R.id.ll_complete_count)
        mLlCancelCount = mRootView.findViewById(R.id.ll_cancel_count)

        val user = UserManager.getUser()
        mTvNickname.text = user.nickname
        mTvSignature.text = user.signature
        Glide.with(this).load(user.icon).into(mIvUserIcon)

        mIbSetting.setOnClickListener(this)
        mTvEditInfo.setOnClickListener(this)
        mLLCourse.setOnClickListener(this)
        mLlDoingCount.setOnClickListener(this)
        mLlCancelCount.setOnClickListener(this)
        mLlCompleteCount.setOnClickListener(this)

        (activity as AppCompatActivity).setSupportActionBar(mTbTop)
        mCtlTop.title = "sendi"

        return mRootView
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_course -> {
                val intent = Intent(requireContext(), CourseActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_edit_info -> {
                val intent = Intent(requireContext(), EditSelfActivity::class.java)
                startActivity(intent)
            }
            R.id.ib_setting -> {
                val intent = Intent(requireContext(), SettingActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_doing_count ->{
                val intent = Intent(requireContext(),OrderHistoryActivity::class.java)
                intent.putExtra(HISTORY_ORDER_TYPE, DOING_ORDER)
                startActivity(intent)
            }
            R.id.ll_cancel_count ->{
                val intent = Intent(requireContext(),OrderHistoryActivity::class.java)
                intent.putExtra(HISTORY_ORDER_TYPE, CANCEL_ORDER)
                startActivity(intent)
            }
            R.id.ll_complete_count ->{
                val intent = Intent(requireContext(),OrderHistoryActivity::class.java)
                intent.putExtra(HISTORY_ORDER_TYPE, COMPLETE_ORDER)
                startActivity(intent)
            }
        }
    }


}
