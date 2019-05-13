package com.bojue.bsapp.myself

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import com.bojue.bsapp.R
import com.bojue.core.common.BaseFragment
import android.support.v7.app.AppCompatActivity
import android.view.*


/**
 * author: asendi.
 * data: 2019/5/10.
 * description:
 */
class MyselfFragment :BaseFragment() {

    private lateinit var mRootView : View
    private lateinit var mTbTop : Toolbar
    private lateinit var mAblTop : AppBarLayout
    private lateinit var mCtlTop : CollapsingToolbarLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = LayoutInflater.from(context).inflate(R.layout.fragment_myself_layout,null,false)
        mTbTop = mRootView.findViewById(R.id.tb_top)
        mAblTop = mRootView.findViewById(R.id.abl_self_top)
        mCtlTop = mRootView.findViewById(R.id.ctbl_self_top)
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


}