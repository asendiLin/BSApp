package com.bojue.bsapp.order

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.widget.ImageView
import android.widget.TextView
import com.bojue.bsapp.R

class OrderHistoryDetailActivity : AppCompatActivity() {

    private lateinit var mIvUserIcon :ImageView
    private lateinit var mTvNickname :TextView
    private lateinit var mTvPhone :TextView
    private lateinit var mTvEndTime :TextView
    private lateinit var mTvContent :TextView
    private lateinit var mTvPrice :TextView
    private lateinit var mCvAccept : CardView
    private lateinit var mIvAcceptUserIcon :ImageView
    private lateinit var mTvAcceptNickname :TextView
    private lateinit var mTvAcceptPhone :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_order_detail)
    }
}
