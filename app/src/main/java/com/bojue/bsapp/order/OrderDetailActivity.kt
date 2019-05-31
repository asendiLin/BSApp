package com.bojue.bsapp.order

import android.arch.lifecycle.Observer
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bojue.bsapp.R
import com.bojue.bsapp.constance.DOING_ORDER
import com.bojue.bsapp.constance.ORDER_DETAIL
import com.bojue.bsapp.constance.SUCCESS_STATU
import com.bojue.bsapp.ext.getViewModel
import com.bojue.bsapp.model.OrderModel
import com.bojue.bsapp.util.ShowImageUtil
import com.bojue.bsapp.util.ToastUtil
import com.bojue.bsapp.util.UserManager
import com.bojue.core.common.BaseActivity
import com.bumptech.glide.Glide
import com.yanzhenjie.album.widget.LoadingDialog

class OrderDetailActivity : BaseActivity() {

    private lateinit var mToolbar : Toolbar
    private lateinit var mBtnOrderAccept : Button
    private lateinit var mTvTitle : TextView
    private lateinit var mIvUserIcon : ImageView
    private lateinit var mTvNickname : TextView
    private lateinit var mTvPhoneNumber : TextView
    private lateinit var mTvEndTime : TextView
    private lateinit var mTvOrderContent : TextView
    private lateinit var mTvOrderPrice : TextView
    private lateinit var mTvOrderAddress : TextView
    private lateinit var mOrderModel : OrderModel

    private val mLoadingDialog by lazy {
        LoadingDialog(this)
    }
    private val mOrderDetailViewModel by lazy {
        getViewModel(OrderDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        initView()

        initData()
    }

    private fun initView() {
        mToolbar=findViewById(R.id.tb_title)
        mBtnOrderAccept = findViewById(R.id.btn_order_accept)
        mTvTitle = findViewById(R.id.tv_title_content)
        mIvUserIcon = findViewById(R.id.iv_user_pic)
        mTvNickname = findViewById(R.id.tv_nickname)
        mTvPhoneNumber = findViewById(R.id.tv_phone_number)
        mTvEndTime = findViewById(R.id.tv_end_time)
        mTvOrderContent = findViewById(R.id.tv_order_content)
        mTvOrderPrice = findViewById(R.id.tv_order__price)
        mTvOrderAddress = findViewById(R.id.tv_address)

        mToolbar.navigationIcon =resources.getDrawable(R.mipmap.ic_title_back)
        mToolbar.setNavigationOnClickListener { finish() }
        mBtnOrderAccept.setOnClickListener {

            acceptOrder(DOING_ORDER,mOrderModel.id,UserManager.getUser().id)
        }
        mTvTitle.text = "订单详情"
    }

    private fun initData() {
        mOrderModel = intent.getParcelableExtra(ORDER_DETAIL)
        ShowImageUtil.showImage(this,mIvUserIcon,mOrderModel.student?.icon)
        mTvNickname.text = mOrderModel.student?.nickname
        mTvPhoneNumber.text = mOrderModel.phone
        mTvEndTime.text = mOrderModel.time
        mTvOrderContent.text = mOrderModel.content
        mTvOrderPrice.text = mOrderModel.money.toString()
        mTvOrderAddress.text = mOrderModel.address
    }

    private fun acceptOrder(status :Int ,id:Int,stuId:Int) {
        if (UserManager.getUser().number .isNullOrEmpty()){
            ToastUtil.showShort(this,"通过学生认证后才能发布订单")
            return
        }
        if (UserManager.getUser().phone.isNullOrEmpty()){
            ToastUtil.showShort(this,"您还没有设置手机号码哦")
        }
        mLoadingDialog.show()
        mOrderDetailViewModel.acceptOrder(status, id, stuId).observe(this , Observer { result->
           if (mLoadingDialog.isShowing){
               mLoadingDialog.dismiss()
           }
           if (result?.status == SUCCESS_STATU){
               Toast.makeText(this,result.data,Toast.LENGTH_SHORT).show()
               finish()
           }else{
               Toast.makeText(this,result?.message,Toast.LENGTH_SHORT).show()
           }

       })
    }
}
