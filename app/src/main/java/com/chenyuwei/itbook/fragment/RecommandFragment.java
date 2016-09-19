package com.chenyuwei.itbook.fragment;


import com.chenyuwei.basematerial.fragment.BaseFragment;
import com.chenyuwei.itbook.R;

/**
 * Created by vivi on 2016/9/3.
 */
public class RecommandFragment extends BaseFragment {

    private int categoryId;

    @Override
    protected int onBindView() {
        return R.layout.fragment_recommand;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
