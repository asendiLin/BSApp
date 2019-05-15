package com.bojue.bsapp.publish

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.bojue.bsapp.R
import com.bojue.bsapp.inject.Injector
import com.bojue.core.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_pulish_layout.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by lizheng on 2019/5/12.
 */
class PublishFragment: BaseFragment(){


    private lateinit var rootView: View
    private lateinit var pvTime: TimePickerView
    private lateinit var spinnerItems:List<String>
    private lateinit var spinner: Spinner
    private lateinit var txt_timepicker: TextView
    private lateinit var mPublishViewModel: PublishViewModel

    private val mmViewModelFactory by lazy {
        Injector.viewModelFactory()
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView =LayoutInflater.from(context).inflate(R.layout.fragment_pulish_layout,null,false)
        val selectedDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        endDate.set(2019, 11, 31)
        spinner = rootView.findViewById(R.id.spinner)
        txt_timepicker = rootView.findViewById(R.id.txt_timepicker)
        txt_timepicker.text = dateToStrLong(selectedDate.time)

        spinnerItems = arrayListOf("1","2","3")
        val spinnerAdapter = ArrayAdapter(context,android.R.layout.simple_spinner_item, spinnerItems)
        spinner.adapter  = spinnerAdapter
        /*
        spinner选择事件
        */
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

        }

        pvTime = TimePickerBuilder(context, OnTimeSelectListener { date, v ->
            txt_timepicker.text = dateToStrLong(date)

        }).setType(booleanArrayOf(false, true, true, false, false, false))// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(18)
                .setTitleSize(22)//标题文字大小
                .setTitleText("选择日期")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTitleBgColor(0xFFFFFFFF.toInt())//标题背景颜色 Night mode
                .setBgColor(0xFFFFFFFF.toInt())//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build()

        txt_timepicker.setOnClickListener {
            pvTime.show()
        }
        return rootView

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPublishViewModel = ViewModelProviders.of(this,mmViewModelFactory).get(PublishViewModel::class.java)
        mPublishViewModel.publishData().observe(this, android.arch.lifecycle.Observer {

        })

    }

    @SuppressLint("SimpleDateFormat")
    fun dateToStrLong(date:Date): String {
        val formatter = SimpleDateFormat("MM月dd日")
        val dateString = formatter.format(date)
        return dateString
    }
}