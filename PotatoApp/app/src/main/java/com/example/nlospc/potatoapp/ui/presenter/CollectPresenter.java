package com.example.nlospc.potatoapp.ui.presenter;

import com.example.nlospc.potatoapp.api.WanService;
import com.example.nlospc.potatoapp.helper.rxjava.RxObserver;
import com.example.nlospc.potatoapp.helper.rxjava.RxResultHelper;
import com.example.nlospc.potatoapp.helper.rxjava.RxSchedulersHelper;
import com.example.nlospc.potatoapp.model.ArticleListVO;
import com.example.nlospc.potatoapp.ui.Base.BasePresenter;
import com.example.nlospc.potatoapp.view.CollectView;

public class CollectPresenter extends BasePresenter<CollectView> {
    private int mCurrentPage;

    public void getRefreshData(){
        mCurrentPage=0;
        WanService.getCollectData(mCurrentPage)
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArticleListVO>() {
                    @Override
                    public void _onError(String errorMessage) {
                        getView().onRefreshFail(errorMessage);
                    }

                    @Override
                    public void _onNext(ArticleListVO articleListVO) {
                        getView().onRefreshSuccess(articleListVO.getDatas());
                    }

                    @Override
                    public void _onComplete() {
                        getView().loadComplete();
                    }
                });
    }

    public void getMoreData(){
        mCurrentPage+=1;
        WanService.getCollectData(mCurrentPage)
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArticleListVO>() {
                    @Override
                    public void _onError(String errorMessage) {
                        getView().onLoadMoreFail(errorMessage);
                    }

                    @Override
                    public void _onNext(ArticleListVO articleListVO) {
                        getView().onRefreshSuccess(articleListVO.getDatas());
                    }
                });
    }
}
