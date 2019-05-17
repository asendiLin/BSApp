package com.bojue.bsapp.community

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bojue.bsapp.R
import com.bojue.bsapp.inject.Injector
import com.bojue.core.common.BaseFragment
import link.fls.swipestack.SwipeStack
import javax.inject.Inject

/**
 * create data : 2019/5/8
 * author : sendi
 * description :社区Fragment
 */
class CommunityFragment : BaseFragment(),View.OnClickListener {
    private lateinit var mRootView : View

    private lateinit var mSSCommunity : SwipeStack
    private lateinit var mFlbAdd : FloatingActionButton
    private lateinit var mCommunityViewModel :CommunityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = LayoutInflater.from(context).inflate(R.layout.fragment_community_layout,null,false)
        mSSCommunity = mRootView.findViewById(R.id.ss_community)
        mSSCommunity.adapter = ComunityAdapter(requireActivity(),ArrayList())
        mFlbAdd = mRootView.findViewById(R.id.fab_add_community)
        mFlbAdd.setOnClickListener(this)
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mCommunityViewModel = ViewModelProviders.of(this,mViewModelFactory).get(CommunityViewModel::class.java)
        mCommunityViewModel.getCommunityList().observe(this,Observer{list->
            //TODO刷新数据
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fab_add_community -> {
                val intent = Intent(requireActivity(),PublishCommunityActivity::class.java)
                context?.startActivity(intent)
            }
        }
    }
}
