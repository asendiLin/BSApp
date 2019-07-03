package com.sendi.community.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sendi.community.R
import com.sendi.community.fragment.CommunityFragment

class CommunityLauncherActivity : AppCompatActivity() {

    private val fragment = CommunityFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_launcher)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_community_content,fragment).commit()
    }
}
