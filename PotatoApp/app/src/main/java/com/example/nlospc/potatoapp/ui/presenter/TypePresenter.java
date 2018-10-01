package com.example.nlospc.potatoapp.ui.presenter;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.api.WanService;
import com.example.nlospc.potatoapp.helper.rxjava.RxObserver;
import com.example.nlospc.potatoapp.helper.rxjava.RxResultHelper;
import com.example.nlospc.potatoapp.helper.rxjava.RxSchedulersHelper;
import com.example.nlospc.potatoapp.model.ArticleListVO;
import com.example.nlospc.potatoapp.model.TypeTagVO;
import com.example.nlospc.potatoapp.ui.Base.BasePresenter;
import com.example.nlospc.potatoapp.ui.adapter.ArticleListAdapter;
import com.example.nlospc.potatoapp.utils.UIUtils;
import com.example.nlospc.potatoapp.view.TypeView;
import com.example.nlospc.potatoapp.widget.AutoLineFeedLayout;

import java.util.ArrayList;
import java.util.List;

public class TypePresenter extends BasePresenter<TypeView> {
    private FragmentActivity mActivity;
    private TypeView mTypeView;
    private int mCurrentPage;
    private List<TypeTagVO> mTagDatas;
    private ArticleListAdapter mAdapter;
    private int mId;
    private int mTabSelect; //标记选中的Tab标签
    private int mTagSelect; //标记选中的Tag标签，用户设置背景色
    private List<TextView> tagTvs;
    private AutoLineFeedLayout llTag;
    public TypePresenter(FragmentActivity activity) {
        this.mActivity = activity;
    }

    public void getTagData(){
        mTypeView=getView();
        WanService.getTypeTagData()
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<List<TypeTagVO>>() {
                    @Override
                    public void _onError(String errorMessage) {

                    }

                    @Override
                    public void _onNext(List<TypeTagVO> typeTagVOS) {
                        mTagDatas=typeTagVOS;
                        setTabUI();
                        Log.d("TestinPresenter","mTagDatas>>>>>>>>>>>>>>>>"+mTagDatas.get(0).getChildren());
                        mTabSelect = 0;
                        getServerData(mTagDatas.get(0).getId());
                    }
                });
    }

    private void setTabUI() {
        TabLayout tabLayout=mTypeView.getTableLayout();
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for(TypeTagVO bean:mTagDatas){
            tabLayout.addTab(tabLayout.newTab().setText(bean.getName()));
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTagUI(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (llTag != null && llTag.getVisibility() == View.VISIBLE) {
                    llTag.setVisibility(View.GONE);
                } else {
                    setTagUI(tab.getPosition());
                }
            }
        });


    }

    private void setTagUI(int position) {
        llTag = mTypeView.getTagLayout();
        llTag.setVisibility(View.VISIBLE);
        llTag.removeAllViews();
        if (tagTvs == null) {
            tagTvs = new ArrayList<>();
        } else {
            tagTvs.clear();
        }
        for (int i = 0; i < mTagDatas.get(position).getChildren().size(); i++) {
            View view = LinearLayout.inflate(mActivity, R.layout.item_tag_layout, null);
            TextView tvItem = (TextView) view.findViewById(R.id.tv_item);
            tvItem.setText(mTagDatas.get(position).getChildren().get(i).getName());
            llTag.addView(view);
            tagTvs.add(tvItem);

            int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击某个tag按钮
                    mTabSelect = position;
                    mTagSelect = finalI;
                    tvItem.setTextColor(UIUtils.getColor(R.color.white));
                    tvItem.setBackgroundResource(R.drawable.shape_tag_sel);
                    mId = mTagDatas.get(position).getChildren().get(finalI).getId();
                    getServerData(mId);
                }
            });
        }

        //设置选中的tag背景
        for (int j = 0; j < tagTvs.size(); j++) {
            if (position == mTabSelect && j == mTagSelect) {
                tagTvs.get(j).setTextColor(UIUtils.getColor(R.color.white));
                tagTvs.get(j).setBackgroundResource(R.drawable.shape_tag_sel);
            } else {
                tagTvs.get(j).setTextColor(UIUtils.getColor(R.color.text0));
                tagTvs.get(j).setBackgroundResource(R.drawable.shape_tag_nor);
            }
        }


    }

    private void getServerData(int mId) {
        mCurrentPage = 0; //从第0页开始
        mAdapter = mTypeView.getAdapter();
        WanService.getTypeDataById(mCurrentPage, mId)
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArticleListVO>() {
                    @Override
                    public void _onNext(ArticleListVO articleListVO) {
                        if (articleListVO.getDatas() != null) {
                            getView().getRefreshDataSuccess(articleListVO.getDatas());
                            mTypeView.getTagLayout().setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getDataError(errorMessage);
                    }
                });
    }
    //加载下一页
    public void getMoreData(){
        mCurrentPage = mCurrentPage + 1;
        WanService.getTypeDataById(mCurrentPage, mId)
                .compose(RxSchedulersHelper.mainio())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArticleListVO>() {
                    @Override
                    public void _onNext(ArticleListVO articleListVO) {
                        getView().getMoreDataSuccess(articleListVO.getDatas());
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getDataError(errorMessage);
                    }
                });

    }
}
