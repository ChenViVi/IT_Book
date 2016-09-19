package com.chenyuwei.itbook.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chenyuwei.basematerial.activity.BaseRecyclerViewActivity;
import com.chenyuwei.basematerial.adapter.BaseRecyclerViewAdapter;

/**
 * Created by vivi on 2016/9/11.
 */
public class ShelfActivity extends BaseRecyclerViewActivity {
    @Override
    protected BaseRecyclerViewAdapter setAdapter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return null;
    }
}
