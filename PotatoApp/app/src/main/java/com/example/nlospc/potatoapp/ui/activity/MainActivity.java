package com.example.nlospc.potatoapp.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.nlospc.potatoapp.R;
<<<<<<< HEAD
import com.example.nlospc.potatoapp.ui.Base.BaseFragment;
=======
>>>>>>> a154429ecfd134ec26a332df4b0b7b1df5c4f7a1
import com.example.nlospc.potatoapp.ui.adapter.FragPagerAdapter;
import com.example.nlospc.potatoapp.ui.fragment.HomeFragment;
import com.example.nlospc.potatoapp.ui.fragment.TypeFragment;
import com.example.nlospc.potatoapp.ui.fragment.UserFragment;
import com.example.nlospc.potatoapp.ui.presenter.WebViewPresenter;
import com.example.nlospc.potatoapp.ui.Base.BaseActivity;
import com.example.nlospc.potatoapp.utils.UIUtils;
import com.example.nlospc.potatoapp.view.CommonWebView;
import com.example.nlospc.potatoapp.widget.IconFontTextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity{
//    @BindView(R.id.view_pager)
    ViewPager mViewPager;
//    @BindView(R.id.if_home)
    IconFontTextView ifHome;
//    @BindView(R.id.tv_home)
    TextView tvHome;
//    @BindView(R.id.ll_home)
    LinearLayout llHome;
//    @BindView(R.id.if_type)
    IconFontTextView ifType;
//    @BindView(R.id.tv_type)
    TextView tvType;
//    @BindView(R.id.ll_type)
    LinearLayout llType;
//    @BindView(R.id.if_user)
    IconFontTextView ifUser;
//    @BindView(R.id.tv_user)
    TextView tvUser;
//    @BindView(R.id.tv_search)
    IconFontTextView tvSearch;
<<<<<<< HEAD
    private List<BaseFragment> mFragments=new ArrayList<BaseFragment>();
=======
    private List<Fragment> mFragments=new ArrayList<>();

>>>>>>> a154429ecfd134ec26a332df4b0b7b1df5c4f7a1
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
        mViewPager=findViewById(R.id.view_pager);
        ifHome=findViewById(R.id.if_home);
        tvHome=findViewById(R.id.tv_home);
        ifType=findViewById(R.id.if_type);
        ifUser=findViewById(R.id.if_user);
        tvType=findViewById(R.id.tv_type);
        tvUser=findViewById(R.id.tv_user);
        tvSearch=findViewById(R.id.tv_search);
        setTabColor(ifHome,tvHome);
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(TypeFragment.newInstance());
        mFragments.add(UserFragment.newInstance());
<<<<<<< HEAD

=======
        mViewPager.setAdapter(new FragPagerAdapter(getSupportFragmentManager(),mFragments));
        mViewPager.setCurrentItem(0,false);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        setTabColor(ifHome,tvHome);
                        break;
                    case 1:
                        setTabColor(ifType,tvType);
                        break;
                    case 2:
                        setTabColor(ifUser,tvUser);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
>>>>>>> a154429ecfd134ec26a332df4b0b7b1df5c4f7a1
    }

    private void setTabColor(IconFontTextView icon, TextView textView) {
        Log.d("Test","ifHome>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ ifHome);
        Log.d("Test","tvHome>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ tvHome);
        Log.d("Test","ifType>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ ifType);
        ifHome.setTextColor(UIUtils.getColor(R.color.tab_nor_color));
        tvHome.setTextColor(UIUtils.getColor(R.color.tab_nor_color));
        ifType.setTextColor(UIUtils.getColor(R.color.tab_nor_color));
        tvType.setTextColor(UIUtils.getColor(R.color.tab_nor_color));
        ifUser.setTextColor(UIUtils.getColor(R.color.tab_nor_color));
        tvUser.setTextColor(UIUtils.getColor(R.color.tab_nor_color));
        icon.setTextColor(UIUtils.getColor(R.color.tab_sel_color));
        textView.setTextColor(UIUtils.getColor(R.color.tab_sel_color));
    }

    @OnClick({R.id.ll_home,R.id.ll_hot_key,R.id.ll_type,R.id.ll_user,R.id.tv_search})
    public void onClicked(View v){
        switch (v.getId()){
            case R.id.ll_home:
                mViewPager.setCurrentItem(0);
                setTabColor(ifHome,tvHome);
                break;
            case R.id.ll_hot_key:
                break;
            case R.id.ll_type:
                mViewPager.setCurrentItem(1);
                setTabColor(ifType,tvType);
                break;
            case R.id.ll_user:
                mViewPager.setCurrentItem(2);
                setTabColor(ifUser,tvUser);
                break;
            case R.id.tv_search:
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
                break;
        }
    }
}
