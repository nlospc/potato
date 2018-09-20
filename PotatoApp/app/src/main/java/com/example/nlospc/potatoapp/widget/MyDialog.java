package com.example.nlospc.potatoapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.nlospc.potatoapp.R;

/**
 * Created by duanziqi on 2018/9/19
 * Description:
 */
public class MyDialog extends Dialog{
    public MyDialog(Context context, View mLayout,int mStyle) {
        super(context,mStyle);
        setContentView(mLayout);
        Window window=getWindow();
        WindowManager.LayoutParams params=window.getAttributes();
        params.gravity= Gravity.CENTER;
        window.setAttributes(params);
    }
}
