package com.chenyuwei.itbook.fragment;

import android.view.View;
import android.widget.EditText;

import com.chenyuwei.basematerial.fragment.BaseFragment;
import com.chenyuwei.basematerial.network.RequestMaker;
import com.chenyuwei.itbook.R;
import com.chenyuwei.itbook.activity.MainActivity;

import java.util.HashMap;

/**
 * Created by vivi on 2016/9/9.
 */
public class RegisterFragment extends BaseFragment {

    private EditText editName;
    private EditText editPhone;
    private EditText editPassword;

    @Override
    protected int onBindView() {
        return R.layout.fragment_register;
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        editName = (EditText) findViewById(R.id.editName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editPassword = (EditText) findViewById(R.id.editPassword);
        findViewById(R.id.btnRegister);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.btnRegister:
                new RequestMaker(activity, RequestMaker.Method.POST, "register") {
                    @Override
                    protected void onSuccess(String response) {
                        toast(response);
                        preferences.edit().putInt("id",Integer.parseInt(response)).apply();
                        preferences.edit().putString("name",editName.getText().toString()).apply();
                        startActivity(MainActivity.class);
                    }

                    @Override
                    protected HashMap<String, String> onPost() {
                        HashMap<String,String> map = new HashMap<>();
                        map.put("name",editName.getText().toString());
                        map.put("phone",editPhone.getText().toString());
                        map.put("password",editPassword.getText().toString());
                        return map;
                    }
                };
                break;
        }
    }
}
