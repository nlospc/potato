package com.example.nlospc.potatoapp.ui.view;

import com.example.nlospc.potatoapp.model.ArticleBean;
import com.example.nlospc.potatoapp.model.BannerBean;

import java.util.List;

/**
 * Created by duanziqi on 2018/9/19
 * Description:
 */
public interface HomeView {
    void showRefreshView(Boolean refresh);
    void getBannerDataSuccess(List<BannerBean> data);
    void getError(String errorMsg);
    void getRefreshDataSuccess(List<ArticleBean> data);
    void getMoreDataSuccess(List<ArticleBean> data);
}
