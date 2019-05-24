package com.bojue.bsapp.login

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.bojue.bsapp.R
import com.bojue.bsapp.activity.HomeActivity
import com.bojue.bsapp.constance.SUCCESS_STATU
import com.bojue.bsapp.ext.getViewModel
import com.bojue.bsapp.model.LoginResponse
import com.bojue.bsapp.model.UserModel
import com.bojue.bsapp.register.RegisterActivity
import com.bojue.bsapp.util.SPUtils
import com.bojue.bsapp.util.UserManager
import com.bojue.bsapp.widget.LoadingDialog
import com.bojue.core.common.BaseActivity

class LoginActivity : BaseActivity(), View.OnClickListener {
    private lateinit var mEtUsername: EditText
    private lateinit var mEtPassword: EditText
    private lateinit var mBtnToRegister: Button
    private lateinit var mFabLogin: FloatingActionButton

    private val mLoginViewModel  by lazy { getViewModel(LoginViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activity)
        mEtUsername = findViewById(R.id.et_login_username)
        mEtPassword = findViewById(R.id.et_login_password)
        mBtnToRegister = findViewById(R.id.btn_to_register)
        mFabLogin = findViewById(R.id.fab_login)
        mBtnToRegister.setOnClickListener(this)
        mFabLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.btn_to_register -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.fab_login -> {
                val dialog = LoadingDialog(this)
                dialog.show()
                val username = mEtUsername.text.toString()
                val password = mEtPassword.text.toString()
                mLoginViewModel.login(username,password).observe(this, Observer{result->
                    dialog.dismiss()
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

    private fun saveUserInfo(userInfo: LoginResponse?) {
        userInfo?.let {
            val userModel = UserModel(userInfo.id,userInfo.username,userInfo.password,
                    userInfo.number,userInfo.classname,userInfo.icon,userInfo.nickname,userInfo.phone,userInfo.signature)
            UserManager.saveUser(userModel)
        }
    }
}
