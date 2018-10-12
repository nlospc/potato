package com.example.nlospc.potatoapp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by duanziqi on 2018/9/26
 * Description:
 */
public class FragPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragment;
    public FragPagerAdapter(FragmentManager fm,List<Fragment> fragment) {
        super(fm);
        mFragment=fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }
}
