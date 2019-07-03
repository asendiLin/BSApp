package com.sendi.myself.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sendi.myself.R
import com.sendi.myself.fragment.MyselfFragment

class MyselfLauncherActivity : AppCompatActivity() {

    private val fragment = MyselfFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myself_launcher)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_myself_content,fragment).commit()
    }
}
