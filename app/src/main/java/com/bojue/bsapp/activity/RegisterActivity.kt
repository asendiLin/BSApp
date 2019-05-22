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

class RegisterActivity : BaseActivity(),View.OnClickListener {

    private lateinit var mEtUsername : EditText
    private lateinit var mEtPassword : EditText
    private lateinit var mBtnToLogin : Button
    private lateinit var mFabRegister : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mEtUsername =findViewById(R.id.et_register_username)
        mEtPassword =findViewById(R.id.et_register_password)
        mBtnToLogin = findViewById(R.id.btn_to_login)
        mFabRegister = findViewById(R.id.fab_register)
        mBtnToLogin.setOnClickListener(this)
        mFabRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_to_login ->{
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.fab_register ->{
                val loadingDialog = LoadingDialog(this)
                loadingDialog.show()
            }
        }
    }
}
