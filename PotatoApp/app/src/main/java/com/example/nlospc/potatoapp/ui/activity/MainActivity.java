package com.example.nlospc.potatoapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.ui.Base.BaseActivity;
import com.example.nlospc.potatoapp.ui.Base.BasePresenter;
import com.example.nlospc.potatoapp.ui.adapter.FragPagerAdapter;
import com.example.nlospc.potatoapp.ui.fragment.HomeFragment;
import com.example.nlospc.potatoapp.ui.fragment.TypeFragment;
import com.example.nlospc.potatoapp.ui.fragment.UserFragment;
import com.example.nlospc.potatoapp.utils.UIUtils;
import com.example.nlospc.potatoapp.widget.IconFontTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private IconFontTextView ifHome;
    private TextView tvHome;
    private IconFontTextView ifType;
    private TextView tvType;
    private IconFontTextView ifUser;
    private TextView tvUser;
    private List<Fragment> mFragments = new ArrayList<>();
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initView() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main, null);
        mViewPager = view.findViewById(R.id.view_pager);
        ifHome = view.findViewById(R.id.if_home);
        tvHome = view.findViewById(R.id.tv_home);
        ifType = view.findViewById(R.id.if_type);
        ifUser = view.findViewById(R.id.if_user);
        tvType = view.findViewById(R.id.tv_type);
        tvUser = view.findViewById(R.id.tv_user);
        setTabColor(ifHome, tvHome);
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(TypeFragment.newInstance());
        mFragments.add(UserFragment.newInstance());
        mViewPager.setAdapter(new FragPagerAdapter(getSupportFragmentManager(), mFragments));
        mViewPager.setCurrentItem(0, false);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setTabColor(ifHome, tvHome);
                        break;
                    case 1:
                        setTabColor(ifType, tvType);
                        break;
                    case 2:
                        setTabColor(ifUser, tvUser);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void setTabColor(IconFontTextView icon, TextView textView) {
        Log.d("Test", "ifHome>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + ifHome);
        Log.d("Test", "tvHome>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + tvHome);
        Log.d("Test", "ifType>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + ifType);
        ifHome.setTextColor(UIUtils.getColor(R.color.tab_nor_color));
        tvHome.setTextColor(UIUtils.getColor(R.color.tab_nor_color));
        ifType.setTextColor(UIUtils.getColor(R.color.tab_nor_color));
        tvType.setTextColor(UIUtils.getColor(R.color.tab_nor_color));
        ifUser.setTextColor(UIUtils.getColor(R.color.tab_nor_color));
        tvUser.setTextColor(UIUtils.getColor(R.color.tab_nor_color));
        icon.setTextColor(UIUtils.getColor(R.color.tab_sel_color));
        textView.setTextColor(UIUtils.getColor(R.color.tab_sel_color));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                mViewPager.setCurrentItem(0);
                setTabColor(ifHome, tvHome);
                break;
            case R.id.ll_hot_key:
                break;
            case R.id.ll_type:
                mViewPager.setCurrentItem(1);
                setTabColor(ifType, tvType);
                break;
            case R.id.ll_user:
                mViewPager.setCurrentItem(2);
                setTabColor(ifUser, tvUser);
                break;
            case R.id.tv_search:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
        }
    }
}
