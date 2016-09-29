package com.chenyuwei.itbook.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.chenyuwei.basematerial.activity.BaseTabTopActivity;
import com.chenyuwei.basematerial.fragment.BaseDrawerFragment;
import com.chenyuwei.basematerial.network.RequestMaker;
import com.chenyuwei.itbook.R;
import com.chenyuwei.itbook.fragment.CategoryFragment;
import com.chenyuwei.itbook.fragment.RecommendFragment;
import com.chenyuwei.itbook.modle.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MainActivity extends BaseTabTopActivity {


    private BaseDrawerFragment drawerFragment;
    private SearchView svBook;

    @Override
    protected int onBindView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        svBook = (SearchView) findViewById(R.id.svBook);
        drawerFragment = (BaseDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        addFragment(new RecommendFragment(),"推荐");
        new RequestMaker(activity, RequestMaker.Method.GET, "query_category") {
            @Override
            protected void onSuccess(String response) {
                Gson gson = new Gson();
                List<Category> items = gson.fromJson(response, new TypeToken<List<Category>>() {}.getType());
                for (Category category : items){
                    CategoryFragment fragment = new CategoryFragment();
                    fragment.setCategoryId(category.getId());
                    addFragment(fragment,category.getName());
                }
            }
        };
        svBook.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setFocusable(true);
        toolbar.setFocusableInTouchMode(true);
        toolbar.requestFocus();
    }
}
