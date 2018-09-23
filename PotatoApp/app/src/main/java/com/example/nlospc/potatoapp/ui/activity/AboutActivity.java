package com.example.nlospc.potatoapp.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.ui.Base.BaseActivity;
import com.example.nlospc.potatoapp.ui.Base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvback;
    @BindView(R.id.tv_search)
    TextView tvsearch;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @OnClick(R.id.tv_back)
    public void onButtonClick() {
        finish();
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initView() {
        tvTitle.setText("关于");
        tvsearch.setVisibility(View.GONE);
    }

}
