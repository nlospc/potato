package com.example.nlospc.potatoapp.ui.presenter;

import com.example.nlospc.potatoapp.api.WanService;
import com.example.nlospc.potatoapp.helper.rxjava.RxObserver;
import com.example.nlospc.potatoapp.helper.rxjava.RxResultHelper;
import com.example.nlospc.potatoapp.helper.rxjava.RxSchedulersHelper;
import com.example.nlospc.potatoapp.model.ArticleListVO;
import com.example.nlospc.potatoapp.model.HotWordBean;
import com.example.nlospc.potatoapp.ui.Base.BasePresenter;
import com.example.nlospc.potatoapp.view.SearchView;

import java.util.List;

public class SearchPresenter extends BasePresenter<SearchView> {
    private int mCurrentPage;

    public void getHotKeyData() {
        WanService.getHotKey()
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<List<HotWordBean>>() {
                    @Override
                    public void _onError(String errorMessage) {
                        getView().getHotKeyFail(errorMessage);
                    }

                    @Override
                    public void _onNext(List<HotWordBean> hotWordBeans) {

                        getView().getHotKeySuccess(hotWordBeans);
                    }
                });
    }

    public void searchData(String key) {
        mCurrentPage = 0;
        WanService.searchArticle(mCurrentPage, key)
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArticleListVO>() {
                    @Override
                    public void _onError(String errorMessage) {
                        getView().searchDataFail(errorMessage);
                    }

                    @Override
                    public void _onNext(ArticleListVO articleListVO) {
                        getView().searchDataSuccess(articleListVO.getDatas());
                    }
                });
    }

    public void getMoreData(String key) {
        mCurrentPage += 1;
        WanService.searchArticle(mCurrentPage, key)
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArticleListVO>() {
                    @Override
                    public void _onError(String errorMessage) {
                        getView().loadMoreDataFail(errorMessage);
                    }

                    @Override
                    public void _onNext(ArticleListVO articleListVO) {
                        getView().loadMoreDataSuccess(articleListVO.getDatas());

                    }
                });
    }
}
