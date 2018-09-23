package com.example.nlospc.potatoapp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.api.WanService;
import com.example.nlospc.potatoapp.helper.rxjava.RxObserver;
import com.example.nlospc.potatoapp.helper.rxjava.RxResultHelper;
import com.example.nlospc.potatoapp.helper.rxjava.RxSchedulersHelper;
import com.example.nlospc.potatoapp.model.ArticleBean;
import com.example.nlospc.potatoapp.ui.activity.WebViewActivity;
import com.example.nlospc.potatoapp.utils.UIUtils;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class CollectArticleAdapter extends BaseQuickAdapter<ArticleBean,BaseViewHolder> {
    private Context mContext;

    public CollectArticleAdapter(Context context, @Nullable List<ArticleBean> data) {
        super(R.layout.item_article, data);
        mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final ArticleBean bean) {
        holder.setText(R.id.tv_title, bean.getTitle())
                .setText(R.id.tv_author, bean.getAuthor())
                .setText(R.id.tv_time, bean.getNiceDate())
                .setText(R.id.tv_type, bean.getChapterName());

        TextView tvCollect = (TextView) holder.getView(R.id.tv_collect);
        tvCollect.setText(UIUtils.getString(R.string.ic_collect_sel));
        tvCollect.setTextColor(UIUtils.getColor(R.color.main));

        tvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unCollectArticler(holder.getLayoutPosition(), bean);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.runActivity(mContext, bean.getLink());
            }
        });

    }

    private void unCollectArticler(int position, ArticleBean bean) {
        WanService.unCollectArticle(bean.getId(),bean.getOriginId(),true)
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<String>() {
                    @Override
                    public void _onNext(String s) {
                        Toast.makeText(mContext, "取消收藏", Toast.LENGTH_SHORT).show();
                        getData().remove(position);
                        if (position != 0){
                            notifyItemRemoved(position);
                        }else{
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        Toast.makeText(mContext, "取消收藏失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
