package com.chenyuwei.itbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenyuwei.basematerial.adapter.BaseListViewAdapter;
import com.chenyuwei.basematerial.adapter.BaseRecyclerViewAdapter;
import com.chenyuwei.itbook.R;
import com.chenyuwei.itbook.modle.Book;
import com.chenyuwei.loadimageview.LoadImageView;

import java.util.List;

/**
 * Created by vivi on 2016/9/1.
 */
public class BookAdapter extends BaseRecyclerViewAdapter<Book,BookAdapter.ViewHolder> {

    public BookAdapter(List<Book> data) {
        super(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateItem(ViewGroup parent, int viewType) {
        return  new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position, Book book) {
        viewHolder.tvTitle.setText(book.getName());
        viewHolder.tvAuthor.setText(book.getAuthor());
        viewHolder.ivCover.load(book.getPic_url());
    }

    class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder{
        TextView tvTitle;
        TextView tvAuthor;
        LoadImageView ivCover;
        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) findViewById(R.id.tvTitle);
            tvAuthor = (TextView) findViewById(R.id.tvAuthor);
            ivCover = (LoadImageView) findViewById(R.id.ivCover);
        }
    }
}
