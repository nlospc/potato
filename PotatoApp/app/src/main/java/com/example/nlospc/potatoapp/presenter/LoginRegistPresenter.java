package com.example.nlospc.potatoapp.presenter;

import com.example.nlospc.potatoapp.api.WanService;
import com.example.nlospc.potatoapp.base.BasePresenter;
import com.example.nlospc.potatoapp.ui.view.LoginRegistView;

/**
 * Created by duanziqi on 2018/9/13
 * Description:
 */
public class LoginRegistPresenter extends BasePresenter<LoginRegistView> {
    public void toLogin(String username,String password){
        WanService.login(username,password)
    }
}
