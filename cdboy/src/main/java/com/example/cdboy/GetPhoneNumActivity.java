package com.example.cdboy;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.cdboy.utils.PhoneUtil;

import java.util.List;

public class GetPhoneNumActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private List<PhoneBean> phoneList;
    private ListView listView;
    private PhoneNumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getphone);
        initView();
        initEvent();
    }


    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.lv_phone);
    }

    private void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        check();
    }

    private void getPhoneNum() {

        PhoneUtil phoneUtil = new PhoneUtil(GetPhoneNumActivity.this);
        phoneList = phoneUtil.getPhone();
        adapter = new PhoneNumAdapter(GetPhoneNumActivity.this,phoneList);
        listView.setAdapter(adapter);

    }

    /**
     * 检查权限
     */
    private void check() {
        //判断是否有权限
        if (ContextCompat.checkSelfPermission(GetPhoneNumActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(GetPhoneNumActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 201);
        } else {
            getPhoneNum();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 201){
            getPhoneNum();
        }else {
            return;
        }
    }
}
