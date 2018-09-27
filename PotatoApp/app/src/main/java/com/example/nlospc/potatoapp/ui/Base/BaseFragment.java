package com.example.nlospc.potatoapp.ui.Base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment<V,T extends BasePresenter<V>> extends Fragment {
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();

        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//因为之后所有的子类都要实现对应的View接口
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(provideContentView(),container,false);
        ButterKnife.bind(this,view);

        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter!=null){
            mPresenter.detachView();
        }
    }

    public void initListenter() {
    }

    public void initData() {

    }


    public void initView(View view) {

    }
    public void initWidget() {

    }

    protected abstract int provideContentView();

    protected abstract T createPresenter();
}
