package com.chenyuwei.itbook.fragment;

import android.view.View;
import android.widget.TextView;

import com.chenyuwei.basematerial.fragment.BaseDrawerFragment;
import com.chenyuwei.itbook.R;
import com.chenyuwei.itbook.activity.ShelfActivity;

/**
 * Created by vivi on 2016/9/2.
 */
public class DrawFragment extends BaseDrawerFragment {

    private TextView tvName;

    @Override
    protected int onBindView() {
        return R.layout.fragment_drawer;
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText(preferences.getString("name",""));
        findViewById(R.id.viewShelf);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.viewShelf:
                startActivity(ShelfActivity.class);
                break;
        }
    }
}
