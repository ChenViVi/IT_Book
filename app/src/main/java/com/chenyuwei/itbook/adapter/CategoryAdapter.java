package com.chenyuwei.itbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenyuwei.basematerial.adapter.BaseListViewAdapter;
import com.chenyuwei.itbook.R;
import com.chenyuwei.itbook.modle.Category;

import java.util.List;

/**
 * Created by vivi on 2016/9/1.
 */
public class CategoryAdapter extends BaseListViewAdapter<Category,CategoryAdapter.ViewHolder> {

    public CategoryAdapter(List<Category> data) {
        super(data);
    }

    @Override
    protected ViewHolder onCreateItem(ViewGroup viewGroup) {
        return  new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position, Category category) {
        viewHolder.tvName.setText(category.getName());
    }

    class ViewHolder extends BaseListViewAdapter.ViewHolder{
        TextView tvName;
        ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) findViewById(R.id.tvName);
        }
    }
}
