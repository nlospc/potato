package com.example.nlospc.potatoapp.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.ui.Base.BaseActivity;
import com.example.nlospc.potatoapp.ui.presenter.WebViewPresenter;
import com.example.nlospc.potatoapp.utils.ActivityUtils;
import com.example.nlospc.potatoapp.utils.UIUtils;
import com.example.nlospc.potatoapp.view.CommonWebView;
import com.example.nlospc.potatoapp.widget.CustomPopWindow;
import com.example.nlospc.potatoapp.widget.IconFontTextView;
import com.example.nlospc.potatoapp.widget.WebViewFragment;

/**
 * Created by duanziqi on 2018/9/20
 * Description:
 */
public class WebViewActivity extends BaseActivity<CommonWebView, WebViewPresenter>
        implements CommonWebView, View.OnClickListener {
    public static final String WEB_URL = "web_url";
    private String mUrl;
    private CustomPopWindow mMorePopWindow;
    private WebViewFragment mWebViewFragment;
    private WebView webView;
    private ProgressBar mProgressBar;
    private TextView mTvBack;
    private TextView mTvTitle;
    private IconFontTextView mTvOther;
    private RelativeLayout mRlTopbar;
    private NestedScrollView mWebViewContainer;

    public static void runActivity(Context context, String mUrl) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WEB_URL, mUrl);
        context.startActivities(new Intent[]{intent});
    }

    @Override
    public void initView() {
        mProgressBar = findViewById(R.id.progress_bar);
        mTvBack = findViewById(R.id.tv_back);
        mTvTitle = findViewById(R.id.tv_title);
        mTvOther = findViewById(R.id.tv_other);
        mRlTopbar = findViewById(R.id.rl_toolbar_layout);
        mWebViewContainer = findViewById(R.id.webview_container);
        mTvOther.setText(UIUtils.getString(R.string.ic_more));
        mWebViewFragment = new WebViewFragment();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mWebViewFragment, R.id.webview_container);
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_webview;
    }

    @Override
    protected WebViewPresenter createPresenter() {
        return new WebViewPresenter();
    }

    @Override
    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    @Override
    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    @Override
    public void initWidget() {
        mUrl = getIntent().getStringExtra(WEB_URL);
    }


    @Override
    protected void onStart() {
        super.onStart();

        webView = mWebViewFragment.getWebView();
        mPresenter.setWebView(webView, mUrl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            } else {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_other:
                View popView = View.inflate(WebViewActivity.this, R.layout.item_webview_more, null);
                mMorePopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(popView)
                        .enableBackgroundDark(false)
                        .create()
                        .showAsDropDown(mTvOther, -430, -10);

                popView.findViewById(R.id.tv_shape).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClipboardManager cmd = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        cmd.setPrimaryClip(ClipData.newPlainText("复制链接", webView.getUrl()));
                        Snackbar.make(getWindow().getDecorView(), "已复制链接", Snackbar.LENGTH_SHORT).show();
                        mMorePopWindow.dissmiss();
                    }
                });
                popView.findViewById(R.id.tv_open_out).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webView.getUrl()));
                        startActivity(intent);
                        mMorePopWindow.dissmiss();
                    }
                });
                break;
        }
    }
}
