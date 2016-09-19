package com.chenyuwei.itbook.modle;

import java.io.Serializable;

/**
 * Created by vivi on 2016/9/3.
 */
public class Category implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
