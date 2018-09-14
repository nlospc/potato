package com.example.nlospc.potatoapp.helper;


import com.lzy.okgo.convert.Converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：16/9/11
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class JasonConvert<T> implements Converter<T> {
    private Type type;
    private Class<T> klass;

    public JasonConvert(Type type) {
        this.type = type;
    }

    public JasonConvert(Class<T> clazz) {
        this.klass = clazz;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body=response.body();
       if(type ==null){
           if(klass==null){
               Type genType=getClass().getGenericSuperclass();
               type=((ParameterizedType)genType).getActualTypeArguments()[0];
           }else return parseClass(response,klass);
       }
       if(type instanceof ParameterizedType){
           return parseParameterizedType(response,(ParameterizedType)type);
       }else if (type instanceof Class){
            return parseClass(response,(Class<?>)type);
        }else{return parseType(response,type);}
    }

    private T parseClass(Response response, Class<T> klass) {
    }
}
