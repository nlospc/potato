package com.example.nlospc.potatoapp.ui.presenter;

import com.example.nlospc.potatoapp.api.WanService;
import com.example.nlospc.potatoapp.helper.rxjava.RxObserver;
import com.example.nlospc.potatoapp.helper.rxjava.RxResultHelper;
import com.example.nlospc.potatoapp.helper.rxjava.RxSchedulersHelper;
import com.example.nlospc.potatoapp.model.ResponseData;
import com.example.nlospc.potatoapp.model.UserBean;
import com.example.nlospc.potatoapp.ui.Base.BasePresenter;
import com.example.nlospc.potatoapp.ui.view.LoginRegistView;

import io.reactivex.disposables.Disposable;

/**
 * Created by duanziqi on 2018/9/13
 * Description:
 */
public class LoginRegistPresenter extends BasePresenter<LoginRegistView> {
    public void toLogin(String username,String password){
        WanService.login(username,password)
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<UserBean>() {
                    @Override
                    public void _onSubscribe(Disposable d) {
                        getView().showProgress("正在登陆...");
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().loginFailed();
                    }

                    @Override
                    public void _onNext(UserBean userBean) {
                        getView().loginSuccess(userBean);
                    }
                    @Override
                    public void _onComplete() {
                        getView().hideProgress();
                    }

                });
    }

    public void toRegist(String userName,String password){
        WanService.regist(userName,password)
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<UserBean>() {
                    @Override
                    public void _onSubscribe(Disposable d) {
                        getView().showProgress("正在注册...");
                    }

                    @Override
                    public void _onComplete() {
                        getView().hideProgress();
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().registerFailed();
                    }

                    @Override
                    public void _onNext(UserBean userBean) {
                        getView().registerSuccess(userBean);
                    }
                });

    }
}
