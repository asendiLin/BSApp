package com.bojue.bsapp.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.sendi.base.util.ToastUtil
import com.sendi.myself.R
import com.sendi.user_export.constance.USER_MANAGER
import com.sendi.user_export.manager.IUserManager

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class SettingPhoneDialog(private val mContext: Context,private val mListener:OnChangePhoneListener)
    : Dialog(mContext), View.OnClickListener {

    private lateinit var mEtPhone: EditText
    private lateinit var mBtnSave: Button

    @Autowired(name = USER_MANAGER)
    lateinit var userManager : IUserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_phone_settiing)
        setCanceledOnTouchOutside(false)
        mEtPhone = findViewById(R.id.et_dialog_phone)
        mEtPhone.setText(userManager.getUser().phone)
        mBtnSave = findViewById(R.id.btn_save)
        mBtnSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_save -> {
                val phone = mEtPhone.text.toString()
                if (phone.length != 11){
                    ToastUtil.showShort(context,"手机号码格式有误")
                }else{
                    mListener.onPhoneChange(phone)
                }
                dismiss()
            }
        }
    }

    interface OnChangePhoneListener{
        fun onPhoneChange(phone: String)
    }
}