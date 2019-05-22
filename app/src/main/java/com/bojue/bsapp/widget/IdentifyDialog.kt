package com.bojue.bsapp.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.bojue.bsapp.R

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class IdentifyDialog(private val mContext: Context,private val identifyListener : OnIdentifyClickListener?)
    : Dialog(mContext), View.OnClickListener {

    private lateinit var mEtNumber: EditText
    private lateinit var mEtPassword: EditText
    private lateinit var mBtnIdentify: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_identify_student)
        setCanceledOnTouchOutside(false)
        mEtNumber = findViewById(R.id.et_number)
        mEtPassword = findViewById(R.id.et_password)
        mBtnIdentify = findViewById(R.id.btn_identify)
        mBtnIdentify.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_identify -> {
                val number = mEtNumber.text.toString()
                val password = mEtPassword.text.toString()
                identifyListener?.onIdentify(number, password)
            }
        }
    }

    interface OnIdentifyClickListener {
        fun onIdentify(number: String, password: String)
    }
}