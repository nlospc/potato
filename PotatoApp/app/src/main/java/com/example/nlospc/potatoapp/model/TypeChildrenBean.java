package com.example.nlospc.potatoapp.model;

import java.io.Serializable;

/**
 * Created by duanziqi on 2018/9/19
 * Description:
 */
public class TypeChildrenBean implements Serializable {
    private int id;

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

    private String name;
    private int visible;
}
