package com.chenyuwei.itbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.chenyuwei.basematerial.activity.BaseRecyclerViewActivity;
import com.chenyuwei.basematerial.adapter.BaseRecyclerViewAdapter;
import com.chenyuwei.basematerial.network.RequestMaker;
import com.chenyuwei.itbook.adapter.BookAdapter;
import com.chenyuwei.itbook.modle.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by vivi on 2016/9/11.
 */
public class ShelfActivity extends BaseRecyclerViewActivity<Book,BookAdapter> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("我的书架");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<Book>() {
            @Override
            public void onItemClick(int position, Book book) {
                Intent intent = new Intent(activity,BookDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("book", book);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        new RequestMaker(activity, RequestMaker.Method.GET,"get_user_collection"+"?id="+ preferences.getInt("id",-1)) {
            @Override
            protected void onSuccess(String response) {
                Gson gson = new Gson();
                List<Book> items = gson.fromJson(response, new TypeToken<List<Book>>() {}.getType());
                addItems(items);
            }

            @Override
            protected void onFail() {
                super.onFail();
                stopRefresh();
                stopLoadMore();
            }
        };
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new GridLayoutManager(activity,2);
    }

    @Override
    protected BookAdapter setAdapter() {
        return new BookAdapter(data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true ;
        }
        return super.onOptionsItemSelected(item);
    }
}
