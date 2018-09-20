package com.example.nlospc.potatoapp.model;

import android.arch.lifecycle.Lifecycle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by duanziqi on 2018/9/19
 * Description:
 */
public class ArticleListVO implements Serializable{
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ArticleBean> getDatas() {
        return datas;
    }

    public void setDatas(List<ArticleBean> datas) {
        this.datas = datas;
    }

    private int offset;
    private int size;
    private List<ArticleBean> datas;
    private int total;
    private int pageCount;
    private int curPage;
    private boolean over;

}
