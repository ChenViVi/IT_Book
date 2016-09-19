package com.chenyuwei.itbook;

import com.chenyuwei.basematerial.BaseApplication;
import com.chenyuwei.basematerial.network.RequestMaker;

/**
 * Created by vivi on 2016/8/31.
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        RequestMaker.setBaseUrl("http://123.206.16.78/index.php/Home/Request/");
    }
}