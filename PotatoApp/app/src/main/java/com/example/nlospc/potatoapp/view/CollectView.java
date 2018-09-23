package com.example.nlospc.potatoapp.view;

import com.example.nlospc.potatoapp.model.ArticleBean;

import java.util.List;

public interface CollectView {
    void onRefreshSuccess(List<ArticleBean> data);

    void onRefreshFail(String errorString);

    void onLoadMoreSuccess(List<ArticleBean> data);

    void onLoadMoreFail(String errorString);

    void loadComplete();
}
