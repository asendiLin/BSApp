package com.bojue.bsapp.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.bojue.bsapp.R
import com.bojue.bsapp.util.SPUtils

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class SettingUrlDialog(private val mContext: Context)
    : Dialog(mContext), View.OnClickListener {

    private lateinit var mEtUrl: EditText
    private lateinit var mBtnSave: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_settiing)
        setCanceledOnTouchOutside(false)
        mEtUrl = findViewById(R.id.et_url)
        mBtnSave = findViewById(R.id.btn_save)
        mBtnSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_identify -> {
                val url = mEtUrl.text.toString()
                SPUtils.saveString(mContext,"base_url", url)
                dismiss()
            }
        }
    }

}