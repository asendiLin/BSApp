package com.bojue.bsapp.community

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import com.bojue.bsapp.R
import com.sendi.base.constance.COMMUNITY_LIST
import com.sendi.base.constance.SUCCESS_STATU
import com.bojue.core.ext.getViewModel
import com.bojue.bsapp.model.CommunityModel
import com.bojue.bsapp.util.ToastUtil
import com.bojue.bsapp.widget.LoadingDialog
import com.bojue.core.common.BaseActivity

class MyselfCommunityActivity : BaseActivity() {

    private val myTag = "MyselfCommunityActivity"
    private lateinit var mRvCommunityList: RecyclerView
    private lateinit var mCommunityListAdapter: MySelfCommunityAdapter
    private val mCommunityList = ArrayList<CommunityModel>()
    private var mDeletePosition = 0
    private val mLoadingDialog by lazy {
        LoadingDialog(this)
    }
    private val mCommunityViewModel by lazy {
        getViewModel(MyselfCommunityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_self_community)
        initView()
        initData()
    }

    private fun initData() {
        val communityList = intent.getParcelableArrayListExtra<CommunityModel>(COMMUNITY_LIST)
        Log.i(myTag, "initData -> $communityList")
        communityList?.let {
            mCommunityList.addAll(communityList)
            mCommunityListAdapter.notifyDataSetChanged()
        }

        mCommunityViewModel.deleteCommunityLiveData.observe(this, Observer { result ->
            if (mLoadingDialog.isShowing) {
                mLoadingDialog.dismiss()
            }
            if (result?.status == SUCCESS_STATU) {
                mCommunityList.removeAt(mDeletePosition)
                mCommunityListAdapter.notifyDataSetChanged()
            } else {
                ToastUtil.showShort(this, result?.message)
            }
        })

//        val username = UserManager.getUser().username
//        val username = "13420117889"
//        mCommunityViewModel.getCommunityList(username).observe(this, Observer {result ->
//            Log.i(myTag,"result - > $result")
//            if (result?.status == SUCCESS_STATU){
//                result.data?.let { data ->
//                    mCommunityList.addAll(data)
//                }
//            }else{
//                Toast.makeText(this,"获取动态列表失败",Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    private fun initView() {
        mRvCommunityList = findViewById(R.id.rv_self_community_list)
        mRvCommunityList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mCommunityListAdapter = MySelfCommunityAdapter(mCommunityList, this)
        mCommunityListAdapter.setOnDeleteCallback(object : MySelfCommunityAdapter.OnDeleteCommunityCallback {
            override fun onDelete(position: Int) {
                mLoadingDialog.show()
                mCommunityViewModel.deleteCommunity(mCommunityList[position].id)
                mDeletePosition = position
            }
        })
        mRvCommunityList.adapter = mCommunityListAdapter
        val tvTitle = findViewById<TextView>(R.id.tv_title_content)
        tvTitle.text = "我的动态"

    }
}
