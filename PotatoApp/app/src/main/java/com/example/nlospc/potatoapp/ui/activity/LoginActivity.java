package com.example.nlospc.potatoapp.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.app.AppConst;
import com.example.nlospc.potatoapp.model.UserBean;
import com.example.nlospc.potatoapp.ui.presenter.LoginRegistPresenter;
import com.example.nlospc.potatoapp.ui.Base.BaseActivity;
import com.example.nlospc.potatoapp.utils.PrefUtils;
import com.example.nlospc.potatoapp.view.LoginRegistView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by duanziqi on 2018/9/13
 * Description:
 */
public class LoginActivity extends BaseActivity<LoginRegistView, LoginRegistPresenter>
        implements LoginRegistView,View.OnClickListener{
    EditText etName;
    EditText etPassword;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_close:
                finish();
                break;
            case R.id.btn_login:
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "用户名/密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (etPassword.getText().toString().length() < 6 || etPassword.getText().toString().length() > 16) {
                    Toast.makeText(LoginActivity.this, "密码为6~16位", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.toLogin(etName.getText().toString(), etPassword.getText().toString());
                }
                break;
            case R.id.btn_register:
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "用户名/密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (etPassword.getText().toString().length() < 6 || etPassword.getText().toString().length() > 16) {
                    Toast.makeText(LoginActivity.this, "密码为6~16位", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.toLogin(etName.getText().toString(), etPassword.getText().toString());
                }
                break;
        }
    }


    @Override
    protected int provideContentViewId() {
        etName=findViewById(R.id.et_name);
        etPassword=findViewById(R.id.et_passwd);
        return R.layout.activity_login;
    }

    @Override
    protected LoginRegistPresenter createPresenter() {
        return new LoginRegistPresenter();
    }


    @Override
    public void showProgress(String s) {
        showWaitingDialog(s);
    }

    @Override
    public void hideProgress() {
        hideWaitingDialog();
    }

    @Override
    public void loginSuccess(UserBean user) {
        PrefUtils.setBoolean(LoginActivity.this, AppConst.IS_LOGIN_KEY, true);
        PrefUtils.setString(LoginActivity.this, AppConst.USERNAME_KEY, etName.getText().toString());
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void registerSuccess(UserBean user) {
        Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
        PrefUtils.setBoolean(LoginActivity.this, AppConst.IS_LOGIN_KEY, true);
        PrefUtils.setString(LoginActivity.this, AppConst.USERNAME_KEY, etName.getText().toString());
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void loginFail() {
        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerFail() {
        Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
    }

}
