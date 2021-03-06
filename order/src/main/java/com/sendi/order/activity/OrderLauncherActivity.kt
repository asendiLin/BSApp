package com.sendi.order.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.order.R
import com.sendi.order.fragment.PublishOrderFragment

class OrderLauncherActivity : AppCompatActivity() {

    private val lunchFragment = PublishOrderFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_activity_order_launcher)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_order_content,lunchFragment)
        transaction.commit()

    }
}
