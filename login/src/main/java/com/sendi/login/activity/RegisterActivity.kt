package com.sendi.login.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.alibaba.android.arouter.facade.annotation.Autowired

import com.sendi.base.constance.SUCCESS_STATU
import com.bojue.core.ext.getViewModel
import com.sendi.base.util.ToastUtil
import com.bojue.core.common.BaseActivity
import com.sendi.base.widget.LoadingDialog
import com.sendi.login.R
import com.sendi.login.model.RegisterResponse
import com.sendi.user_export.constance.USER_MANAGER
import com.sendi.user_export.manager.IUserManager
import com.sendi.user_export.model.UserModel

class RegisterActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mEtUsername: EditText
    private lateinit var mEtPassword: EditText
    private lateinit var mBtnToLogin: Button
    private lateinit var mFabRegister: FloatingActionButton

    @Autowired(name = USER_MANAGER)
    lateinit var userManager : IUserManager

    private val mRegisterViewModel by lazy {
        getViewModel(com.sendi.login.viewmodel.RegisterViewModel::class.java)
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
//                          todo:  val intent = Intent(this, HomeActivity::class.java)
//                            startActivity(intent)
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
            val userModel = UserModel(userInfo.id, userInfo.username, userInfo.password,
                    userInfo.number, userInfo.classname, userInfo.icon, userInfo.nickname,
                    userInfo.phone, userInfo.signature)
            userManager.saveUser(userModel)
        }
    }
}
