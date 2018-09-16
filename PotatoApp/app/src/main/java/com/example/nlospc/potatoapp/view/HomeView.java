package com.example.nlospc.potatoapp.view;

import com.example.nlospc.potatoapp.model.ArticleBean;
import com.example.nlospc.potatoapp.model.BannerBean;

import java.util.List;

public interface HomeView {
    void showRefreshView(boolean refresh);

    void getBannerDataSuccess(List<BannerBean> data);

    void getDataError(String message);

    void getRefreshDataSuccess(List<ArticleBean> data);

    void getMoreDataSuccess(List<ArticleBean> data);
}
