package com.bojue.bsapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bojue.bsapp.R;
import com.bojue.bsapp.model.CommunityModel;
import com.bojue.bsapp.util.ShowImageUtil;
import com.bojue.bsapp.util.UserManager;

import java.util.ArrayList;
import java.util.List;

/**
 * author: asendi.
 * data: 2019/5/30.
 * description:
 */

public class ListBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private List<CommunityModel> mCommunityModelList = new ArrayList<>(32);
    private RecyclerView mRvCommentList;
    private EditText mEtComentContent;
    private Button mBtnSend;
    private CommentItemAdapter mCommentItemAdapter;
    private OnClickSendCommentCallback mClickSendCommentCallback;
    private LoadingDialog mLoadingDialog;
    private int mPosition = 0;
    private String myTag = "ListBottomSheetDialogFragment";

    public void setClickSendCommentCallback(OnClickSendCommentCallback clickSendCommentCallback) {
        mClickSendCommentCallback = clickSendCommentCallback;
    }

    public void show(FragmentManager fragmentManager, String tag, int position) {
        show(fragmentManager, tag);
        mPosition = position;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mCommunityModelList.clear();
        mCommentItemAdapter.notifyDataSetChanged();
    }

    public void refreshCommentList(List<CommunityModel> communityModelList) {
        Log.i(myTag, "refreshCommentList");
        mCommunityModelList.clear();
        mCommunityModelList.addAll(communityModelList);
        mCommentItemAdapter.notifyDataSetChanged();


//        View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_item_list_dialog_item, (ViewGroup) getView(), false);
//        int itemHigh = getItemHigh(itemView);

//        if (itemHigh * communityModelList.size() < getScreenHeight(requireContext())){
//            Dialog dialog = getDialog();
//            Window win = dialog.getWindow();
//            win.setGravity(Gravity.BOTTOM);
//            WindowManager.LayoutParams params = win.getAttributes();
//            params.height = itemHigh * communityModelList.size();
//            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//            win.setAttributes(params);
//        }

//        if (mCommunityModelList.isEmpty()) {
//            Dialog dialog = getDialog();
//            Window win = dialog.getWindow();
//            win.setGravity(Gravity.BOTTOM);
//            WindowManager.LayoutParams params = win.getAttributes();
//            params.height = (int) (getScreenHeight(getActivity()) * 0.2);
//            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//            win.setAttributes(params);
//        }
    }

    public void refreshCommentList(CommunityModel communityModel) {
        communityModel.setIcon(UserManager.INSTANCE.getUser().getIcon());
        communityModel.setNickname(UserManager.INSTANCE.getUser().getNickname());
        mCommunityModelList.add(communityModel);
        mCommentItemAdapter.notifyDataSetChanged();
        mEtComentContent.setText("");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //给dialog设置主题为透明背景 不然会有默认的白色背景
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomBottomSheetDialogTheme);
    }

    /**
     * 如果想要点击外部消失的话 重写此方法
     *
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        //设置点击外部可消失
        dialog.setCanceledOnTouchOutside(true);
        //设置使软键盘弹出的时候dialog不会被顶起
        Window win = dialog.getWindow();
        WindowManager.LayoutParams params = win.getAttributes();
        win.setSoftInputMode(params.SOFT_INPUT_ADJUST_NOTHING);
        //这里设置dialog的进出动画
//        win.setWindowAnimations(R.style.DialogBottomAnim);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 在这里将view的高度设置为精确高度，即可屏蔽向上滑动不占全屏的手势。
        //如果不设置高度的话 会默认向上滑动时dialog覆盖全屏
        View view = inflater.inflate(R.layout.fragment_item_list_dialog, container, false);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (getScreenHeight(getActivity()) * 0.5)));
        Log.i(myTag, "onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRvCommentList = view.findViewById(R.id.rv_comment_list);
        mEtComentContent = view.findViewById(R.id.et_comment_content);
        mBtnSend = view.findViewById(R.id.btn_send_comment);
        mLoadingDialog = new LoadingDialog(requireActivity());

        mCommentItemAdapter = new CommentItemAdapter(mCommunityModelList);
        mRvCommentList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvCommentList.setAdapter(mCommentItemAdapter);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEtComentContent.getText().toString();
                if (mClickSendCommentCallback != null) {
                    mClickSendCommentCallback.onSend(mPosition, content);

                }
            }
        });
    }

    private class CommentItemAdapter extends RecyclerView.Adapter<CommentItemAdapter.CommentViewHolder> {
        private final List<CommunityModel> mCommunityModels;

        CommentItemAdapter(@NonNull List<CommunityModel> modelList) {
            this.mCommunityModels = modelList;
        }

        @Override
        public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CommentViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(CommentViewHolder holder, int position) {
            CommunityModel communityModel = mCommunityModels.get(position);
            holder.tvComment.setText(communityModel.getContent());
            holder.tvNickname.setText(communityModel.getNickname());
            ShowImageUtil.INSTANCE.showImage(requireActivity(), holder.ivIcon, communityModel.getIcon());
        }

        @Override
        public int getItemCount() {
            return mCommunityModels.size();
        }

        public class CommentViewHolder extends RecyclerView.ViewHolder {
            final TextView tvComment;
            final TextView tvNickname;
            final ImageView ivIcon;

            CommentViewHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.fragment_item_list_dialog_item, parent, false));
                tvComment = itemView.findViewById(R.id.tv_comment_content);
                tvNickname = itemView.findViewById(R.id.tv_nickname);
                ivIcon = itemView.findViewById(R.id.civ_comment_user_icon);
            }
        }
    }


    private int getItemHigh(View itemView) {

        int high = itemView.getMeasuredHeight();
        Log.i(myTag, "getItemHigh -> " + high);
        return high;
    }

    /**
     * 得到屏幕的高
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    public interface OnClickSendCommentCallback {
        void onSend(int position, String content);
    }
}
