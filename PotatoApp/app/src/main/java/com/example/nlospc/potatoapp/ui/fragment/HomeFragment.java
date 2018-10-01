package com.example.nlospc.potatoapp.ui.fragment;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.model.ArticleBean;
import com.example.nlospc.potatoapp.model.BannerBean;
import com.example.nlospc.potatoapp.ui.Base.BaseFragment;
import com.example.nlospc.potatoapp.ui.activity.WebViewActivity;
import com.example.nlospc.potatoapp.ui.adapter.ArticleListAdapter;
import com.example.nlospc.potatoapp.ui.presenter.HomePresenter;
import com.example.nlospc.potatoapp.view.HomeView;
import com.example.nlospc.potatoapp.widget.ImageLoaderManager;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class HomeFragment extends BaseFragment<HomeView, HomePresenter>
        implements HomeView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private RecyclerView rvContent;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArticleListAdapter mAdapter;
    private BGABanner mBannerView;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;

    }

    @Override
    public void onRefresh() {
        mPresenter.getBannerData();
        mPresenter.getRefrenceData();
    }

    @Override
    protected int provideContentView() {
        return R.layout.frag_home;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void showRefreshView(boolean refresh) {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(refresh));
    }

    @Override
    public void getBannerDataSuccess(List<BannerBean> data) {
        Log.d("getBannerDataSuccess","data>>>>>>>2>>>>>>>>>"+data);
        mBannerView.setData(R.layout.item_banner, data, null);
        mBannerView.setAdapter((BGABanner.Adapter<View, BannerBean>) (banner, itemView, model, position) -> {
            ImageView imageView = itemView.findViewById(R.id.image_view);
            ImageLoaderManager.LoadImage(getContext(), model.getImagePath(), imageView);
        });
        mBannerView.setDelegate((BGABanner.Delegate<View, BannerBean>) (banner, itemView, model, position) -> WebViewActivity.runActivity(getContext(), model.getUrl()));
    }
    @Override
    public void initView(View v) {
        rvContent = v.findViewById(R.id.rv_content);
        swipeRefreshLayout = v.findViewById(R.id.swipe_fresh);
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ArticleListAdapter(getContext(), null);
        rvContent.setAdapter(mAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this, rvContent);

        //添加头部轮播图布局
        View headView = View.inflate(getActivity(), R.layout.activity_banner, null);
        mBannerView = headView.findViewById(R.id.banner);
        mAdapter.addHeaderView(headView);

        onRefresh();
    }

    @Override
    public void getDataError(String message) {
        showRefreshView(false);
        Snackbar.make(rvContent, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void getRefreshDataSuccess(List<ArticleBean> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void getMoreDataSuccess(List<ArticleBean> data) {
        if (data.size() != 0) {
            mAdapter.addData(data);
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMoreData();
    }
}
