package com.example.nlospc.potatoapp.view;


import com.example.nlospc.potatoapp.model.UserBean;

public interface LoginRegistView {
    void showProgress(String tipString);

    void hideProgress();

    void loginSuccess(UserBean user);

    void registerSuccess(UserBean user);

    void loginFail();

    void registerFail();
}
