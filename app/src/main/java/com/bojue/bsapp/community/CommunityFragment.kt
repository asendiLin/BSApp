package com.bojue.bsapp.community

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bojue.bsapp.R
import com.bojue.core.common.BaseFragment
import link.fls.swipestack.SwipeStack
import javax.inject.Inject

/**
 * create data : 2019/5/8
 * author : sendi
 * description :社区Fragment
 */
class CommunityFragment : BaseFragment() {

    private lateinit var mRootView : View
    private lateinit var mSSCommunity : SwipeStack

    @Inject
    lateinit var mCommunityViewModel :CommunityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = LayoutInflater.from(context).inflate(R.layout.fragment_community_layout,null,false)
        mSSCommunity = mRootView.findViewById(R.id.ss_community)
        mSSCommunity.adapter = ComunityAdapter(context,ArrayList())

        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCommunityViewModel.getCommunityList().observe(this,Observer{list->
            //TODO刷新数据
        })
    }

}