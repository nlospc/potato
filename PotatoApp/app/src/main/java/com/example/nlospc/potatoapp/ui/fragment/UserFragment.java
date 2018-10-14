package com.example.nlospc.potatoapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.app.AppConst;
import com.example.nlospc.potatoapp.ui.Base.BaseFragment;
import com.example.nlospc.potatoapp.ui.Base.BasePresenter;
import com.example.nlospc.potatoapp.ui.activity.AboutActivity;
import com.example.nlospc.potatoapp.ui.activity.CollectActivity;
import com.example.nlospc.potatoapp.ui.activity.LoginActivity;
import com.example.nlospc.potatoapp.utils.PrefUtils;
import com.example.nlospc.potatoapp.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by duanziqi on 2018/9/25
 * Description:
 */
public class UserFragment extends BaseFragment{


    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tv_name)
    AppCompatTextView tvName;
    @BindView(R.id.cv_collect)
    CardView cvCollect;
    @BindView(R.id.cv_about)
    CardView cvAbout;
    @BindView(R.id.tv_logou)
    AppCompatTextView tvLogou;
    @BindView(R.id.cv_logou)
    CardView cvLogou;
    @BindView(R.id.ll_user_info)
    LinearLayout llUserInfo;
    Unbinder unbinder;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentView() {
        return R.layout.frag_user;
    }


    @Override
    public void initView(View rootView) {
        tvLogou = rootView.findViewById(R.id.tv_logou);
        tvName = rootView.findViewById(R.id.tv_name);
        if (PrefUtils.getBoolean(getContext(), AppConst.IS_LOGIN_KEY, false) == false) {
            tvLogou.setText("点击登录");
            tvName.setText("暂未登录");
        } else {
            tvName.setText(PrefUtils.getString(getContext(), AppConst.USERNAME_KEY, "暂未登录"));
            tvLogou.setText("退出登录");
        }
    }

    @OnClick({R.id.cv_collect,R.id.cv_about,R.id.cv_logou})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_collect:
                if (PrefUtils.getBoolean(getContext(), AppConst.IS_LOGIN_KEY, false) == false) {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getActivity(), CollectActivity.class));
                }
                break;
            case R.id.cv_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.cv_logou:
                if (PrefUtils.getBoolean(getContext(), AppConst.IS_LOGIN_KEY, false) == false) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    //注销
                    PrefUtils.setBoolean(getContext(), AppConst.IS_LOGIN_KEY, false);
                    Toast.makeText(getContext(), "已注销", Toast.LENGTH_SHORT).show();
                    tvLogou.setText("点击登录");
                    tvName.setText("暂未登录");
                }
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
