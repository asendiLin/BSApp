package com.sendi.order.fragment

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.bojue.core.ext.getViewModel
import com.sendi.base.util.ToastUtil
import com.bojue.core.common.BaseFragment
import com.example.order.R
import com.sendi.base.constance.*
import com.sendi.base.widget.LoadingDialog
import com.sendi.order.inject.OrderInjector
import com.sendi.order.viewmodel.PublishOrderViewModel
import com.sendi.user_export.constance.USER_MANAGER
import com.sendi.user_export.manager.IUserManager
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by lizheng on 2019/5/12.
 */
@Route(path = ORDER_PUBLISH_PATH)
class PublishOrderFragment : BaseFragment(),View.OnClickListener{

    private val myTag = "PublishOrderFragment"

    private lateinit var rootView: View
    private lateinit var pvTime: TimePickerView
    private lateinit var spinnerItems:List<String>
    private lateinit var txt_timepicker: TextView
    private lateinit var mBtnPublish : FloatingActionButton
    private lateinit var mTvType1 : TextView
    private lateinit var mTvType2 : TextView
    private lateinit var mTvType3 : TextView
    private lateinit var mTvType4 : TextView
    private lateinit var mTvType5 : TextView
    private lateinit var mTvType6 : TextView
    private lateinit var mTvType7 : TextView

    private lateinit var mEtPhoneNumber :EditText
    private lateinit var mEtPay :EditText
    private lateinit var mEtDetailContent :EditText

    @Autowired(name = USER_MANAGER)
    lateinit var userManager : IUserManager

    private val mLoadingDialog by lazy {
        LoadingDialog(requireActivity())
    }

