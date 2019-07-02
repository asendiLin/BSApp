package com.bojue.bsapp.myself

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import com.bojue.bsapp.R
import com.bojue.core.common.BaseFragment
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.*
import android.widget.*
import com.bojue.bsapp.community.MyselfCommunityActivity
import com.bojue.bsapp.course.CourseActivity
import com.sendi.base.event.SettingEvent
import com.bojue.core.ext.getViewModel
import com.bojue.bsapp.model.CommunityModel
import com.sendi.user.UserModel
import com.bojue.bsapp.setting.SettingActivity
import com.bojue.bsapp.util.*
import com.bojue.core.event.EventUtil
import com.sendi.base.constance.*
import com.sendi.base.util.ToastUtil
import org.greenrobot.eventbus.Subscribe
import java.util.*
import kotlin.collections.ArrayList


/**
 * author: asendi.
 * data: 2019/5/10.
 * description:
 */
class MyselfFragment : BaseFragment(), View.OnClickListener {
    private val myTag = "MyselfFragment"

    private lateinit var mRootView: View
    private lateinit var mTbTop: Toolbar
    private lateinit var mAblTop: AppBarLayout
    private lateinit var mCtlTop: CollapsingToolbarLayout
    private lateinit var mLLCourse: LinearLayout
    private lateinit var mTvEditInfo: TextView
    private lateinit var mIbSetting: ImageButton
    private lateinit var mIvUserIcon: ImageView
    private lateinit var mTvNickname: TextView
    private lateinit var mTvSignature: TextView
    private lateinit var mTvDoingCount: TextView
    private lateinit var mTvCancelCount: TextView
    private lateinit var mTvCompleteCount: TextView
    private lateinit var mLlDoingCount: LinearLayout
    private lateinit var mLlCancelCount: LinearLayout
    private lateinit var mLlCompleteCount: LinearLayout
    private lateinit var mLlMyselfCommunity: LinearLayout
    private lateinit var mTvCourseStatus: TextView
    private lateinit var mTvZanCount: TextView
    private lateinit var mTvCommunityCount: TextView

    private val mCommunityList = ArrayList<CommunityModel>(32)

    private val mLoadingDialog by lazy {
        LoadingDialog(requireActivity())
    }

