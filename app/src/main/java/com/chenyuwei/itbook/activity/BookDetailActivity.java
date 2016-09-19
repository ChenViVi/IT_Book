package com.chenyuwei.itbook.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chenyuwei.basematerial.activity.BaseActivity;
import com.chenyuwei.basematerial.network.RequestMaker;
import com.chenyuwei.itbook.R;
import com.chenyuwei.itbook.modle.Book;
import com.chenyuwei.loadimageview.LoadImageView;

public class BookDetailActivity extends BaseActivity {

    private Book book;
    private TextView tvInfo;
    private LoadImageView ivCover;

    @Override
    protected int onBindView() {
        return R.layout.activity_book_detailt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        ivCover = (LoadImageView) findViewById(R.id.ivCover);
        findViewById(R.id.fOpen);
        book = (Book)getIntent().getSerializableExtra("book");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCollapsingToolbarLayout.setTitle(book.getName());
        tvInfo.setText(book.getInformation());
        ivCover.load(book.getPic_url());
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.fOpen:
                new RequestMaker(activity, RequestMaker.Method.POST, "set_user_collection_status?" + "userid=" + preferences.getInt("id",-1) + "&bookid="+ book.getId() + "&status=1","set_user_collection_status") {
                    @Override
                    protected void onSuccess(String response) {
                        Intent intent = new Intent(activity,ReadingActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("book", book);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                };
                break;
        }
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