    private var mCurrentType = originType
    private  val  mPublishViewModel by lazy { 
        getViewModel(PublishOrderViewModel::class.java)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView =LayoutInflater.from(context).inflate(R.layout.order_fragment_pulish_order_layout,null,false)
        mBtnPublish = rootView.findViewById(R.id.btn_order_publish)
        mTvType1 =rootView.findViewById(R.id.tv_order_type1)
        mTvType2 =rootView.findViewById(R.id.tv_order_type2)
        mTvType3 =rootView.findViewById(R.id.tv_order_type3)
        mTvType4 =rootView.findViewById(R.id.tv_order_type4)
        mTvType5 =rootView.findViewById(R.id.tv_order_type5)
        mTvType6 =rootView.findViewById(R.id.tv_order_type6)
        mTvType7 =rootView.findViewById(R.id.tv_order_type7)
        mEtPhoneNumber = rootView.findViewById(R.id.et_phone_number)
        mEtPay = rootView.findViewById(R.id.et_pay)
        mEtDetailContent = rootView.findViewById(R.id.et_detail_content)

        mBtnPublish.setOnClickListener(this)
        mTvType1.setOnClickListener(this)
        mTvType2.setOnClickListener(this)
        mTvType3.setOnClickListener(this)
        mTvType4.setOnClickListener(this)
        mTvType5.setOnClickListener(this)
        mTvType6.setOnClickListener(this)
        mTvType7.setOnClickListener(this)
        val selectedDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        endDate.set(2019, 11, 31)

        txt_timepicker = rootView.findViewById(R.id.txt_timepicker)
        txt_timepicker.text = dateToStrLong(selectedDate.time)

        spinnerItems = arrayListOf("1","2","3")
        val spinnerAdapter = ArrayAdapter(context,android.R.layout.simple_spinner_item, spinnerItems)

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
                .setSubmitColor(resources.getColor(R.color.colorTheme))//确定按钮文字颜色
                .setCancelColor(resources.getColor(R.color.colorTheme))//取消按钮文字颜色
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

        mPublishViewModel.publishLiveData.observe(this, Observer {result ->
            Log.i(myTag,"result-> $result")
            if (mLoadingDialog.isShowing){
                mLoadingDialog.dismiss()
            }
            result?.let {
                if (result.status == SUCCESS_STATU){
                    Toast.makeText(requireContext(),"发布成功",Toast.LENGTH_SHORT).show()
                    reset()
                }else{
                    Toast.makeText(requireContext(),result.message,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun reset(){
        mEtPhoneNumber.setText("")
        mEtDetailContent.setText("")
        mEtPay.setText("")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_order_publish ->{

                if (userManager.getUser().number .isNullOrEmpty()){
                    ToastUtil.showShort(requireContext(),"通过学生认证后才能发布订单")
                    return
                }

                if (mEtPhoneNumber.text.isNullOrEmpty() ||  mEtPay.text.isNullOrEmpty() || mEtDetailContent.text.isNullOrEmpty()){
                    ToastUtil.showShort(requireContext(),"请完善订单信息")
                    return
                }

                mLoadingDialog.show()
                val stuId = userManager.getUser().id
                val phoneNumber = mEtPhoneNumber.text.toString()
                val pay = mEtPay.text.toString().toInt()
                val date = txt_timepicker.text.toString()
                val orderDetail = mEtDetailContent.text.toString()
                val location = "广东海洋大学"

                mPublishViewModel.publishOrder(orderDetail,stuId,mCurrentType,phoneNumber,
                        pay,date,location)
            }
            R.id.tv_order_type1 ->{
                showSelectedType(paotui)
            }
            R.id.tv_order_type2 ->{
                showSelectedType(daina)
            }
            R.id.tv_order_type3 ->{
                showSelectedType(pinche)
            }
            R.id.tv_order_type4 ->{
                showSelectedType(huhuan)
            }
            R.id.tv_order_type5 ->{
                showSelectedType(shunfengche)
            }
            R.id.tv_order_type6->{
                showSelectedType(peiban)
            }
            R.id.tv_order_type7 ->{
                showSelectedType(other)
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun showSelectedType(type :Int){
        if(type == mCurrentType){
            return
        }
        
        when(mCurrentType){
            paotui ->{
                mTvType1.background = getNormalBackground()
                mTvType1.setTextColor(R.color.colorBlack)
            }
            daina ->{
                mTvType2.background = getNormalBackground()
                mTvType2.setTextColor(R.color.colorBlack)
            }
            pinche ->{
                mTvType3.background = getNormalBackground()
                mTvType3.setTextColor(R.color.colorBlack)
            }
            huhuan ->{
                mTvType4.background = getNormalBackground()
                mTvType4.setTextColor(R.color.colorBlack)
            }
            shunfengche ->{
                mTvType5.background = getNormalBackground()
                mTvType5.setTextColor(R.color.colorBlack)
            }
            peiban ->{
                mTvType6.background = getNormalBackground()
                mTvType6.setTextColor(R.color.colorBlack)
            }
            other ->{
                mTvType7.background = getNormalBackground()
                mTvType7.setTextColor(R.color.colorBlack)
            }
        }
        
        when(type){
            paotui ->{
                mTvType1.background = getSelectedBackground()
                mTvType1.setTextColor(R.color.colorTheme)
            }
            daina ->{
                mTvType2.background = getSelectedBackground()
                mTvType2.setTextColor(R.color.colorTheme)
            }
            pinche ->{
                mTvType3.background = getSelectedBackground()
                mTvType3.setTextColor(R.color.colorTheme)
            }
            huhuan ->{
                mTvType4.background = getSelectedBackground()
                mTvType4.setTextColor(R.color.colorTheme)
            }
            shunfengche ->{
                mTvType5.background = getSelectedBackground()
                mTvType5.setTextColor(R.color.colorTheme)
            }
            peiban ->{
                mTvType6.background = getSelectedBackground()
                mTvType6.setTextColor(R.color.colorTheme)
            }
            other ->{
                mTvType7.background = getSelectedBackground()
                mTvType7.setTextColor(R.color.colorTheme)
            }
        }
        mCurrentType = type
    }
    
    private fun getSelectedBackground():Drawable{
        return requireActivity().resources.getDrawable(R.drawable.ractangle_type_selected_bg)
    }
    private fun getNormalBackground():Drawable{
        return requireActivity().resources.getDrawable(R.drawable.ractangle_type_bg)
    }
    
    @SuppressLint("SimpleDateFormat")
    fun dateToStrLong(date: Date): String {
        val formatter = SimpleDateFormat("MM月dd日")
        val dateString = formatter.format(date)
        return dateString
    }

    override fun viewModelFactory(): ViewModelProvider.Factory {
        return OrderInjector.viewModelFactory()
    }
}