package com.example.cdboy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PhoneNumAdapter extends BaseAdapter {

    private Context context;
    private List<PhoneBean> mlist;

    public PhoneNumAdapter(Context context, List<PhoneBean> mlist){
        this.context = context;
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_phone_num,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_userName.setText(mlist.get(position).getUsername());
        holder.tv_phoneNum.setText(mlist.get(position).getPhonrnum());
        return convertView;
    }

    public class ViewHolder {
        private TextView tv_userName;
        private TextView tv_phoneNum;

       ViewHolder(View v){
           tv_userName = v.findViewById(R.id.tv_username);
           tv_phoneNum = v.findViewById(R.id.tv_phone_num);
       }
    }
}
