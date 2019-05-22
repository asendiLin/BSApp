package com.bojue.bsapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.bojue.bsapp.R
import com.bojue.bsapp.widget.LoadingDialog
import com.bojue.core.common.BaseActivity

class LoginActivity : BaseActivity(), View.OnClickListener {
    private lateinit var mEtUsername : EditText
    private lateinit var mEtPassword : EditText
    private lateinit var mBtnToRegister : Button
    private lateinit var mFabLogin : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activity)
        mEtUsername =findViewById(R.id.et_login_username)
        mEtPassword =findViewById(R.id.et_login_password)
        mBtnToRegister = findViewById(R.id.btn_to_register)
        mFabLogin = findViewById(R.id.fab_login)
        mBtnToRegister.setOnClickListener(this)
        mFabLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.btn_to_register ->{
                val intent = Intent(this,RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.fab_login ->{
                val loadingDialog = LoadingDialog(this)
                loadingDialog.show()
            }
        }

    }
}
