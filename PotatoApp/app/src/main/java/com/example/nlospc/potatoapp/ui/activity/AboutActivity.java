package com.example.nlospc.potatoapp.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.ui.Base.BaseActivity;
import com.example.nlospc.potatoapp.ui.Base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvSearch;
    private TextView tvTitle;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_back)
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

        tvSearch.setVisibility(View.GONE);
    }

}
