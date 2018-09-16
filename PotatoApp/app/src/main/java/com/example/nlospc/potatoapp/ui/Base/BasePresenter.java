package com.example.nlospc.potatoapp.ui.Base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<V> {
    protected Reference<V> mViewRef;

    public void attachView(V view){
        mViewRef=new WeakReference<V>(view);
    }

    protected V getView(){return mViewRef.get();}

    public void detachView(){
        if(mViewRef!=null){
            mViewRef.clear();
            mViewRef=null;
        }
    }
}
