package com.example.administrator.testnewaa;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;


/**
 * Created by boy on 2017/5/3.
 */

public class MyNoScrollListView extends ListView {
    private Context context;
    public MyNoScrollListView(Context context) {
        super(context);
        this.context = context;
    }

    public MyNoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(context, R.anim.list_animation), 0.2f));
//            }
//        }).start();

    }
}
