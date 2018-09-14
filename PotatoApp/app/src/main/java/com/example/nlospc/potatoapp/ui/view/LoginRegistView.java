package com.example.nlospc.potatoapp.ui.view;

import com.example.nlospc.potatoapp.model.UserBean;

/**
 * Created by duanziqi on 2018/9/13
 * Description:
 */
public interface LoginRegistView {
    void showProgress(String s);
    void hideProgress();
    void loginSuccess(UserBean user);
    void loginFailed();
    void registerSuccess(UserBean user);
    void registerFailed();

}
