package com.example.nlospc.potatoapp.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class IconFontTextView extends android.support.v7.widget.AppCompatTextView {
    private Context mContext;

    public IconFontTextView(Context context) {
        super(context);
        mContext=context;
        initView();

    }

    public IconFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        Typeface iconfont=Typeface.createFromAsset(mContext.getAssets(),"iconfont.ttf");
        setTypeface(iconfont);
    }
}
