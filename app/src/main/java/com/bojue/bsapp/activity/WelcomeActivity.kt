package com.bojue.bsapp.activity

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.bojue.bsapp.R
import com.sendi.base.constance.UNKNOW_USER
import com.bojue.core.common.BaseActivity
import com.sendi.home.activity.HomeActivity
import com.sendi.login.activity.LoginActivity
import com.sendi.user_export.constance.USER_MANAGER
import com.sendi.user_export.manager.IUserManager
import java.util.*


class WelcomeActivity : BaseActivity() {

    private val myTag = "WelcomeActivity"

    @Autowired(name = USER_MANAGER)
    lateinit var userManager : IUserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcom)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onResume() {
        super.onResume()

        Log.i(myTag,"start")

        val delayTask = object : TimerTask() {
            override fun run() {
                val id = userManager.getUser().id
                if (id == UNKNOW_USER){
                    Log.i(myTag,"to login")
                    val intent  = Intent(this@WelcomeActivity, LoginActivity::class.java)
                    startActivity(intent)
                }else{
                    Log.i(myTag,"to home")
                    val intent  = Intent(this@WelcomeActivity, HomeActivity::class.java)
                    startActivity(intent)
                }
                finish()
            }
        }
        val timer = Timer()
        timer.schedule(delayTask, 2000)
        /*
        runBlocking(newFixedThreadPoolContext(1, "start")){
            Log.i(myTag,"start")
            delay(3000)
            launch(UI){
                val id = UserManager.getUser().id
                if (id == UNKNOW_USER){
                    Log.i(myTag,"to login")
                    val intent  = Intent(this@WelcomeActivity,LoginActivity::class.java)
                    startActivity(intent)
                }else{
                    Log.i(myTag,"to home")
                    val intent  = Intent(this@WelcomeActivity,HomeActivity::class.java)
                    startActivity(intent)
                }
                finish()
            }
        }*/
    }
}
