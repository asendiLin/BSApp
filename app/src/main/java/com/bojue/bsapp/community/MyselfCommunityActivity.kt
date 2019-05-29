package com.bojue.bsapp.community

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.bojue.bsapp.R
import com.bojue.bsapp.constance.SUCCESS_STATU
import com.bojue.bsapp.ext.getViewModel
import com.bojue.bsapp.model.CommunityModel
import com.bojue.bsapp.util.UserManager
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
//        val username = UserManager.getUser().username
        val username = "13420117889"
        mCommunityViewModel.getCommunityList(username).observe(this, Observer {result ->
            Log.i(myTag,"result - > $result")
            if (result?.status == SUCCESS_STATU){
                result.data?.let { data ->
                    mCommunityList.addAll(data)
                }
            }else{
                Toast.makeText(this,"获取动态列表失败",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initView() {
        mRvCommunityList = findViewById(R.id.rv_self_community_list)
        mRvCommunityList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mCommunityListAdapter = MySelfCommunityAdapter(mCommunityList,this)
        mRvCommunityList.adapter = mCommunityListAdapter
        val tvTitle = findViewById<TextView>(R.id.tv_title_content)
        tvTitle.text = "我的动态"

    }
}
