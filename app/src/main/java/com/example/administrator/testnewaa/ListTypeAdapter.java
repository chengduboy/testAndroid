package com.example.administrator.testnewaa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Boy on 2017/5/12.
 *
 * @Email wang1138966371@163.com
 */

public class ListTypeAdapter extends BaseAdapter {

    private Context context;
    private List<String> mList;
    private int index = 0;
    private boolean isShowTag = false;

    public ListTypeAdapter(Context context, int index, List<String> mList, boolean isShowTag) {
        this.context = context;
        this.mList = mList;
        this.index = index;
        this.isShowTag = isShowTag;
    }

    public void setIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_recycler_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

                holder.tvItmeOnetext.setText(mList.get(i));
//        switch (i) {
//            case 0:
//                holder.tvItmeOnetext.setText("综合排序");
//                break;
//            case 1:
//                holder.tvItmeOnetext.setText("咨询人数");
//                break;
//            case 2:
//                holder.tvItmeOnetext.setText("好评率");
//                break;
//        }
        if (i == index) {
//            holder.tvItmeOnetext.setTextColor(Color.parseColor(context.getString(R.string.select_color)));
        } else {
//            holder.tvItmeOnetext.setTextColor(Color.parseColor(context.getString(R.string.title_color)));
        }
        if (isShowTag) {
//            if (i == index) {
//                holder.ivSelecedIcon.setVisibility(View.VISIBLE);
//            } else {
//                holder.ivSelecedIcon.setVisibility(View.GONE);
//
//            }
        }

        return view;
    }


    static class ViewHolder {
//        @BindView(R.id.tv_itme_onetext)
        TextView tvItmeOnetext;
//        @BindView(R.id.iv_seleced_icon)
//        ImageView ivSelecedIcon;

        ViewHolder(View view) {
            tvItmeOnetext = view.findViewById(R.id.tv_title);
//            ButterKnife.bind(this, view);
        }
    }
}
