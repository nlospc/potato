package com.example.nlospc.potatoapp.helper.rxjava;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by duanziqi on 2018/9/19
 * Description:
 */
public abstract class RxObserver<T> implements Observer<T>{
    @Override
    public void onSubscribe(Disposable d) {
        _onSubscribe(d);
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        _onError(e.getMessage());
    }

    @Override
    public void onComplete() {
        _onComplete();
    }

    public void _onComplete() {
    }

    public abstract void _onError(String errorMessage);

    public abstract void _onNext(T t);

    public void _onSubscribe(Disposable d){}
}
