package com.bojue.bsapp.course

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.bojue.bsapp.R
import com.bojue.bsapp.util.DateUtils
import com.bojue.bsapp.util.PrefUtils
import com.bojue.core.common.BaseActivity
import java.util.*

class CourseActivity : BaseActivity(), ICurrentWeek, AdapterView.OnItemSelectedListener,View.OnClickListener {
    private val myTag = "CourseActivity"

    private var mCurrWeekNum = 0
    private var mSelectWeekNum = 0

    private lateinit var mTvTitleNav : TextView
    private lateinit var mIbWeekSetting : ImageButton

    private lateinit var mWeekSpinner: Spinner
    private lateinit var mWeekDayAdapter: WeekDayAdapter
    private lateinit var mWeekArr: ArrayList<String>
    /**
     * 课程页面的button引用，6行7列
     */
    private val lessons = arrayOf(intArrayOf(R.id.lesson17, R.id.lesson11, R.id.lesson12, R.id.lesson13, R.id.lesson14, R.id.lesson15, R.id.lesson16),
            intArrayOf(R.id.lesson27, R.id.lesson21, R.id.lesson22, R.id.lesson23, R.id.lesson24, R.id.lesson25, R.id.lesson26),
            intArrayOf(R.id.lesson37, R.id.lesson31, R.id.lesson32, R.id.lesson33, R.id.lesson34, R.id.lesson35, R.id.lesson36),
            intArrayOf(R.id.lesson47, R.id.lesson41, R.id.lesson42, R.id.lesson43, R.id.lesson44, R.id.lesson45, R.id.lesson46),
            intArrayOf(R.id.lesson57, R.id.lesson51, R.id.lesson52, R.id.lesson53, R.id.lesson54, R.id.lesson55, R.id.lesson56)/*,
            intArrayOf(R.id.lesson67, R.id.lesson61, R.id.lesson62, R.id.lesson63, R.id.lesson64, R.id.lesson65, R.id.lesson66)*/)


    /**
     * 某节课的背景图,用于随机获取
     */
    private val bg = intArrayOf(R.drawable.kb1, R.drawable.kb2, R.drawable.kb3, R.drawable.kb4,
            R.drawable.kb5, R.drawable.kb6, R.drawable.kb7, R.drawable.kb8, R.drawable.kb9,
            R.drawable.kb10, R.drawable.kb11, R.drawable.kb12, R.drawable.kb13, R.drawable.kb14,
            R.drawable.kb15, R.drawable.kb16, R.drawable.kb17, R.drawable.kb18, R.drawable.kb19,
            R.drawable.kb20, R.drawable.kb21, R.drawable.kb22, R.drawable.kb23, R.drawable.kb24,
            R.drawable.kb25)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        mWeekSpinner = findViewById(R.id.week_day_spinner)
        mTvTitleNav = findViewById(R.id.tv_nav_title)
        mIbWeekSetting = findViewById(R.id.ib_week_setting)
        initWeekSpinner()
        mTvTitleNav.setOnClickListener(this)
        mIbWeekSetting.setOnClickListener(this)
        initCourseTable()
    }

    private fun initCourseTable() {

        findViewById<Button>( lessons[0][3]).setBackgroundResource(getRandomBgRes())
        findViewById<Button>( lessons[0][3]).text = "智能商务 \n 第二教学楼B3305"

        findViewById<Button>( lessons[3][1]).setBackgroundResource(getRandomBgRes())
        findViewById<Button>( lessons[3][1]).text = "RFID技术及应用 \n 第二教学楼B3503"

        findViewById<Button>( lessons[2][2]).setBackgroundResource(getRandomBgRes())
        findViewById<Button>( lessons[2][2]).text = "物联网工程 \n 第二教学楼B4305"

        findViewById<Button>( lessons[3][2]).setBackgroundResource(getRandomBgRes())
        findViewById<Button>( lessons[3][2]).text = "移动通信与移动技术 \n 第二教学楼B3304"

        findViewById<Button>( lessons[2][5]).setBackgroundResource(getRandomBgRes())
        findViewById<Button>( lessons[2][5]).text = "移动通信与移动技术 \n 第二教学楼B3304"

        findViewById<Button>( lessons[3][5]).setBackgroundResource(getRandomBgRes())
        findViewById<Button>( lessons[3][5]).text = "物联网工程 \n 第二教学楼B4305"

//        for (row in 0..4){
//            for (col in 0..6){
//                findViewById<Button>( lessons[row][col]).setBackgroundResource(getRandomBgRes())
//            }
//        }
    }

    private fun initWeekSpinner() {
        updateCurrWeek()
        mWeekArr = ArrayList()
        val s = "第1周"
        (25 downTo 1)
                .map { s.replace("[\\d]+".toRegex(), it.toString()) }
                .forEach { mWeekArr.add(0, it) }

        mWeekDayAdapter = WeekDayAdapter(this, R.layout.week_day_item_layout, mWeekArr)
        mWeekDayAdapter.setDropDownViewResource(R.layout.week_day_drop_item_layout)
        mWeekSpinner.adapter = mWeekDayAdapter
        if (mCurrWeekNum <= 25) {
            mWeekSpinner.setSelection(mCurrWeekNum - 1, true)
        }
        mWeekSpinner.onItemSelectedListener = this
    }

    private fun updateCurrWeek() {
        val currTime = Date().time
        val beginTime = PrefUtils.getBeginTime(this, currTime)
        mCurrWeekNum = DateUtils.countCurrWeek(beginTime, currTime)
        mSelectWeekNum = mCurrWeekNum
    }

    private fun clickSelectCurrWeekNum() {
//        if (mCurrWeekNum == 0) {
//            return
//        }
        val num = arrayOfNulls<String>(25)
        for (i in 0..24) {
            num[i] = "第" + (i + 1).toString() + "周"
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("选择当前周")
        builder.setItems(num, DialogInterface.OnClickListener { dialog, which ->
            if (which + 1 == mCurrWeekNum) {
                return@OnClickListener
            }
            PrefUtils.setBeginTime(this, DateUtils.countBeginTime(Calendar.getInstance(), which + 1))

            refreshCourseTable(which+1)
        })
        builder.create().show()
    }

    private fun refreshCourseTable(selectCurrWeek : Int) {
        mCurrWeekNum = selectCurrWeek
        mWeekSpinner.setSelection(mCurrWeekNum - 1)
        mWeekDayAdapter.notifyDataSetChanged()
    }

    override fun getCurrentWeek(): Int {
        return mCurrWeekNum
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_nav_title ->{
                finish()
            }
            R.id.ib_week_setting -> {
               clickSelectCurrWeekNum()
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.i(myTag, "onItemSelected select item $position")
        mSelectWeekNum = position+1
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    private fun getRandomBgRes(): Int{
        val random = Random()
        val index = random.nextInt(25)
        Log.i(myTag,"getRandomBgRes index -> $index")
        return bg[index]
    }
}
