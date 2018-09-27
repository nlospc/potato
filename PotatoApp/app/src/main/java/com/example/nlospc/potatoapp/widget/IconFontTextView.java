package com.example.nlospc.potatoapp.widget;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;



/**
 * 适用于fontawesome的字体TextView
 * Created by Sogrey on 2017/1/17.
 */

public class IconFontTextView extends AppCompatTextView {
    private Context mContext;

    public IconFontTextView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public IconFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        Typeface iconfont = Typeface.createFromAsset(mContext.getAssets(), "iconfont.ttf");
        setTypeface(iconfont);
    }
}