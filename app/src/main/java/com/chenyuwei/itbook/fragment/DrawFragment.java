package com.chenyuwei.itbook.fragment;

import android.widget.TextView;

import com.chenyuwei.basematerial.fragment.BaseDrawerFragment;
import com.chenyuwei.itbook.R;

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
    }
}
