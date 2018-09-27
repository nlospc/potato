package com.example.nlospc.potatoapp.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class App extends Application {
    public static List<Activity> activities=new LinkedList<>();
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext=this.getApplicationContext();
        initOkgo();

    }
    public static Context getmContext() {
        return mContext;
    }

    private void initOkgo() {
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        OkGo.getInstance()
                .init(this)
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.NO_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(3);
    }
}
