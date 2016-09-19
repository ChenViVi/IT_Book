package com.chenyuwei.itbook.fragment;

import android.content.Intent;
import android.os.Bundle;

import com.chenyuwei.basematerial.adapter.BaseListViewAdapter;
import com.chenyuwei.basematerial.fragment.BaseListViewFragment;
import com.chenyuwei.basematerial.network.RequestMaker;
import com.chenyuwei.itbook.activity.CategoryActivity;
import com.chenyuwei.itbook.adapter.CategoryAdapter;
import com.chenyuwei.itbook.modle.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.List;

/**
 * Created by vivi on 2016/9/3.
 */
public class CategoryFragment extends BaseListViewFragment<Category,CategoryAdapter> {

    private int categoryId;

    @Override
    protected void onCreateView() {
        super.onCreateView();
        new RequestMaker(activity, RequestMaker.Method.GET,"query_sub_category?id=" + categoryId) {
            @Override
            protected void onSuccess(String response) {
                Gson gson = new Gson();
                List<Category> items = gson.fromJson(response, new TypeToken<List<Category>>() {}.getType());
                addItems(items);
            }
        };
        setOnItemClickListener(new BaseListViewAdapter.OnItemClickListener<Category>() {
            @Override
            public void onItemClick(int position, Category category) {
                Intent intent = new Intent(activity, CategoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("category", category);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected CategoryAdapter setAdapter() {
        return new CategoryAdapter(data);
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
