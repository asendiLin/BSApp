package com.bojue.bsapp.community

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.bojue.bsapp.R

class PublishCommunityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish_community)

    }

    fun onGetPhoto(view : View){
        showBottomDialog()
    }


    private fun showBottomDialog(){
        val bottomDialog = Dialog(this,R.style.BottomDialog)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_select_bottom,null,false)
        bottomDialog.setContentView(view)
        val window = bottomDialog.window
        window.setGravity(Gravity.BOTTOM)
        val params = window.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = params
        bottomDialog.show()
    }
}
