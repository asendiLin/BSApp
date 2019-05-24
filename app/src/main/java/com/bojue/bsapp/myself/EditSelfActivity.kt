package com.bojue.bsapp.myself

import android.arch.lifecycle.Observer
import android.graphics.drawable.RippleDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bojue.bsapp.R
import com.bojue.bsapp.constance.SUCCESS_STATU
import com.bojue.bsapp.ext.getViewModel
import com.bojue.bsapp.util.UserManager
import com.bojue.bsapp.widget.LoadingDialog
import com.bojue.core.common.BaseActivity

class EditSelfActivity : BaseActivity() , View.OnClickListener{
    private val myTag = "EditSelfActivity"

    private lateinit var mBtnComplete : Button
    private lateinit var mTvBack :TextView
    private lateinit var mEtNickname : EditText
    private lateinit var mEtSignature : EditText
    private  var mLoadingDialog : LoadingDialog? = null
    private val mEditInfoViewModel by lazy {
        getViewModel(EditInfoViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_self)
        mBtnComplete = findViewById(R.id.btn_complete)
        mTvBack = findViewById(R.id.tv_nav_title)
        mEtNickname = findViewById(R.id.et_nickname)
        mEtSignature = findViewById(R.id.et_signature)

        mTvBack.setOnClickListener(this)

        mEditInfoViewModel.editInfoLiveData.observe(this, Observer{result ->
            //TODO:保存用户信息
            mLoadingDialog?.dismiss()

            Log.i(myTag,"user = ${result?.data}")

            result?.let {
                if (result.status == SUCCESS_STATU){
                    result.data?.let {
                        UserManager.saveUser(it)
                    }
                }else{
                    Toast.makeText(this,result.message,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.tv_nav_title ->{
                finish()
            }
            R.id.btn_complete ->{
                if (mLoadingDialog == null){
                    mLoadingDialog = LoadingDialog(this)
                    mLoadingDialog?.show()
                }
                val nickname = mEtNickname.text.toString()
                val signature = mEtSignature.text.toString()
                val user =UserManager.getUser()
                user.nickname = nickname
                user.signature = signature
                mEditInfoViewModel.editInfo(user.stuId,user.username,user.password,
                        user.number,user.classname,user.icon,user.nickname,user.phone,user.signature)
            }

        }
    }
}
