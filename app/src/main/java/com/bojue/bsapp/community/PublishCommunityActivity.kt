package com.bojue.bsapp.community

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.bojue.bsapp.R
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.api.widget.Widget

class PublishCommunityActivity : AppCompatActivity() {

    private val myTag = "PublishCommunityAct"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish_community)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_edit_content,PublishCommunityFragment())
        transaction.commit()
    }
}
