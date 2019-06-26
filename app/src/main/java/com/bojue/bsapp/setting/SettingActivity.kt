package com.bojue.bsapp.setting

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bojue.bsapp.R
import com.bojue.bsapp.constance.BASE_URL
import com.bojue.bsapp.constance.BASE_URL_KEY
import com.bojue.bsapp.constance.FAIL_STATU
import com.bojue.bsapp.event.SettingEvent
import com.bojue.core.ext.getViewModel
import com.bojue.bsapp.login.LoginActivity
import com.bojue.bsapp.myself.EditInfoViewModel
import com.bojue.bsapp.util.SPUtils
import com.bojue.bsapp.util.ToastUtil
import com.bojue.bsapp.util.UserManager
import com.bojue.bsapp.widget.IdentifyDialog
import com.bojue.bsapp.widget.LoadingDialog
import com.bojue.bsapp.widget.SettingPhoneDialog
import com.bojue.bsapp.widget.SettingUrlDialog
import com.bojue.core.common.BaseActivity
import com.bojue.core.event.EventUtil
import com.bojue.core.util.ActivityManger

class SettingActivity : BaseActivity(), View.OnClickListener {

    private val myTag = "SettingActivity"

    private lateinit var mTvBack: TextView
    private lateinit var mTvIdentifyStudent: TextView
    private lateinit var mTvPhoneNumber: TextView
    private lateinit var mTvClearCache: TextView
    private lateinit var mTvSettingUrl: TextView
    private lateinit var mBtnLogout: Button
    private lateinit var mSettingPhoneDialog :SettingPhoneDialog
    private val mLoadingDialog by lazy {
        LoadingDialog(this)
    }

    private val mSettingViewModel by lazy {
        getViewModel(SettingViewModel::class.java)
    }
    private val mEditInfoViewModel by lazy {
        getViewModel(EditInfoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        mTvBack = findViewById(R.id.tv_nav_title)
        mTvIdentifyStudent = findViewById(R.id.tv_identify_student)
        mTvSettingUrl = findViewById(R.id.tv_setting_url)
        mTvPhoneNumber = findViewById(R.id.tv_phone_number)
        mTvClearCache = findViewById(R.id.tv_clear_cache)
        mBtnLogout = findViewById(R.id.btn_logout)
        val user = UserManager.getUser()
        Log.i(myTag, "user -> $user")
        mTvIdentifyStudent.text = getString(R.string.student_identify, "${user.number}")
        mTvPhoneNumber.text = getString(R.string.phone_number, "${user.phone}")
        mTvClearCache.text = getString(R.string.clear_cache, "16.6")
        mTvSettingUrl.text = SPUtils.getString(this, BASE_URL_KEY, BASE_URL)
        mTvSettingUrl.setOnClickListener(this)
        mTvBack.setOnClickListener(this)
        mTvIdentifyStudent.setOnClickListener(this)
        mBtnLogout.setOnClickListener(this)
        mTvPhoneNumber.setOnClickListener(this)

        mEditInfoViewModel.editInfoLiveData.observe(this, Observer { result ->
            Log.i(myTag, "user -> ${result?.data}")
            if (mLoadingDialog.isShowing) {
                mLoadingDialog.dismiss()
            }
            if (result?.status != FAIL_STATU) {
                mSettingPhoneDialog.dismiss()
                val newUser = UserManager.getUser()
                newUser.phone = result?.data?.phone
                UserManager.saveUser(user)
                ToastUtil.showShort(this, "修改成功")
                mTvPhoneNumber.text = getString(R.string.phone_number, "${result?.data?.phone}")
            } else {
                ToastUtil.showShort(this, "修改失败")
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_nav_title -> {
                finish()
            }
            R.id.tv_identify_student -> {
                val identifyDialog = IdentifyDialog(this, object : IdentifyDialog.OnIdentifyClickListener {
                    override fun onIdentify(number: String, password: String) {

                        val loadingDialog = LoadingDialog(this@SettingActivity)
                        loadingDialog.show()
                        val user = UserManager.getUser()
                        val username = user.username

                        Log.i(myTag, "username = $username number $number password $password")

//                        val delayTask = object : TimerTask() {
//                            override fun run() {
//                                launch(UI){
//                                    loadingDialog.dismiss()
//                                    Toast.makeText(this@SettingActivity, "验证成功", Toast.LENGTH_SHORT).show()
//                                    EventUtil.post(SettingEvent(SettingEvent.COURSE))
//                                    mTvIdentifyStudent.text = number
//                                    user.number = number
//                                    UserManager.saveUser(user)
//                                }
//
//                            }
//                        }
//                        val timer = Timer()
//                        timer.schedule(delayTask, 2000)

                            mSettingViewModel
                                    .identify(username, number, password)
                                    .observe(this@SettingActivity, Observer { result ->
                                        loadingDialog.dismiss()
                                        result?.let {
                                            if (result.status != FAIL_STATU) {
                                                mTvIdentifyStudent.text = number
                                                user.number = number
                                                UserManager.saveUser(user)
                                                EventUtil.post(SettingEvent(SettingEvent.COURSE))
                                                Toast.makeText(this@SettingActivity, "验证成功", Toast.LENGTH_SHORT).show()
                                            } else {
                                                Toast.makeText(this@SettingActivity, result.message, Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    })
                    }
                })
                identifyDialog.show()
            }

            R.id.tv_setting_url -> {
                val urlDialog = SettingUrlDialog(this)
                urlDialog.show()
            }

            R.id.tv_phone_number -> {
                mSettingPhoneDialog = SettingPhoneDialog(this, object : SettingPhoneDialog.OnChangePhoneListener {
                    override fun onPhoneChange(phone: String) {
                        mLoadingDialog.show()
                        val user = UserManager.getUser()
                        mEditInfoViewModel.editInfo(user.id, user.username, user.password, user.number,
                                user.classname, user.icon, user.nickname, phone, user.signature)
                    }
                })
                mSettingPhoneDialog.show()
            }

            R.id.btn_logout -> {
                ActivityManger.logout()
                UserManager.clearUser()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

        }
    }
}
