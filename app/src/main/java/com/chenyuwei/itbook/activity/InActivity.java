package com.chenyuwei.itbook.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.chenyuwei.basematerial.activity.BaseActivity;
import com.chenyuwei.basematerial.adapter.TabAdapter;
import com.chenyuwei.basematerial.util.Tool;
import com.chenyuwei.itbook.R;
import com.chenyuwei.itbook.fragment.LoginFragment;
import com.chenyuwei.itbook.fragment.RegisterFragment;

/**
 * Created by vivi on 2016/9/9.
 */
public class InActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter adapterTab;
    private View layoutHeader;

    @Override
    protected int onBindView() {
        return R.layout.activity_in;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        layoutHeader = findViewById(R.id.layoutHeader);
        adapterTab = new TabAdapter(getSupportFragmentManager());
        adapterTab.addFragment( new LoginFragment(),getResources().getString(R.string.fragment_login));
        adapterTab.addFragment(new RegisterFragment(),getResources().getString(R.string.fragment_register));
        viewPager.setAdapter(adapterTab);
        tabLayout.setupWithViewPager(viewPager);
        rootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > 0)){
                    layoutHeader.getLayoutParams().height = 0;
                    layoutHeader.getLayoutParams().width = 0;
                    layoutHeader.setLayoutParams(layoutHeader.getLayoutParams());
                }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > 0)){
                    layoutHeader.getLayoutParams().height = Tool.dp2px(activity,230);
                    layoutHeader.getLayoutParams().width = Tool.getScreenWidth(activity);
                    layoutHeader.setLayoutParams(layoutHeader.getLayoutParams());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
    }
}