    private val mMyselfViewModel by lazy {
        getViewModel(MyselfViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventUtil.register(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = LayoutInflater.from(context).inflate(R.layout.fragment_myself_layout, null, false)
        mTbTop = mRootView.findViewById(R.id.tb_top)
        mAblTop = mRootView.findViewById(R.id.abl_self_top)
        mCtlTop = mRootView.findViewById(R.id.ctbl_self_top)
        mLLCourse = mRootView.findViewById(R.id.ll_course)
        mTvEditInfo = mRootView.findViewById(R.id.tv_edit_info)
        mIbSetting = mRootView.findViewById(R.id.ib_setting)
        mIvUserIcon = mRootView.findViewById(R.id.civ_myself_user)
        mTvNickname = mRootView.findViewById(R.id.tv_myself_nickname)
        mTvSignature = mRootView.findViewById(R.id.tv_myself_signature)
        mTvDoingCount = mRootView.findViewById(R.id.tv_doing_count)
        mTvCompleteCount = mRootView.findViewById(R.id.tv_complete_count)
        mTvCancelCount = mRootView.findViewById(R.id.tv_cancel_count)
        mLlDoingCount = mRootView.findViewById(R.id.ll_doing_count)
        mLlCompleteCount = mRootView.findViewById(R.id.ll_complete_count)
        mLlCancelCount = mRootView.findViewById(R.id.ll_cancel_count)
        mLlMyselfCommunity = mRootView.findViewById(R.id.ll_myself_community)
        mTvCourseStatus = mRootView.findViewById(R.id.tv_course_status)
        mTvZanCount = mRootView.findViewById(R.id.tv_community_zan_count)
        mTvCommunityCount = mRootView.findViewById(R.id.tv_community_count)
        initCurrentCourse()

        val user = UserManager.getUser()
        mTvNickname.text = user.nickname
        mTvSignature.text = user.signature
        ShowImageUtil.showImage(this, mIvUserIcon, user.icon)

        mIbSetting.setOnClickListener(this)
        mTvEditInfo.setOnClickListener(this)
        mLLCourse.setOnClickListener(this)
        mLlDoingCount.setOnClickListener(this)
        mLlCancelCount.setOnClickListener(this)
        mLlCompleteCount.setOnClickListener(this)
        mIvUserIcon.setOnClickListener(this)
        mLlMyselfCommunity.setOnClickListener(this)

        (activity as AppCompatActivity).setSupportActionBar(mTbTop)
        mCtlTop.title = UserManager.getUser().nickname

        return mRootView
    }

    private fun initCurrentCourse() {
        val user = UserManager.getUser()
        if (user.number == null) {
            mTvCourseStatus.text = "您未通过学生认证哦"
        } else {
            //查课表
            val calendar = Calendar.getInstance()
            calendar.time = Date()
            val week = calendar.get(Calendar.DAY_OF_WEEK)
            Log.i(myTag,"week- > $week")
            val courseCount = CourseUtil.getTodayCourseCount(week-1)
            if (courseCount == 0) {
                mTvCourseStatus.text = "今天没课哦"
            } else {
                val spannableString = SpannableString("今天有${courseCount}节课要上哦")
                spannableString.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorTheme)), 3, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                mTvCourseStatus.text = spannableString
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mMyselfViewModel.myselfCommunityLiveData.observe(this, Observer { data ->
            data?.let {
                mTvZanCount.text = data.status.toString()
                mTvCommunityCount.text = data.data?.size.toString()
                data.data?.let {
                    mCommunityList.clear()
                    mCommunityList.addAll(data.data)
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        mMyselfViewModel.getCommunityList(UserManager.getUser().username)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_course -> {
                val user = UserManager.getUser()
                if (user.number == null) {
                    ToastUtil.showLong(requireContext(), "你还没有通过学生认证哦")
                } else {
                    val intent = Intent(requireContext(), CourseActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.tv_edit_info -> {
                val intent = Intent(requireContext(), EditSelfActivity::class.java)
                startActivity(intent)
            }
            R.id.ib_setting -> {
                val intent = Intent(requireContext(), SettingActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_doing_count -> {
                val intent = Intent(requireContext(), OrderHistoryActivity::class.java)
                intent.putExtra(HISTORY_ORDER_TYPE, DOING_ORDER)
                startActivity(intent)
            }
            R.id.ll_cancel_count -> {
                val intent = Intent(requireContext(), OrderHistoryActivity::class.java)
                intent.putExtra(HISTORY_ORDER_TYPE, CANCEL_ORDER)
                startActivity(intent)
            }
            R.id.ll_complete_count -> {
                val intent = Intent(requireContext(), OrderHistoryActivity::class.java)
                intent.putExtra(HISTORY_ORDER_TYPE, COMPLETE_ORDER)
                startActivity(intent)
            }
            R.id.ll_myself_community -> {
                val intent = Intent(requireActivity(), MyselfCommunityActivity::class.java)
                intent.putParcelableArrayListExtra(COMMUNITY_LIST, mCommunityList)
                requireActivity().startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventUtil.unregister(this)
    }

    @Subscribe
    fun onUserInfoEvent(user: com.sendi.user.UserModel) {
        mTvNickname.text = user.nickname
        mTvSignature.text = user.signature
        ShowImageUtil.showImage(this, mIvUserIcon, user.icon)
    }

    @Subscribe
    fun onSettingEvent(event : SettingEvent){
        when(event.type){
            SettingEvent.COURSE ->{
                mTvCourseStatus.text = "您已通过学生认证，赶快查询课表吧！"
            }
        }
    }

}
