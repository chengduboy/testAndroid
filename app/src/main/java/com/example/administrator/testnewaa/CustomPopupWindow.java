package com.example.administrator.testnewaa;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Boy on 2017/5/12.
 *
 * @Email wang1138966371@163.com
 */

public class CustomPopupWindow extends PopupWindow {

    private Activity activity;
    private View contentView;

    private ListTypeAdapter adapter;
    private AdapterView.OnItemClickListener onItemClickListener;

    // 用于保存PopupWindow的宽度
    private int width;
    // 用于保存PopupWindow的高度
    private int height;

    public CustomPopupWindow(Activity activity, List<String> data, int index, boolean isShowtag, AdapterView.OnItemClickListener onItemClickListener) {
        super(activity);
        this.activity = activity;
        this.onItemClickListener = onItemClickListener;
        if (data != null) {
            adapter = new ListTypeAdapter(activity,index, data,isShowtag);
        }
        this.initPopupWindow();
    }

    public void setIndex(int index){
        adapter.setIndex(index);
    }

    public CustomPopupWindow(Activity activity) {
        super(activity);
        this.activity = activity;
        this.initPopupWindow();
    }

    private void initPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.contentView = inflater.inflate(R.layout.activity_doctortype, null);
        if (adapter != null) {
            MyNoScrollListView listView = (MyNoScrollListView) this.contentView.findViewById(R.id.list_type);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(onItemClickListener);
        }

        this.setContentView(contentView);
        // 设置弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体可点击
        this.setTouchable(true);
        this.setFocusable(true);
        // 设置点击是否消失
        this.setOutsideTouchable(true);
        //设置弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupWindowAnimation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable background = new ColorDrawable(Color.WHITE);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(background);
        // 绘制
        this.mandatoryDraw();
    }

    /**
     * 强制绘制popupWindowView，并且初始化popupWindowView的尺寸
     */
    private void mandatoryDraw() {
        this.contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        /**
         * 强制刷新后拿到PopupWindow的宽高
         */
        this.width = this.contentView.getMeasuredWidth();
        this.height = this.contentView.getMeasuredHeight();
    }

    /**
     * 显示在控件的下右方
     *
     * @param parent parent
     */
    public void showAtDropDownRight(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            // x y
            int[] location = new int[2];
            //获取在整个屏幕内的绝对坐标
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, 0, location[0] + parent.getWidth() - this.width, location[1] + parent.getHeight());
        }
    }

    /**
     * 屏幕底部弹出
     *
     * @param parent parent
     */
    public void showPopFormBottom(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            // x y
            int[] location = new int[2];
            //获取在整个屏幕内的绝对坐标
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }

    /**
     * 显示在控件的下左方
     *
     * @param parent parent
     */
    public void showAtDropDownLeft(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            // x y
            int[] location = new int[2];
            //获取在整个屏幕内的绝对坐标
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, 0, location[0], location[1] + parent.getHeight());
        }
    }

    /**
     * 显示在控件的下中方
     *
     * @param parent parent
     */
    public void showAtDropDownCenter(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            // x y
            int[] location = new int[2];
            //获取在整个屏幕内的绝对坐标
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, 0, location[0] / 2 + parent.getWidth() / 2 - this.width / 6, location[1] + parent.getHeight());
        }
    }

    /**
     * 显示在控件的下中方
     *
     * @param parent parent
     */
    public void showAtDropDown(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            // x y
            int[] location = new int[2];
            //获取在整个屏幕内的绝对坐标
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, 0, 0, location[1] + parent.getHeight());
        }
    }


    public static class PopupWindowBuilder {
        private static String activityHashCode;
        private static CustomPopupWindow popupWindow;
        public static PopupWindowBuilder ourInstance;

        public static PopupWindowBuilder getInstance(Activity activity) {
            if (ourInstance == null) ourInstance = new PopupWindowBuilder();
            String hashCode = String.valueOf(activity.hashCode());
            /**
             * 不同一个Activity
             */
            if (!hashCode.equals(String.valueOf(activityHashCode))) {
                activityHashCode = hashCode;
                popupWindow = new CustomPopupWindow(activity);
            }
            return ourInstance;
        }

        public PopupWindowBuilder setTouchable(boolean touchable) {
            popupWindow.setTouchable(touchable);
            return this;
        }

        public PopupWindowBuilder setAnimationStyle(int animationStyle) {
            popupWindow.setAnimationStyle(animationStyle);
            return this;
        }

        public PopupWindowBuilder setBackgroundDrawable(Drawable background) {
            popupWindow.setBackgroundDrawable(background);
            return this;
        }

        public CustomPopupWindow getPopupWindow() {
            popupWindow.update();
            return popupWindow;
        }

    }

}