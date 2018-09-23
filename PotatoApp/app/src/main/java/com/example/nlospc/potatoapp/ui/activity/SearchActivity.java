package com.example.nlospc.potatoapp.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.nlospc.potatoapp.R;
import com.example.nlospc.potatoapp.model.ArticleBean;
import com.example.nlospc.potatoapp.model.HotWordBean;
import com.example.nlospc.potatoapp.ui.Base.BaseActivity;
import com.example.nlospc.potatoapp.ui.adapter.ArticleListAdapter;
import com.example.nlospc.potatoapp.ui.presenter.SearchPresenter;
import com.example.nlospc.potatoapp.view.SearchView;
import com.example.nlospc.potatoapp.widget.AutoLineFeedLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchView,SearchPresenter>
        implements SearchView,BaseQuickAdapter.RequestLoadMoreListener {
   @BindView(R.id.et_search)
    EditText etSearch;
   @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
   @BindView(R.id.tv_back)
    TextView tvBack;
   @BindView(R.id.tv_clean_input)
   TextView tvCleanInput;
   @BindView(R.id.layout_hot_key)
    AutoLineFeedLayout layoutHotKey;
   @BindView(R.id.ll_hot_key)
    LinearLayout llHotKey;
   @BindView(R.id.rv_content)
    RecyclerView rvContent;
   private ArticleListAdapter mArticleAdapter;
    @OnClick({R.id.tv_clean_input,R.id.tv_back})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.tv_clean_input:
                etSearch.setText(" ");
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }


    @Override
    public void initListener() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPresenter.searchData(etSearch.getText().toString());
            }
        });
    }
    @Override
    public void initView() {

        rvContent.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        mArticleAdapter = new ArticleListAdapter(SearchActivity.this, null);
        rvContent.setAdapter(mArticleAdapter);

        mArticleAdapter.setOnLoadMoreListener(this, rvContent);

        mPresenter.getHotKeyData();

    }
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    public void onLoadMoreRequested() {
        String keyword=etSearch.getText().toString();
        if(!TextUtils.isEmpty(keyword)){
            mPresenter.getMoreData(etSearch.getText().toString());
        }
    }

    @Override
    public void getHotKeySuccess(List<HotWordBean> data) {
       layoutHotKey.removeAllViews();
       for (int i=0;i<data.size();i++){
           View view=LinearLayout.inflate(SearchActivity.this,R.layout.item_search,null);
           TextView tvHotKey=findViewById(R.id.text_item);
           tvHotKey.setText(data.get(i).getName());
           layoutHotKey.addView(tvHotKey);
           int finalone=i;
           view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   etSearch.setText(data.get(finalone).getName());

                   CharSequence charSequence=etSearch.getText();

                   if(charSequence instanceof Spannable){
                       Spannable spanText= (Spannable) charSequence;
                       Selection.setSelection(spanText,charSequence.length());
                   }
               }
           });
       }
    }

    @Override
    public void getHotKeyFail(String message) {
        Toast.makeText(SearchActivity.this,
                "获取失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchDataSuccess(List<ArticleBean> data) {
        if(data==null||data.size()==0){
            llHotKey.setVisibility(View.VISIBLE);
            rvContent.setVisibility(View.GONE);
        }else {
            llHotKey.setVisibility(View.GONE);
            rvContent.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void searchDataFail(String message) {
        Toast.makeText(SearchActivity.this,
                "没有找到", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadMoreDataSuccess(List<ArticleBean> data) {
        if(data.size()==0){
            mArticleAdapter.loadMoreEnd();
        }else {
            mArticleAdapter.addData(data);
            mArticleAdapter.loadMoreComplete();
        }
    }

    @Override
    public void loadMoreDataFail(String message) {
        Toast.makeText(SearchActivity.this, "没有更多了", Toast.LENGTH_SHORT).show();
    }
}
