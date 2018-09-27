package com.example.nlospc.potatoapp.ui.activity;


import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.model.ArticleBean;
import com.example.nlospc.potatoapp.ui.Base.BaseActivity;
import com.example.nlospc.potatoapp.ui.adapter.CollectArticleAdapter;
import com.example.nlospc.potatoapp.ui.presenter.CollectPresenter;
import com.example.nlospc.potatoapp.view.CollectView;
import com.example.nlospc.potatoapp.widget.IconFontTextView;



import java.util.List;


import butterknife.BindView;
import butterknife.OnClick;

public class CollectActivity extends BaseActivity<CollectView,CollectPresenter>
        implements CollectView,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.tv_back)
    IconFontTextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_no_collect)
    TextView tvNoCollect;
    @BindView(R.id.tv_other)
    TextView tvOther;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.swipe_fresh)
    SwipeRefreshLayout swipeRefreshLayout;
    private CollectArticleAdapter mAdapter;
    @OnClick(R.id.tv_back)
    public void onViewClicked(){
        finish();
    }



    @Override
    public void initView() {
        tvTitle.setText("我的收藏");

        rvContent.setLayoutManager(new LinearLayoutManager(CollectActivity.this));
        mAdapter=new CollectArticleAdapter(CollectActivity.this,null);
        rvContent.setAdapter(mAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this,rvContent);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mPresenter.getRefreshData();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMoreData();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_collect;
    }

    @Override
    protected CollectPresenter createPresenter() {
        return new CollectPresenter();
    }

    @Override
    public void onRefreshSuccess(List<ArticleBean> data) {
        mAdapter.setNewData(data);
        tvNoCollect.setVisibility(data.size()==0? View.VISIBLE:View.GONE);
    }

    @Override
    public void onRefreshFail(String errorString) {
        showSwipeRefresh(false);
        Snackbar.make(rvContent,errorString,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreSuccess(List<ArticleBean> data) {
        if(data.size()==0){
            mAdapter.loadMoreEnd();
        }else {
            mAdapter.addData(data);
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onLoadMoreFail(String errorString) {
        showSwipeRefresh(false);
        mAdapter.loadMoreComplete();
        Snackbar.make(rvContent,errorString,Snackbar.LENGTH_SHORT).show();

    }

    private void showSwipeRefresh(boolean isRefresh) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(isRefresh);
            }
        });
    }

    @Override
    public void loadComplete() {
        showSwipeRefresh(false);
    }
}