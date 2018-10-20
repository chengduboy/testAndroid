package com.example.cdboy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class TestActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_testimage);
        imageView = findViewById(R.id.iv_iamge);
        imageView.setImageResource(R.mipmap.testimage);
    }
}
