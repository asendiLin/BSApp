package com.bojue.bsapp.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.bojue.bsapp.R

/**
 * author: asendi.
 * data: 2019/5/22.
 * description:
 */
class LoadingDialog(private val mContext : Context) : Dialog(mContext) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        setCanceledOnTouchOutside(false)
    }
}