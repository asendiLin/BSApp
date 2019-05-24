package com.bojue.bsapp.community

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.bojue.bsapp.R
import com.bojue.bsapp.constance.SUCCESS_STATU
import com.bojue.bsapp.model.CommunityModel
import com.bojue.bsapp.widget.LoadingDialog
import com.bojue.core.common.BaseFragment
import link.fls.swipestack.SwipeStack

/**
 * create data : 2019/5/8
 * author : sendi
 * description :社区Fragment
 */
class CommunityFragment : BaseFragment(),View.OnClickListener {

    private lateinit var mRootView : View
    private lateinit var mSSCommunity : SwipeStack
    private lateinit var mFlbAdd : FloatingActionButton
    private lateinit var mBtnRelaod : Button
    private lateinit var mCommunityViewModel :CommunityViewModel
    private lateinit var mCommunityListAdapter : CommunityAdapter
    private val mCommunityList = ArrayList<CommunityModel>()
    private var mLoadingDialog : LoadingDialog? = null
    private val mRemoveCommunityList = ArrayList<CommunityModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = LayoutInflater.from(context).inflate(R.layout.fragment_community_layout,null,false)
        mSSCommunity = mRootView.findViewById(R.id.ss_community)
        mCommunityListAdapter = CommunityAdapter(requireActivity(),mCommunityList)
        mSSCommunity.adapter = mCommunityListAdapter
        mSSCommunity.setListener(object : SwipeStack.SwipeStackListener{
            override fun onViewSwipedToLeft(position: Int) {
                mRemoveCommunityList.add(mCommunityList[position])
            }

            override fun onViewSwipedToRight(position: Int) {
                mRemoveCommunityList.add(mCommunityList[position])
            }

            override fun onStackEmpty() {
                mBtnRelaod.visibility = View.VISIBLE
            }
        })
        mFlbAdd = mRootView.findViewById(R.id.fab_add_community)
        mBtnRelaod = mRootView.findViewById(R.id.btn_reload)
        mBtnRelaod.setOnClickListener(this)
        mFlbAdd.setOnClickListener(this)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLoadingDialog = LoadingDialog(requireContext())
        mLoadingDialog?.show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mCommunityViewModel = ViewModelProviders.of(this,mViewModelFactory).get(CommunityViewModel::class.java)
        mCommunityViewModel.getCommunityList().observe(this,Observer{result->
            mLoadingDialog?.let { dialog->
                if (dialog.isShowing){
                    dialog.dismiss()
                }
            }
            if (result?.status == SUCCESS_STATU){
                val communityList = result.data
                communityList?.let {
                    mCommunityList.removeAll(mRemoveCommunityList)
                    mRemoveCommunityList.clear()
                    if (communityList.isEmpty()){
                        mBtnRelaod.visibility = View.VISIBLE
                    }else{
                        mBtnRelaod.visibility = View.GONE
                        mCommunityList.addAll(communityList)
                        mCommunityListAdapter.notifyDataSetChanged()
                    }
                }
            }else{
                Toast.makeText(requireContext(),result?.message,Toast.LENGTH_SHORT).show()
                mBtnRelaod.visibility = View.VISIBLE
            }

        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fab_add_community -> {
                val intent = Intent(requireActivity(),PublishCommunityActivity::class.java)
                context?.startActivity(intent)
            }
            R.id.btn_reload -> {
                mLoadingDialog?.show()
                mCommunityViewModel.getCommunityList()
            }
        }
    }
}
