package com.chenyuwei.itbook.modle;

import java.io.Serializable;

/**
 * Created by vivi on 2016/9/3.
 */
public class Book implements Serializable {
    private int id;
    private int sub_categoryid;
    private String name;
    private String author;
    private String information;
    private String pic_url;
    private String url;

    public Book(String name,String author,String pic_url){
        this.name = name;
        this.author = author;
        this.pic_url = pic_url;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getSub_categoryid() {
        return sub_categoryid;
    }

    public String getAuthor() {
        return author;
    }

    public String getInformation() {
        return information;
    }

    public String getPic_url() {
        return pic_url;
    }

    public String getUrl() {
        return url;
    }
}
