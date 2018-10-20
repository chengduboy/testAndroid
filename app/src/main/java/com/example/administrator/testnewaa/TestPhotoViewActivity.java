package com.example.administrator.testnewaa;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import com.example.administrator.testnewaa.view.photoview.PhotoView;
import com.zhouwei.blurlibrary.EasyBlur;

public class TestPhotoViewActivity extends AppCompatActivity {


    private static String picUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539596546551&di=6c7221e08e5212eee196b42174bc417a&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F0b55b319ebc4b745b19f82c1c4fc1e178b8215d9.jpg";

    private Button btnMohu;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photoview);

        initView();
    }


    private void initView() {

        final PhotoView photoView = findViewById(R.id.photoView);
        btnMohu = findViewById(R.id.btn_mohu);

        Glide.with(TestPhotoViewActivity.this).asBitmap().load(picUrl).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                bitmap = resource;
            }
        });


        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.ic_launcher)//加载成功之前占位图
                .error(R.mipmap.back_black_left)    //加载错误之后的错误图
//                .override(400, 400)    //指定图片的尺寸
//指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
                .fitCenter()
//指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的宽高都大于等于ImageView的宽度，然后截取中间的显示。）
                .centerCrop()
                .circleCrop()//指定图片的缩放类型为centerCrop （圆形）
                .skipMemoryCache(true)    //跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL)    //缓存所有版本的图像
                .diskCacheStrategy(DiskCacheStrategy.NONE)    //跳过磁盘缓存
                .diskCacheStrategy(DiskCacheStrategy.DATA)    //只缓存原来分辨率的图片
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);    //只缓存最终的图片

        Glide.with(this)
                .load(picUrl)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade(2000))
                .into(photoView);

        btnMohu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bitmap != null){
                    Bitmap finalBitmap = EasyBlur.with(TestPhotoViewActivity.this)
                            .bitmap(bitmap) //要模糊的图片
                            .radius(6)//模糊半径
                            .scale(8)
                            .blur();

                    photoView.setImageBitmap(finalBitmap);
                }

            }
        });


    }



}
