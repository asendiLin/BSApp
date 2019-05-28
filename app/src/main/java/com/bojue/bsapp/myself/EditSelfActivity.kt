package com.bojue.bsapp.myself

import android.arch.lifecycle.Observer
import android.graphics.drawable.RippleDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.bojue.bsapp.R
import com.bojue.bsapp.constance.SUCCESS_STATU
import com.bojue.bsapp.ext.getViewModel
import com.bojue.bsapp.util.UserManager
import com.bojue.bsapp.widget.LoadingDialog
import com.bojue.core.common.BaseActivity
import com.bumptech.glide.Glide

class EditSelfActivity : BaseActivity() , View.OnClickListener{
    private val myTag = "EditSelfActivity"

    private lateinit var mBtnComplete : Button
    private lateinit var mTvBack :TextView
    private lateinit var mEtNickname : EditText
    private lateinit var mEtSignature : EditText
    private lateinit var mIvUserIcon : ImageView
    private  var mLoadingDialog : LoadingDialog? = null
    private val mEditInfoViewModel by lazy {
        getViewModel(EditInfoViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_self)

        initView()

        mEditInfoViewModel.editInfoLiveData.observe(this, Observer{result ->
            mLoadingDialog?.dismiss()

            Log.i(myTag,"user = ${result?.data}")

            result?.let {
                if (result.status == SUCCESS_STATU){
                    result.data?.let {
                        UserManager.saveUser(it)
                    }
                    Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,result.message,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initView() {
        mBtnComplete = findViewById(R.id.btn_complete)
        mTvBack = findViewById(R.id.tv_nav_title)
        mEtNickname = findViewById(R.id.et_nickname)
        mEtSignature = findViewById(R.id.et_signature)
        mIvUserIcon = findViewById(R.id.iv_user_icon)

        mBtnComplete.setOnClickListener(this)
        mTvBack.setOnClickListener(this)
        val user = UserManager.getUser()
        mEtNickname.setText(user.nickname?:"")
        mEtSignature.setText(user.signature?:"")
        Glide.with(this).load(user.icon).into(mIvUserIcon)
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
