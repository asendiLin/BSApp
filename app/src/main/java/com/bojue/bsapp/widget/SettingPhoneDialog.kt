package com.bojue.bsapp.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.bojue.bsapp.R
import com.bojue.bsapp.util.ToastUtil
import com.bojue.bsapp.util.UserManager

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class SettingPhoneDialog(private val mContext: Context,private val mListener:OnChangePhoneListener)
    : Dialog(mContext), View.OnClickListener {

    private lateinit var mEtPhone: EditText
    private lateinit var mBtnSave: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_phone_settiing)
        setCanceledOnTouchOutside(false)
        mEtPhone = findViewById(R.id.et_dialog_phone)
        mEtPhone.setText(UserManager.getUser().phone)
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