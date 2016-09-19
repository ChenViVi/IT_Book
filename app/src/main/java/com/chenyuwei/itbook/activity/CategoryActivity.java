package com.chenyuwei.itbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chenyuwei.basematerial.activity.BaseRecyclerViewActivity;
import com.chenyuwei.basematerial.adapter.BaseRecyclerViewAdapter;
import com.chenyuwei.basematerial.network.RequestMaker;
import com.chenyuwei.itbook.adapter.BookAdapter;
import com.chenyuwei.itbook.modle.Book;
import com.chenyuwei.itbook.modle.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by vivi on 2016/9/3.
 */
public class CategoryActivity extends BaseRecyclerViewActivity<Book,BookAdapter> {

    private Category category;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPullRefreshEnable(true);
        setPullLoadEnable(true);
        category = (Category)getIntent().getSerializableExtra("category");
        requestBookList(0,20);
        setTitle(category.getName());
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
    public void onRefresh() {
        super.onRefresh();
        requestBookList(0,10);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        requestBookList(data.size(),10);
    }

    private void requestBookList(final int start_pos,final int list_num){
        new RequestMaker(activity, RequestMaker.Method.GET, "query_book?sub_categoryid=" + category.getId() + "&start_pos=" + start_pos + "&list_num=" + list_num) {
            @Override
            protected void onSuccess(String response) {
                Gson gson = new Gson();
                List<Book> items = gson.fromJson(response, new TypeToken<List<Book>>() {}.getType());
                if (start_pos ==0 && data.size() > 0){
                    clearItems();
                }
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
}
