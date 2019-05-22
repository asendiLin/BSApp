package com.bojue.bsapp.setting

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.TextView
import com.bojue.bsapp.R
import com.bojue.bsapp.constance.SUCCESS_STATU
import com.bojue.bsapp.ext.getViewModel
import com.bojue.bsapp.util.SPUtils
import com.bojue.bsapp.widget.IdentifyDialog
import com.bojue.bsapp.widget.LoadingDialog
import com.bojue.core.common.BaseActivity

class SettingActivity :BaseActivity(),View.OnClickListener {

    private lateinit var mTvBack : TextView
    private lateinit var mTvIdentifyStudent :TextView

    private val mSettingViewModel by lazy {
        getViewModel(SettingViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        mTvBack = findViewById(R.id.tv_nav_title)
        mTvIdentifyStudent =findViewById(R.id.tv_identify_student)
        mTvBack.setOnClickListener(this)
        mTvIdentifyStudent.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_nav_title ->{
                finish()
            }
            R.id.tv_identify_student->{
                val identifyDialog = IdentifyDialog(this,object : IdentifyDialog.OnIdentifyClickListener{
                    override fun onIdentify( number: String, password: String) {
                        val loadingDialog = LoadingDialog(this@SettingActivity)
                        loadingDialog.show()
                        val username = SPUtils.getString(this@SettingActivity, "username", "00")
                        username?.let {
                            mSettingViewModel
                                    .identify(username, number, password)
                                    .observe(this@SettingActivity, Observer {result ->
                                        loadingDialog.dismiss()
                                        result?.let {
                                            if (result.status == SUCCESS_STATU){
                                                mTvIdentifyStudent.text = number
                                                SPUtils.saveString(this@SettingActivity,"number",number)
                                            }else{
                                                val alertDialog = AlertDialog.Builder(this@SettingActivity).setMessage(result.message).create()
                                                alertDialog.show()
                                            }
                                        }
                                    })
                        }
                    }
                })
                identifyDialog.show()
            }
        }
    }
}
