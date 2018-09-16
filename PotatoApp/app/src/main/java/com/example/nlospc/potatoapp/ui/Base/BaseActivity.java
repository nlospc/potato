package com.example.nlospc.potatoapp.ui.Base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.nlospc.potatoapp.app.App;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<V,T extends BasePresenter<V>> extends AutoLayoutActivity {
    protected T mPresenter;
    private Dialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.activities.add(this);
        initWidget();

        mPresenter=createPresenter();
        if(mPresenter!=null){
            mPresenter.attachView((V)this);
        }
        setContentView(provideContentViewId());
        ButterKnife.bind(this);
        excuteStatesBar();

        initView();
        initData();
        initListener();
    }

    public void initListener() {

    }

    public void initData() {

    }

    public void initView() {
    }

    private void excuteStatesBar() {
        ViewGroup viewGroup=getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView=viewGroup.getChildAt(0);
        if(mChildView!=null){
            mChildView.setFitsSystemWindows(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.detachView();
        }
    }

    protected abstract int provideContentViewId();

    protected abstract T createPresenter();

    public void initWidget(){}
}
