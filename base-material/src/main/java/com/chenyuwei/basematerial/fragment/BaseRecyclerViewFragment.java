package com.chenyuwei.basematerial.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chenyuwei.basematerial.R;
import com.chenyuwei.basematerial.adapter.BaseListViewAdapter;
import com.chenyuwei.basematerial.adapter.BaseRecyclerViewAdapter;
import com.superrecycleview.superlibrary.recycleview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivi on 2016/9/3.
 */
public abstract class BaseRecyclerViewFragment<Item,Adapter extends BaseRecyclerViewAdapter> extends BaseFragment implements SuperRecyclerView.LoadingListener{

    protected ArrayList<Item> data = new ArrayList<>();
    private Adapter adapter;
    private SuperRecyclerView recyclerView;
    private Toolbar toolbar;
    private TextView tvTitle;

    @Override
    protected int onBindView() {
        return R.layout.base_fragment_recycle_view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = setAdapter();
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        recyclerView = (SuperRecyclerView) findViewById(R.id.recyclerView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        recyclerView.setLayoutManager(setLayoutManager());
        recyclerView.setAdapter(adapter);
        enableToolBar(false);
        setPullLoadEnable(false);
        setPullRefreshEnable(false);
        recyclerView.setLoadingListener(this);
        clearItems();
    }

    protected abstract RecyclerView.LayoutManager setLayoutManager();

    protected void enableToolBar(boolean enable){
        if (enable){
            toolbar.setVisibility(View.VISIBLE);
        }
        else {
            toolbar.setVisibility(View.GONE);
        }
    }

    protected void setTitle(String title){
        enableToolBar(true);
        tvTitle.setText(title);
    }

    protected void setTitle(int titleId){
        setTitle(getResources().getString(titleId));
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    protected void setPullLoadEnable(boolean enable){
        recyclerView.setLoadMoreEnabled(enable);
    }

    protected void setPullRefreshEnable(boolean enable){
        recyclerView.setRefreshEnabled(enable);
    }

    protected void stopRefresh(){
        recyclerView.completeRefresh();
    }

    protected void stopLoadMore(){
        recyclerView.completeLoadMore();
    }

    protected void addItems(List<Item> items){
        data.addAll(items);
        adapter.notifyDataSetChanged();
    }

    protected void clearItems(){
        if (data.size() > 0){
            data.clear();
            adapter.notifyDataSetChanged();
        }
    }

    protected abstract Adapter setAdapter();

    protected void setOnItemClickListener(BaseRecyclerViewAdapter.OnItemClickListener<Item> listener){
        adapter.setOnItemClickListener(listener);
    }
}
