package com.example.nlospc.potatoapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by duanziqi on 2018/9/19
 * Description:
 */
public class TypeTagVO implements Serializable {
    private int id;
    private String name;
    private int visible;
    private List<TypeChildrenBean> childrens;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public List<TypeChildrenBean> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<TypeChildrenBean> childrens) {
        this.childrens = childrens;
    }


}
