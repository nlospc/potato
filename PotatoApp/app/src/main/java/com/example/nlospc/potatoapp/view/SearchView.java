package com.example.nlospc.potatoapp.view;

import com.example.nlospc.potatoapp.model.ArticleBean;
import com.example.nlospc.potatoapp.model.HotWordBean;

import java.util.List;

public interface SearchView {
    void getHotKeySuccess(List<HotWordBean> data);
    void getHotKeyFail(String message);

    void searchDataSuccess(List<ArticleBean> data);

    void searchDataFail(String message);

    void loadMoreDataSuccess(List<ArticleBean> data);

    void loadMoreDataFail(String message);
}
