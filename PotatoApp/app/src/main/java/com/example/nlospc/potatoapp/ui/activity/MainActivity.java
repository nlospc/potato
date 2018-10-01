package com.example.nlospc.potatoapp.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.ui.Base.BaseFragment;
import com.example.nlospc.potatoapp.ui.adapter.FragPagerAdapter;
import com.example.nlospc.potatoapp.ui.fragment.HomeFragment;
import com.example.nlospc.potatoapp.ui.fragment.TypeFragment;
import com.example.nlospc.potatoapp.ui.fragment.UserFragment;
import com.example.nlospc.potatoapp.ui.presenter.WebViewPresenter;
import com.example.nlospc.potatoapp.ui.Base.BaseActivity;
import com.example.nlospc.potatoapp.view.CommonWebView;
import com.example.nlospc.potatoapp.widget.IconFontTextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;

public class MainActivity extends BaseActivity{
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.if_home)
    IconFontTextView ifHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.if_type)
    IconFontTextView ifType;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.if_user)
    IconFontTextView ifUser;
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.tv_search)
    IconFontTextView tvSearch;
    private List<BaseFragment> mFragments=new ArrayList<BaseFragment>();
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected WebViewPresenter createPresenter() {
        return null;
    }

    @Override
    public void initView() {
        setTabColor(ifHome,tvHome);
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(TypeFragment.newInstance());
        mFragments.add(UserFragment.newInstance());

    }

    private void setTabColor(IconFontTextView ifHome, TextView tvHome) {

    }
}
