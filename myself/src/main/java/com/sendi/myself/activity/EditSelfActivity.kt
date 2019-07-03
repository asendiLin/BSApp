package com.sendi.myself.activity

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.sendi.base.constance.SUCCESS_STATU
import com.bojue.core.ext.getViewModel
import com.sendi.base.util.UploadPicManager
import com.bojue.core.common.BaseActivity
import com.bojue.core.event.EventUtil
import com.sendi.base.util.ShowImageUtil
import com.sendi.base.widget.LoadingDialog
import com.sendi.myself.R
import com.sendi.user_export.constance.USER_MANAGER
import com.sendi.user_export.manager.IUserManager
import com.sendi.user_export.model.UserModel
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.api.widget.Widget

class EditSelfActivity : BaseActivity() , View.OnClickListener{
    private val myTag = "EditSelfActivity"

    private lateinit var mBtnComplete : Button
    private lateinit var mTvBack :TextView
    private lateinit var mEtNickname : EditText
    private lateinit var mEtSignature : EditText
    private lateinit var mIvUserIcon : ImageView
    private lateinit var mRlUserIcon : RelativeLayout
    private val mLoadingDialog by lazy {
        LoadingDialog(this)
    }
    private val mEditInfoViewModel by lazy {
        getViewModel(com.sendi.myself.viewmodel.EditInfoViewModel::class.java)
    }

    @Autowired(name = USER_MANAGER)
    lateinit var userManager: IUserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_self)

        initView()

        mEditInfoViewModel.editInfoLiveData.observe(this, Observer{result ->
            mLoadingDialog.dismiss()

            Log.i(myTag,"user = ${result?.data}")

            result?.let {
                if (result.status == SUCCESS_STATU){
                    result.data?.let {
                        userManager.saveUser(it)
                        EventUtil.post(it)
                    }
                    Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show()
                    finish()
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
        mRlUserIcon = findViewById(R.id.rl_user_icon)

        mBtnComplete.setOnClickListener(this)
        mTvBack.setOnClickListener(this)
        mRlUserIcon.setOnClickListener(this)
//        val user = userManager.getUser()
        val user = UserModel(1,"name","123","","","","asendi","","better man")
        mEtNickname.setText(user.nickname?:"")
        mEtSignature.setText(user.signature?:"")
        ShowImageUtil.showImage(this,mIvUserIcon,user.icon)
    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.tv_nav_title ->{
                finish()
            }
            R.id.btn_complete ->{
                mLoadingDialog.show()
                val nickname = mEtNickname.text.toString()
                val signature = mEtSignature.text.toString()
                val user =userManager.getUser()
                Log.i(myTag,"edit info user-> $user")
                user.nickname = nickname
                user.signature = signature
                mEditInfoViewModel.editInfo(user.id,user.username,user.password,
                            user.number,user.classname,user.icon,user.nickname,user.phone,user.signature)
            }
            R.id.rl_user_icon ->{
                showBottomDialog()
            }
        }
    }


    private fun showBottomDialog() {
        val bottomDialog = Dialog(this, R.style.BottomDialog)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_select_bottom, null, false)
        val tvAlbum = view.findViewById<TextView>(R.id.tv_album)
        val tvCamera = view.findViewById<TextView>(R.id.tv_camera)
        val tvCancel = view.findViewById<TextView>(R.id.tv_cancel)
        val titleWidget = Widget.newDarkBuilder(this)
                .title("相册")
                .build()
        tvAlbum.setOnClickListener {
            Album.image(this) // Image selection.
                    .singleChoice()
                    .widget(titleWidget)
                    .camera(true)
                    .columnCount(4)
                    .onResult { albumFileList ->
                        albumFileList.listIterator().forEach {
                            Log.i(myTag, "path : ${it.path}")
                            uploadPic(it.path)
                        }
                    }
                    .onCancel { cancel ->
                    }
                    .start()
            if (bottomDialog.isShowing) {
                bottomDialog.dismiss()
            }
        }

        tvCamera.setOnClickListener {
            Album.camera(this)
                    .image()
                    .onResult { path ->
                        Log.i(myTag, "path : $path")
                        uploadPic(path)
                    }
                    .start()
            if (bottomDialog.isShowing) {
                bottomDialog.dismiss()
            }
        }

        tvCancel.setOnClickListener {
            if (bottomDialog.isShowing) {
                bottomDialog.dismiss()
            }
        }

        bottomDialog.setContentView(view)
        val window = bottomDialog.window
        window.setGravity(Gravity.BOTTOM)
        val params = window.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = params
        bottomDialog.show()
    }

    private fun uploadPic(path: String) {
        val bitmap = BitmapFactory.decodeFile(path)
        UploadPicManager().uploadPic(this,bitmap,object : UploadPicManager.OnUploadPicListener{
            override fun onSuccess(path: String) {
                val user = userManager.getUser()
                user.icon = path
                ShowImageUtil.showImage(this@EditSelfActivity,mIvUserIcon,user.icon)
                Log.i(myTag,"edit pic user -> $user")
                    mEditInfoViewModel.editInfo(user.id,user.username,user.password,
                            user.number,user.classname,user.icon,user.nickname,user.phone,user.signature)
            }

            override fun onFail(message: String) {
                Toast.makeText(this@EditSelfActivity,message,Toast.LENGTH_SHORT).show()
            }
        })
    }
}
