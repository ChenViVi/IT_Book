package com.chenyuwei.basematerial.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.chenyuwei.basematerial.BaseApplication;

/**
 * Created by vivi on 2016/7/18.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    protected AppCompatActivity activity;
    protected View rootView;
    protected RequestQueue appQueue;
    protected RequestQueue queue;
    protected SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (AppCompatActivity) getActivity();
        appQueue = Volley.newRequestQueue(activity);
        queue = ((BaseApplication)activity.getApplication()).getQueue();
        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(onBindView(),null);
        onCreateView();
        return rootView;
    }

    protected void onCreateView(){}

    protected View findViewById(int id){
        View view = rootView.findViewById(id);
        if (!(rootView.findViewById(id) instanceof AdapterView)){
            view.setOnClickListener(this);
        }
        return view;
    }

    protected void toast(String message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    protected void startActivity(Class<?> cls){
        startActivity(new Intent(activity, cls));
    }

    protected abstract int onBindView();

    @Override
    public void onClick(View view) {

    }
}
