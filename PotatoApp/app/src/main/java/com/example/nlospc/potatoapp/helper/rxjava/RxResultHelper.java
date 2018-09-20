package com.example.nlospc.potatoapp.helper.rxjava;

import com.example.nlospc.potatoapp.model.ResponseData;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by duanziqi on 2018/9/19
 * Description:
 */
public class RxResultHelper {

    private static final int RESPONSE_SUCCESS_CODE = 0; //大部分为200
    private static final int RESPONSE_ERROR_CODE = -1;



    public static <T> ObservableTransformer<ResponseData<T>, T> handleResult() {
        return tObservable -> tObservable.flatMap(
                (Function<ResponseData<T>, Observable<T>>) tResponseData -> {
                    //可以相应更改
                    if (tResponseData.getErrorCode() == RESPONSE_SUCCESS_CODE) {
                        return Observable.just(tResponseData.getData());
                    } else if (tResponseData.getErrorCode() == RESPONSE_ERROR_CODE) {
                        return Observable.error(new Exception(tResponseData.getErrorMsg()));
                    } else {
                        return Observable.empty();
                    }
                }
        );
    }

}
