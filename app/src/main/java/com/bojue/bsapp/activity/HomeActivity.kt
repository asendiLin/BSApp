package com.bojue.bsapp.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.RadioGroup
import com.bojue.bsapp.R
import com.bojue.bsapp.community.CommunityFragment
import com.bojue.bsapp.myself.MyselfFragment
import com.bojue.bsapp.order.OrderListFrgment
import com.bojue.bsapp.publish.PublishOrderFragment
import com.bojue.core.common.BaseActivity

/**
 * 用于存放首页四个tab对应的Fragment
 */
class HomeActivity : BaseActivity() {

    private lateinit var mRgBottom: RadioGroup

    private var mCurrentIndex = 0
    private var mCurrentFragment : Fragment? = null

    private val mFragments = arrayOf(OrderListFrgment(), PublishOrderFragment(),CommunityFragment(),MyselfFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mRgBottom = findViewById(R.id.rg_bottom)
        mRgBottom.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_order_list -> {
                    mCurrentIndex = ORDER_LIST_INDEX
                }
                R.id.rb_order_publish -> {
                    mCurrentIndex = ORDER_PUBLISH_INDEX
                }
                R.id.rb_community -> {
                    mCurrentIndex = COMMUNITY_INDEX
                }
                R.id.rb_myself -> {
                    mCurrentIndex = MYSELF_INDEX
                }
            }

            changeFragment(mCurrentFragment,mFragments[mCurrentIndex])
        }


        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_home_content, OrderListFrgment(), "HOME")
        transaction.commit()
    }

    private fun changeFragment(fromFragment : Fragment? , toFragment: Fragment) {
        if (fromFragment != toFragment){
            val transaction = supportFragmentManager.beginTransaction()

            if (fromFragment!= null){
                transaction.hide(fromFragment)
            }

            if (!toFragment.isAdded){
                transaction.add(R.id.fl_home_content,toFragment).commit()
            }else{
                transaction.show(toFragment).commit()
            }
            mCurrentFragment = toFragment
        }
    }

}

const val ORDER_LIST_INDEX = 0
const val ORDER_PUBLISH_INDEX = 1
const val COMMUNITY_INDEX = 2
const val MYSELF_INDEX = 3