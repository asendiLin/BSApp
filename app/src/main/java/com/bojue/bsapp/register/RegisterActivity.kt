package com.bojue.bsapp.register

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.bojue.bsapp.R
import com.bojue.bsapp.activity.HomeActivity
import com.sendi.base.constance.SUCCESS_STATU
import com.bojue.core.ext.getViewModel
import com.bojue.bsapp.login.LoginActivity
import com.bojue.bsapp.model.RegisterResponse
import com.sendi.user.UserModel
import com.sendi.base.util.ToastUtil
import com.bojue.bsapp.util.UserManager
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
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
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
                finish()
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
                            ToastUtil.showShort(this,"注册成功")
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            ToastUtil.showShort(this,result.message)
                        }
                    }

                })
            }
        }
    }


    private fun saveUserInfo(userInfo: RegisterResponse?) {
        userInfo?.let {
            val userModel = com.sendi.user.UserModel(userInfo.id, userInfo.username, userInfo.password,
                    userInfo.number, userInfo.classname, userInfo.icon, userInfo.nickname,
                    userInfo.phone, userInfo.signature)
            UserManager.saveUser(userModel)
        }
    }
}
