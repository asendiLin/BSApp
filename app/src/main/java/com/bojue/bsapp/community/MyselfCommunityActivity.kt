package com.bojue.bsapp.community

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.bojue.bsapp.R
import com.bojue.bsapp.ext.getViewModel
import com.bojue.bsapp.model.CommunityModel
import com.bojue.core.common.BaseActivity

class MyselfCommunityActivity : BaseActivity() {

    private val myTag = "MyselfCommunityActivity"
    private lateinit var mRvCommunityList : RecyclerView
    private lateinit var mCommunityListAdapter : MySelfCommunityAdapter
    private val mCommunityList = ArrayList<CommunityModel>()

    private val mCommunityViewModel  by lazy {
        getViewModel(MyselfCommunityViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_self_community)
        initView()
        initData()
    }

    private fun initData() {
        mCommunityViewModel.getCommunityList().observe(this, Observer {result ->
            Log.i(myTag,"result - > $result")
        })
    }

    private fun initView() {
        mRvCommunityList = findViewById(R.id.rv_self_community_list)
        mRvCommunityList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mCommunityListAdapter = MySelfCommunityAdapter(mCommunityList,this)
        mRvCommunityList.adapter = mCommunityListAdapter
    }
}
