package com.example.nlospc.potatoapp.ui.presenter;

import com.example.nlospc.potatoapp.api.WanService;
import com.example.nlospc.potatoapp.helper.rxjava.RxObserver;
import com.example.nlospc.potatoapp.helper.rxjava.RxResultHelper;
import com.example.nlospc.potatoapp.helper.rxjava.RxSchedulersHelper;
import com.example.nlospc.potatoapp.model.ArticleListVO;
import com.example.nlospc.potatoapp.model.BannerBean;
import com.example.nlospc.potatoapp.ui.Base.BasePresenter;
import com.example.nlospc.potatoapp.view.HomeView;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class HomePresenter extends BasePresenter<HomeView> {
    private int mCurrentPage;
    public void getRefrenceData(){
        mCurrentPage=0;
        WanService.getHomeData(mCurrentPage)
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArticleListVO>() {
                    @Override
                    public void _onError(String errorMessage) {
                        getView().getDataError(errorMessage);
                    }

                    @Override
                    public void _onNext(ArticleListVO articleListVO) {
                        getView().getRefreshDataSuccess(articleListVO.getDatas());
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        getView().showRefreshView(true);
                    }

                    @Override
                    public void onComplete() {
                        getView().showRefreshView(false);
                    }
                });
    }

    public void getMoreData() {
        mCurrentPage = mCurrentPage + 1;
        WanService.getHomeData(mCurrentPage)
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArticleListVO>() {
                    @Override
                    public void _onNext(ArticleListVO articleListVO) {
                        getView().getMoreDataSuccess(articleListVO.getDatas());
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getDataError(errorMessage);
                    }
                });
    }

    //获取轮播图数据
    public void getBannerData() {

        WanService.getBannerData()
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<List<BannerBean>>() {
                    @Override
                    public void _onNext(List<BannerBean> bannerBeans) {
                        getView().getBannerDataSuccess(bannerBeans);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getDataError(errorMessage);
                    }
                });
    }
}
