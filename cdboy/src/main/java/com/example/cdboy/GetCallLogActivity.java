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

public class GetCallLogActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private List<CallLogBean> mlist;
    private CallLogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getcalllog);
        initView();
    }

    private void initView(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.lv_phone);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        check();
    }


    private void getCallLog(){

        PhoneUtil phoneUtil = new PhoneUtil(GetCallLogActivity.this);
        mlist = phoneUtil.getCallLog(GetCallLogActivity.this);
        adapter = new CallLogAdapter(GetCallLogActivity.this,mlist);
        listView.setAdapter(adapter);

    }

    /**
     * 检查权限
     */
    private void check() {
        //判断是否有权限
        if (ContextCompat.checkSelfPermission(GetCallLogActivity.this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(GetCallLogActivity.this, new String[]{Manifest.permission.READ_CALL_LOG}, 201);
        } else {
            getCallLog();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 201){
            getCallLog();
        }else {
            return;
        }

    }
}
