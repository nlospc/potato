package com.example.nlospc.potatoapp.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.model.ArticleBean;
import com.example.nlospc.potatoapp.ui.Base.BaseFragment;
import com.example.nlospc.potatoapp.ui.adapter.ArticleListAdapter;
import com.example.nlospc.potatoapp.ui.presenter.TypePresenter;
import com.example.nlospc.potatoapp.view.TypeView;
import com.example.nlospc.potatoapp.widget.AutoLineFeedLayout;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TypeFragment extends BaseFragment<TypeView, TypePresenter>
        implements TypeView, BaseQuickAdapter.RequestLoadMoreListener {


    Unbinder unbinder;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.ll_tag)
    AutoLineFeedLayout llTag;
    @BindView(R.id.layout_blank)
    AutoLinearLayout layoutBlank;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    private ArticleListAdapter mAdapter;

    public static TypeFragment newInstance() {
        return new TypeFragment();
    }

    @Override
    protected int provideContentView() {
        return R.layout.frag_type;
    }

    @Override
    protected TypePresenter createPresenter() {
        return new TypePresenter(getActivity());
    }

    @Override
    public void initView(View rootView) {
        rvContent = rootView.findViewById(R.id.rv_content);
        tabLayout = rootView.findViewById(R.id.tabLayout);
        llTag = rootView.findViewById(R.id.ll_tag);

        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ArticleListAdapter(getContext(), null);
        rvContent.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, rvContent);

        mPresenter.getTagData();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMoreData();
    }

    @Override
    public TabLayout getTableLayout() {
        return tabLayout;
    }

    @Override
    public AutoLineFeedLayout getTagLayout() {
        return llTag;
    }


    @Override
    public ArticleListAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void getDataError(String message) {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
