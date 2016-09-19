package com.chenyuwei.itbook.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.chenyuwei.basematerial.activity.BaseActivity;
import com.chenyuwei.basematerial.view.DownlandDialog;
import com.chenyuwei.itbook.R;
import com.chenyuwei.itbook.modle.Book;
import com.joanzapata.pdfview.PDFView;


import java.io.File;

public class ReadingActivity extends BaseActivity {

    private PDFView pdfView;
    private Book book;

    @Override
    protected int onBindView() {
        return R.layout.activity_reading;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pdfView = (PDFView) findViewById(R.id.pdfView);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        book = (Book)getIntent().getSerializableExtra("book");
        setTitle(book.getName());
        new DownlandDialog(activity, book.getUrl(), new File(Environment.getExternalStorageDirectory() + "/ITDownload", book.getId() + ".pdf")) {

            @Override
            protected void onExists(File file) {
                pdfView.fromFile(file).defaultPage(preferences.getInt(String.valueOf(book.getId()),1)).load();
            }

            @Override
            public void onFinish(File file) {
                pdfView.fromFile(file).defaultPage(preferences.getInt(String.valueOf(book.getId()),1)).load();
            }

            @Override
            protected void onFail(File file) {
                super.onFail(file);
                finish();
            }

            @Override
            protected void onCancel(File file) {
                super.onCancel(file);
                finish();
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true ;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        preferences.edit().putInt(String.valueOf(book.getId()), pdfView.getCurrentPage() + 1).apply();
    }
}
