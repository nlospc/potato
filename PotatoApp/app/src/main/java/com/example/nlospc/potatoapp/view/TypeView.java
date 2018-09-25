package com.example.nlospc.potatoapp.view;

import android.support.design.widget.TabLayout;

import com.example.nlospc.potatoapp.model.ArticleBean;
import com.example.nlospc.potatoapp.ui.adapter.ArticleListAdapter;
import com.example.nlospc.potatoapp.widget.AutoLineFeedLayout;

import java.util.List;

public interface TypeView {
    TabLayout getTableLayout();
    AutoLineFeedLayout getTagLayout();
    ArticleListAdapter getAdapter();

    void getDataError(String message);
    void getRefreshDataSuccess(List<ArticleBean> data);
    void getMoreDataSuccess(List<ArticleBean> data);

}
