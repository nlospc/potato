package com.example.nlospc.potatoapp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by duanziqi on 2018/9/13
 * Description:
 */
public abstract class BasePresenter<V> {
    protected Reference<V> mViewRefr;

    protected V getView(){
        return mViewRefr.get();
    }
}
