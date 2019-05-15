package com.bojue.bsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bojue.bsapp.community.CommunityFragment
import com.bojue.bsapp.publish.PublishFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_content, PublishFragment(),"Publish")
        transaction.commit()
    }
}
