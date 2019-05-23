package com.bojue.bsapp.myself

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.bojue.bsapp.R
import com.bojue.bsapp.ext.getViewModel
import com.bojue.core.common.BaseActivity

class EditSelfActivity : BaseActivity() , View.OnClickListener{
    private val myTag = "EditSelfActivity"

    private lateinit var mBtnComplete : Button
    private lateinit var mTvBack :TextView
    private val mEditInfoViewModel by lazy {
        getViewModel(EditInfoViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_self)
        mBtnComplete = findViewById(R.id.btn_complete)
        mTvBack = findViewById(R.id.tv_nav_title)
        mTvBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.tv_nav_title ->{
                finish()
            }
            R.id.btn_complete ->{

                mEditInfoViewModel.editInfo()
            }

        }
    }
}
