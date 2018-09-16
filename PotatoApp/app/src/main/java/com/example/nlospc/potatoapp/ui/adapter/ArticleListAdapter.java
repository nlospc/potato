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
import com.example.nlospc.potatoapp.helper.RxSchedulersHelper;
import com.example.nlospc.potatoapp.model.ArticleBean;
import com.example.nlospc.potatoapp.model.ResponseData;
import com.example.nlospc.potatoapp.utils.PrefUtils;
import com.example.nlospc.potatoapp.utils.UIUtils;

import java.util.List;

import javax.xml.transform.Transformer;

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
                webViewActivity.runActivity(mContext, item.getLink());
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

    private void unCollectArticler(ArticleBean item, TextView tvCollect) {
        WanService.unCollectArticle(item.getId(),item.getOriginId(),false)
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResult)

    }
}
