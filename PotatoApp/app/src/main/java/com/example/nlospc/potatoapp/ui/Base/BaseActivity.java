package com.example.nlospc.potatoapp.ui.Base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.support.v7.widget.AppCompatTextView;


import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.app.App;

import com.example.nlospc.potatoapp.widget.MyDialog;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AutoLayoutActivity {
    protected T mPresenter;
    private Dialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.activities.add(this);
        initWidget();

        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        Log.d("potatoTest","id>>>>>>>>>>>>>>>"+provideContentViewId());

        setContentView(provideContentViewId());
//        ButterKnife.bind(this);
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
        ViewGroup viewGroup = getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = viewGroup.getChildAt(0);
        if (mChildView != null) {
            mChildView.setFitsSystemWindows(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected abstract int provideContentViewId();

    protected abstract T createPresenter();

    public void initWidget() {
    }

    public Dialog showWaitingDialog(String tip) {
        hideWaitingDialog();
        View view = View.inflate(this, R.layout.dialog_waiting, null);
        if (!TextUtils.isEmpty(tip)) {
            ((AppCompatTextView) view.findViewById(R.id.tvTip)).setText(tip);
        }
        mDialog = new MyDialog(this, view, R.layout.dialog_waiting);
        mDialog.show();
        mDialog.setCancelable(false);
        return mDialog;
    }

    public void hideWaitingDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

}
