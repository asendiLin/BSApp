package com.bojue.bsapp.register

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.bojue.bsapp.R
import com.bojue.bsapp.constance.SUCCESS_STATU
import com.bojue.bsapp.ext.getViewModel
import com.bojue.bsapp.login.LoginActivity
import com.bojue.bsapp.model.LoginResponse
import com.bojue.bsapp.model.RegisterResponse
import com.bojue.bsapp.util.SPUtils
import com.bojue.bsapp.widget.LoadingDialog
import com.bojue.core.common.BaseActivity

class RegisterActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mEtUsername: EditText
    private lateinit var mEtPassword: EditText
    private lateinit var mBtnToLogin: Button
    private lateinit var mFabRegister: FloatingActionButton

    private val mRegisterViewModel by lazy {
        getViewModel(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mEtUsername = findViewById(R.id.et_register_username)
        mEtPassword = findViewById(R.id.et_register_password)
        mBtnToLogin = findViewById(R.id.btn_to_login)
        mFabRegister = findViewById(R.id.fab_register)
        mBtnToLogin.setOnClickListener(this)
        mFabRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_to_login -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.fab_register -> {
                val loadingDialog = LoadingDialog(this)
                loadingDialog.show()

                val username = mEtUsername.text.toString()
                val password = mEtPassword.text.toString()
                mRegisterViewModel.register(username, password).observe(this, Observer {result ->
                    loadingDialog.dismiss()
                    result?.let {
                        if (result.status == SUCCESS_STATU){
                            saveUserInfo(result.data)
                        }else{
                            val alertDialog = AlertDialog.Builder(this).setMessage(result.message).create()
                            alertDialog.show()
                        }
                    }

                })
            }
        }
    }


    private fun saveUserInfo(userInfo: RegisterResponse?) {
        userInfo?.let {
            SPUtils.saveInt(this,"id",userInfo.id)
            SPUtils.saveString(this,"username",userInfo.username)
            SPUtils.saveString(this,"password",userInfo.password)
            userInfo.icon?.let {
                SPUtils.saveString(this,"icon",userInfo.icon)
            }
            userInfo.number?.let {
                SPUtils.saveString(this,"icon",userInfo.number)
            }
            userInfo.phone?.let {
                SPUtils.saveString(this,"icon",userInfo.phone)
            }
            userInfo.signature?.let {
                SPUtils.saveString(this,"signature",userInfo.signature)
            }
            userInfo.classname?.let {
                SPUtils.saveString(this,"username",userInfo.classname)
            }
        }
    }
}
