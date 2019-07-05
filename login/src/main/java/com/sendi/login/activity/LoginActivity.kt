package com.sendi.login.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.sendi.base.constance.SUCCESS_STATU
import com.bojue.core.ext.getViewModel
import com.sendi.login.model.LoginResponse
import com.sendi.base.util.ToastUtil
import com.bojue.core.common.BaseActivity
import com.sendi.base.constance.HOME_ACTIVITY
import com.sendi.base.widget.LoadingDialog
import com.sendi.login.R
import com.sendi.login.inject.LoginInjector
import com.sendi.login.viewmodel.LoginViewModel
import com.sendi.user_export.constance.USER_MANAGER
import com.sendi.user_export.manager.IUserManager
import com.sendi.user_export.model.UserModel

@Route(path = "/login/login_activity")
class LoginActivity : BaseActivity(), View.OnClickListener {
    private lateinit var mEtUsername: EditText
    private lateinit var mEtPassword: EditText
    private lateinit var mBtnToRegister: Button
    private lateinit var mFabLogin: FloatingActionButton

    private val mLoginViewModel  by lazy { getViewModel(LoginViewModel::class.java) }

    @Autowired(name = USER_MANAGER)
    lateinit var userManager : IUserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activity)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
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
                finish()
            }
            R.id.fab_login -> {
                val dialog = LoadingDialog(this)
                dialog.show()
                val username = mEtUsername.text.toString()
                val password = mEtPassword.text.toString()
                mLoginViewModel.login(username,password).observe(this, Observer{result->
                    dialog.dismiss()

                    ARouter.getInstance().build(HOME_ACTIVITY).navigation(this)
                    finish()

                    result?.let {
                        if (result.status == SUCCESS_STATU){
                            saveUserInfo(result.data)
                            ARouter.getInstance().build(HOME_ACTIVITY).navigation(this)
                            finish()
                        }else{
                            ToastUtil.showShort(this,result.message)
                        }
                    }
                })
            }
        }

    }

    private fun saveUserInfo(userInfo: LoginResponse?) {
        userInfo?.let {
            val userModel = UserModel(userInfo.id, userInfo.username, userInfo.password,
                    userInfo.number, userInfo.classname, userInfo.icon, userInfo.nickname, userInfo.phone, userInfo.signature)
            userManager.saveUser(userModel)
        }
    }

    override fun requireViewModelFactory(): ViewModelProvider.Factory {
       return LoginInjector.viewModelFactory()
    }
}
