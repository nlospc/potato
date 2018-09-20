package com.example.nlospc.potatoapp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.api.WanService;
import com.example.nlospc.potatoapp.app.AppConst;
import com.example.nlospc.potatoapp.helper.rxjava.RxObserver;
import com.example.nlospc.potatoapp.helper.rxjava.RxResultHelper;
import com.example.nlospc.potatoapp.helper.rxjava.RxSchedulersHelper;
import com.example.nlospc.potatoapp.model.ArticleBean;
import com.example.nlospc.potatoapp.model.ResponseData;
import com.example.nlospc.potatoapp.ui.activity.WebViewActivity;
import com.example.nlospc.potatoapp.utils.PrefUtils;
import com.example.nlospc.potatoapp.utils.UIUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ArticleListAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {

    private Context mContext;


    public ArticleListAdapter(Context context, @Nullable List<ArticleBean> data) {
        super(R.layout.item_article, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ArticleBean item) {
        helper.setText(R.id.tv_title, Html.fromHtml(item.getTitle()))
                .setText(R.id.tv_author, item.getAuthor())
                .setText(R.id.tv_time, item.getNiceDate())
                .setText(R.id.tv_type, item.getChapterName());
        final TextView tvCollect = helper.getView(R.id.tv_collect);
        if (item.isCollect()) {
            tvCollect.setText(UIUtils.getString(R.string.ic_collect_sel));
            tvCollect.setTextColor(UIUtils.getColor(R.color.main));
        } else {
            tvCollect.setText(UIUtils.getString(R.string.ic_collect_nor));
            tvCollect.setTextColor(UIUtils.getColor(R.color.text3));
        }
        tvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectArticle(tvCollect, item);
            }
        });
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.runActivity(mContext, item.getLink());
            }
        });
    }

    private void collectArticle(TextView tvCollect, ArticleBean item) {
        if (PrefUtils.isExist(mContext, AppConst.IS_LOGIN_KEY, false) == false){
            Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
        return; }
        if (item.isCollect()){
            unCollectArticler(item,tvCollect);
        }else {
            collectArticler(item,tvCollect);
        }
    }

    private void collectArticler(ArticleBean item, TextView tvCollect) {
        WanService.collectArticle(item.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseData<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseData<String> stringResponseData) {
                        if(stringResponseData.getErrorCode()==0){
                            Toast.makeText(mContext, "收藏成功", Toast.LENGTH_SHORT).show();
                            item.setCollect(true);
                            tvCollect.setText(UIUtils.getString(R.string.ic_collect_sel));
                            tvCollect.setTextColor(UIUtils.getColor(R.color.main));
                        }else {
                            Toast.makeText(mContext,stringResponseData.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mContext, "收藏失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void unCollectArticler(ArticleBean item, TextView tvCollect) {
        WanService.unCollectArticle(item.getId(),item.getOriginId(),false)
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<String>() {
                    @Override
                    public void _onError(String errorMessage) {
                        Toast.makeText(mContext, "取消收藏失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void _onNext(String s) {
                        Toast.makeText(mContext, "取消收藏", Toast.LENGTH_SHORT).show();
                        item.setCollect(false);
                        tvCollect.setText(UIUtils.getString(R.string.ic_collect_nor));
                        tvCollect.setTextColor(UIUtils.getColor(R.color.text3));

                    }
                });

    }
}
