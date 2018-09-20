package com.example.nlospc.potatoapp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.presenter.WebViewPresenter;
import com.example.nlospc.potatoapp.ui.Base.BaseActivity;
import com.example.nlospc.potatoapp.ui.Base.BasePresenter;
import com.example.nlospc.potatoapp.view.CommonWebView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<CommonWebView,WebViewPresenter> {
    @BindView(R.id.view_pager)
    ViewPager mViewPager;








    private List<Fragment> mFragment =new ArrayList<>();
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected WebViewPresenter createPresenter() {
        return null;
    }


}
