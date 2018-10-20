package com.example.administrator.testnewaa;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import java.util.ArrayList;
import java.util.List;

public class TopViewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private RectclerViewAdapter adapter;
    private Button button;

    private CustomPopupWindow popupWindow;

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_top_view);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appBarLayout = findViewById(R.id.appbar);
        recyclerView = findViewById(R.id.reclerview);
        linearLayout = findViewById(R.id.ll_topview);
        button = findViewById(R.id.btn_screen);
        adapter = new RectclerViewAdapter(TopViewActivity.this, getData(20));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        popupWindow = new CustomPopupWindow(this, getData(6), index, false, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
//                if (doctorListBean != null && doctorListBean.getData() != null) {
//                    doctorListBean.getData().clear();
//                }
                popupWindow.setIndex(i);

                popupWindow.dismiss();
//                getDoctordata();
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                mainDarkview.startAnimation(animOut); //这个是设置灰色背景的
//                mainDarkview.setVisibility(View.GONE);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAtDropDown(findViewById(R.id.btn_screen));
            }
        });
    }


    private List<String> getData(int size) {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            data.add("Test data" + i);
        }
        return data;
    }
}
