package com.sendi.community.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.sendi.base.constance.SUCCESS_STATU
import com.bojue.core.ext.getViewModel
import com.sendi.base.util.ToastUtil
import com.bojue.core.common.BaseFragment
import com.sendi.base.widget.LoadingDialog
import com.sendi.community.R
import com.sendi.community.activity.PublishCommunityActivity
import com.sendi.community.adapter.CommunityAdapter
import com.sendi.community.inject.CommunityInjector
import com.sendi.community.viewmodel.CommunityViewModel
import com.sendi.community_export.model.CommunityModel
import com.sendi.user_export.constance.USER_MANAGER
import com.sendi.user_export.manager.IUserManager
import link.fls.swipestack.SwipeStack

/**
 * create data : 2019/5/8
 * author : sendi
 * description :社区Fragment
 */
class CommunityFragment : BaseFragment(),View.OnClickListener {

    private val myTag = "CommunityFragment"
    private val COMMENT_DIALOG_TAG= "comment_dialog"
    private lateinit var mRootView : View
    private lateinit var mSSCommunity : SwipeStack
    private lateinit var mFlbAdd : FloatingActionButton
    private lateinit var mBtnRelaod : Button
    private val mCommunityViewModel by lazy {
        getViewModel(CommunityViewModel::class.java)
    }
    private lateinit var mCommunityListAdapter : CommunityAdapter
    private val mCommunityList = ArrayList<CommunityModel>()
    private var mLoadingDialog : LoadingDialog? = null

    private val mCommentDialog = ListBottomSheetDialogFragment()

    @Autowired(name = USER_MANAGER)
    lateinit var userManager : IUserManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = LayoutInflater.from(context).inflate(R.layout.fragment_community_layout,null,false)
        mSSCommunity = mRootView.findViewById(R.id.ss_community)
        mCommunityListAdapter = CommunityAdapter(requireActivity(),mCommunityList)
        mSSCommunity.adapter = mCommunityListAdapter
        mSSCommunity.setListener(object : SwipeStack.SwipeStackListener{
            override fun onViewSwipedToLeft(position: Int) {
                Log.i(myTag,"position -> $position")
//                mRemoveCommunityList.add(mCommunityList[position])
            }

            override fun onViewSwipedToRight(position: Int) {
                Log.i(myTag,"position -> $position")
//                mRemoveCommunityList.add(mCommunityList[position])
            }

            override fun onStackEmpty() {
                Log.i(myTag,"position ->onStackEmpty")
                mBtnRelaod.visibility = View.VISIBLE
            }
        })

        mCommentDialog.setClickSendCommentCallback { position, content ->
            mLoadingDialog?.show()
            Log.i(myTag,"publish  -> ${mCommunityList[position].id}")
            mCommunityViewModel.publishComment(content,userManager.getUser().id,mCommunityList[position].id)
        }

        mCommunityListAdapter.setOnClickListener(object : CommunityAdapter.OnClickItemListener{
            override fun like(position: Int) {
                Log.i(myTag,"like -> $position")
                mCommunityViewModel.postZan(mCommunityList[position].id,userManager.getUser().id)
            }

            override fun share(position: Int) {
            }

            override fun comment(position: Int) {
                Log.i(myTag,"comment  id -> ${mCommunityList[position].id}")
                mCommunityViewModel.getCommentList(mCommunityList[position].id)
                mCommentDialog.show(requireFragmentManager(),COMMENT_DIALOG_TAG,position)
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
        mCommunityViewModel.getCommunityList(1/*userManager.getUser().id*/).observe(this,Observer{result->
            mLoadingDialog?.let { dialog->
                if (dialog.isShowing){
                    dialog.dismiss()
                }
            }
            if (result?.status == SUCCESS_STATU){
                val communityList = result.data
                communityList?.let {
//                    mCommunityList.removeAll(mRemoveCommunityList)
//                    mRemoveCommunityList.clear()
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
        mCommunityViewModel.zanLiveData.observe(this, Observer {result ->
        })
        mCommunityViewModel.commentLiveData.observe(this, Observer { result ->
            if (mLoadingDialog?.isShowing == true){
                mLoadingDialog?.dismiss()
            }
            if (result?.status == SUCCESS_STATU){
                mCommentDialog.refreshCommentList(result.data)
            }else{
                ToastUtil.showShort(requireContext(),result?.message)
            }
        })

        mCommunityViewModel.publishCommentLiveData.observe(this, Observer {result->
            if (mLoadingDialog?.isShowing == true){
                mLoadingDialog?.dismiss()
            }
            if (result?.status == SUCCESS_STATU){
                ToastUtil.showShort(requireContext(),"评论成功")
                mCommentDialog.refreshCommentList(result.data)
//                if ( mCommentDialog.dialog.isShowing){
//                    mCommentDialog.dismiss()
//                }
            }else{
                ToastUtil.showShort(requireContext(),"评论失败")
            }
        })

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fab_add_community -> {
                val intent = Intent(requireActivity(), PublishCommunityActivity::class.java)
                context?.startActivity(intent)
            }
            R.id.btn_reload -> {
                mLoadingDialog?.show()
                mCommunityViewModel.getCommunityList(1/*userManager.getUser().id*/)
            }
        }
    }

    override fun viewModelFactory(): ViewModelProvider.Factory {
        return CommunityInjector.viewModelFactory()
    }
}
