package com.bojue.bsapp.order

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bojue.bsapp.R
import com.bojue.core.common.BaseActivity

class OrderDetailActivity : BaseActivity() {

    private lateinit var mToolbar : Toolbar
    private lateinit var mBtnOrderAccept : Button
    private lateinit var mTvTitle : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        mToolbar=findViewById(R.id.tb_title)
        mBtnOrderAccept = findViewById(R.id.btn_order_accept)
        mTvTitle = findViewById(R.id.tv_title_content)
        mToolbar.navigationIcon =resources.getDrawable(R.mipmap.ic_title_back)
        mToolbar.setNavigationOnClickListener { finish() }
        mBtnOrderAccept.setOnClickListener {
            acceptOrder()
        }
        mTvTitle.text = "订单详情"
    }

    private fun acceptOrder() {
        Toast.makeText(this,"接单",Toast.LENGTH_LONG).show()
    }
}
