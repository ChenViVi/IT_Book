package com.chenyuwei.itbook.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chenyuwei.basematerial.adapter.BaseRecyclerViewAdapter;
import com.chenyuwei.basematerial.fragment.BaseFragment;
import com.chenyuwei.basematerial.fragment.BaseListViewFragment;
import com.chenyuwei.basematerial.fragment.BaseRecyclerViewFragment;
import com.chenyuwei.basematerial.network.RequestMaker;
import com.chenyuwei.itbook.R;
import com.chenyuwei.itbook.activity.BookDetailActivity;
import com.chenyuwei.itbook.adapter.BookAdapter;
import com.chenyuwei.itbook.modle.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by vivi on 2016/9/3.
 */
public class RecommendFragment extends BaseRecyclerViewFragment<Book,BookAdapter> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new RequestMaker(activity, RequestMaker.Method.GET,"query_recommend","query_recommend") {
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
}
