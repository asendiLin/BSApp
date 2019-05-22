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
import android.widget.LinearLayout
import android.widget.TextView
import com.bojue.bsapp.course.CourseActivity
import com.bojue.bsapp.setting.SettingActivity


/**
 * author: asendi.
 * data: 2019/5/10.
 * description:
 */
class MyselfFragment :BaseFragment() ,View.OnClickListener{
    private lateinit var mRootView : View

    private lateinit var mTbTop : Toolbar
    private lateinit var mAblTop : AppBarLayout
    private lateinit var mCtlTop : CollapsingToolbarLayout
    private lateinit var mLLCourse : LinearLayout
    private lateinit var mTvEditInfo : TextView
    private lateinit var mIbSetting : ImageButton
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = LayoutInflater.from(context).inflate(R.layout.fragment_myself_layout,null,false)
        mTbTop = mRootView.findViewById(R.id.tb_top)
        mAblTop = mRootView.findViewById(R.id.abl_self_top)
        mCtlTop = mRootView.findViewById(R.id.ctbl_self_top)
        mLLCourse = mRootView.findViewById(R.id.ll_course)
        mTvEditInfo = mRootView.findViewById(R.id.tv_edit_info)
        mIbSetting = mRootView.findViewById(R.id.ib_setting)
        mIbSetting.setOnClickListener(this)
        mTvEditInfo.setOnClickListener(this)
        mLLCourse.setOnClickListener(this)
        (activity as AppCompatActivity).setSupportActionBar(mTbTop)
        mCtlTop.title = "sendi"
//        mAblTop.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
//            if (verticalOffset <= -mLlTop.height / 2) {
//                mCtlTop.title = showName
//            } else {
//                mCtlTop.title = ""
//            }
//        })
        return mRootView
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ll_course ->{
                val intent = Intent(requireContext(), CourseActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_edit_info -> {
                val intent = Intent(requireContext(), EditSelfActivity::class.java)
                startActivity(intent)
            }
            R.id.ib_setting ->{
                val intent = Intent(requireContext(), SettingActivity::class.java)
                startActivity(intent)
            }
        }
    }


}
