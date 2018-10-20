package com.example.administrator.testnewaa;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.administrator.testnewaa.util.AppConfig;
import com.example.administrator.testnewaa.util.DataCleanManager;
import com.example.administrator.testnewaa.util.FileUtil;
import com.example.administrator.testnewaa.util.MethodsCompat;
import com.example.cdboy.GetCallLogActivity;
import com.example.cdboy.GetPhoneNumActivity;
import com.example.cdboy.TestActivity;

import org.kymjs.kjframe.Core;
import org.kymjs.kjframe.utils.FileUtils;

import java.io.File;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    private Button btnImage;
    private Button btnPhone;
    private Button btnCallLog;
    private Button btnRetrofit;
    private Button btnPhotoView;
    private Button btnTopView;

    //------****** 缓存相关****----------
    private final int CLEAN_SUC = 1001;
    private final int CLEAN_FAIL = 1002;

    private long sdcradCachSize = 0;
    private String sdCardCachPath = FileUtils.getSDCardPath() + File.separator + "Testibrary/cache";

    private static final String TAG = "MainActivity";

    /**
     * 获取城市的接口
     * <p>
     * "0" : {
     * "id" : "2",
     * "name" : "北京"
     * },
     * "1" : {
     * "id" : "3",
     * "name" : "安徽"
     * },
     * "2" : {
     * "id" : "4",
     * "name" : "福建"
     * },
     * "3" : {
     * "id" : "5",
     * "name" : "甘肃"
     * },
     * "4" : {
     * "id" : "6",
     * "name" : "广东"
     * }
     * <p>
     * 参数：pid  省级id或市级id
     */
    private static final String getCityUrl = "http://yf.xnetyy.com/PhoneApi/ZtApi/get_city";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEven();

    }

    private void initView() {
        btnImage = findViewById(R.id.btn_image_activity);
        btnPhone = findViewById(R.id.btn_phone_activity);
        btnCallLog = findViewById(R.id.btn_call_log_activity);
        btnRetrofit = findViewById(R.id.btn_retrofit);
        btnPhotoView = findViewById(R.id.btn_photoview);
        btnTopView = findViewById(R.id.btn_top_view);

//        ContentResolver cr = getContentResolver();
//        cr.insert(Uri.parse("content://com.example.administrator.testnewaa"),null);
//        Cursor cursor = cr.query()

        caculateCacheSize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        caculateCacheSize();
    }

    private void initEven() {
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GetPhoneNumActivity.class));
            }
        });

        btnCallLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GetCallLogActivity.class));
            }
        });


        btnRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(MainActivity.this,RecorderService.class);
//                startService(intent);

                getConfirmDialog(MainActivity.this, "是否清空缓存?", new DialogInterface.OnClickListener
                        () {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clearAppCache();
                    }
                }).show();

            }
        });

        btnPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestPhotoViewActivity.class));
            }
        });

        btnTopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TopViewActivity.class));
            }
        });
    }


    public static AlertDialog.Builder getConfirmDialog(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setMessage(Html.fromHtml(message));
        builder.setPositiveButton("确定", onClickListener);
        builder.setNegativeButton("取消", null);
        return builder;
    }

    public static AlertDialog.Builder getDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder;
    }


    /**
     * 计算缓存的大小
     */
    private void caculateCacheSize() {
        long fileSize = 0;
        String cacheSize = "0KB";
        File filesDir = getFilesDir();
        File cacheDir = getCacheDir();

        fileSize += FileUtil.getDirSize(filesDir);
        Log.i("mainActivity==>","filesDirSize==>"+FileUtil.getDirSize(filesDir));
        fileSize += FileUtil.getDirSize(cacheDir);
        Log.i("mainActivity==>","cacheDirSize==>"+FileUtil.getDirSize(cacheDir));
        // 2.2版本才有将应用缓存转移到sd卡的功能
        if (isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
            File externalCacheDir = MethodsCompat
                    .getExternalCacheDir(MainActivity.this);
            fileSize += FileUtil.getDirSize(externalCacheDir);
            Log.i("mainActivity==>","externalCacheDirSize==>"+FileUtil.getDirSize(externalCacheDir));
            sdcradCachSize = FileUtil.getDirSize(new File(sdCardCachPath));
            fileSize += sdcradCachSize;
            Log.i("mainActivity==>","sdcradCachSize==>"+sdcradCachSize);
        }
        if (fileSize > 0)
            cacheSize = FileUtil.formatFileSize(fileSize);
        btnRetrofit.setText("缓存：" + cacheSize);
    }

    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }

    /**
     * 清除app缓存
     */
    public void myclearaAppCache() {
        DataCleanManager.cleanDatabases(MainActivity.this);
        // 清除数据缓存
        DataCleanManager.cleanInternalCache(MainActivity.this);
        // 2.2版本才有将应用缓存转移到sd卡的功能
        if (isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
            DataCleanManager.cleanCustomCache(MethodsCompat.getExternalCacheDir(MainActivity.this));
        }
        // 清除编辑器保存的临时内容
        Properties props = getProperties();
        for (Object key : props.keySet()) {
            String _key = key.toString();
            if (_key.startsWith("temp"))
                removeProperty(_key);
        }

        if (sdcradCachSize != 0){
            DataCleanManager.cleanCustomCache(sdCardCachPath);
        }

        DataCleanManager.deleteDirectory(getCacheDir());
        DataCleanManager.deleteDirectory(getFilesDir());

        Core.getKJBitmap().cleanCache();
    }

    /**
     * 清除保存的缓存
     */
    public Properties getProperties() {
        return AppConfig.getAppConfig(MainActivity.this).get();
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(MainActivity.this).remove(key);
    }

    /**
     * 清除app缓存
     *
     * @param
     */
    public void clearAppCache() {

        new Thread() {
            @Override
            public void run() {
                Message msg = new Message();
                try {
                    myclearaAppCache();
                    msg.what = CLEAN_SUC;
                } catch (Exception e) {
                    e.printStackTrace();
                    msg.what = CLEAN_FAIL;
                }
                handler.sendMessage(msg);
            }
        }.start();
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case CLEAN_FAIL:
                    Toast.makeText(MainActivity.this, "清除失败", Toast.LENGTH_SHORT).show();
                    break;
                case CLEAN_SUC:
                    Toast.makeText(MainActivity.this, "清除成功", Toast.LENGTH_SHORT).show();
                    btnRetrofit.setText("0KB");
                    break;
            }
        }

        ;
    };


}
