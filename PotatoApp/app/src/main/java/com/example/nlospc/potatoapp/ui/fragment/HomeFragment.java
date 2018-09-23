package com.example.nlospc.potatoapp.ui.fragment;

import android.media.Image;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

public class HomeFragment extends BaseFragment<HomeView,HomePresenter>
        implements HomeView,SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.swipe_fresh)
    SwipeRefreshLayout swipeRefreshLayout;
    private ArticleListAdapter mAdapter;
    private BGABanner mBannerView;

    public static HomeFragment newInstance(){
        return new HomeFragment();
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
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(refresh);
            }
        });
    }

    @Override
    public void getBannerDataSuccess(List<BannerBean> data) {
        mBannerView.setData(R.layout.item_banner,data,null);
        mBannerView.setAdapter(new BGABanner.Adapter<View,BannerBean>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, @Nullable BannerBean model, int position) {
                ImageView imageView=itemView.findViewById(R.id.image_view);
                ImageLoaderManager.LoadImage(getContext(),model.getImagePath(),imageView);
            }
        });
        mBannerView.setDelegate(new BGABanner.Delegate<View,BannerBean>() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, @Nullable BannerBean model, int position) {
                WebViewActivity.runActivity(getContext(),model.getUrl());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView(View view) {
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ArticleListAdapter(getContext(), null);
        rvContent.setAdapter(mAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener((BaseQuickAdapter.RequestLoadMoreListener) this, rvContent);

        //添加头部轮播图布局
        View headView = View.inflate(getActivity(), R.layout.item_banner, null);
        mBannerView = headView.findViewById(R.id.banner);
        mAdapter.addHeaderView(headView);

        onRefresh();
    }

    @Override
    public void getDataError(String message) {
        showRefreshView(false);
        Snackbar.make(rvContent,message,Snackbar.LENGTH_SHORT).show();
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
}
