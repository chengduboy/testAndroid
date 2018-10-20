package com.example.cdboy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cdboy.utils.PhoneUtil;

import java.util.List;

public class CallLogAdapter extends BaseAdapter {

    private Context context;
    private List<CallLogBean> mlist;

    public CallLogAdapter(Context context, List<CallLogBean> mlist){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_call_log,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_userName.setText(mlist.get(position).getUsername());
        holder.tv_phoneNum.setText(mlist.get(position).getPhonenum2());
        holder.tv_callStarttime.setText(PhoneUtil.formatDate(mlist.get(position).getCallstarttime()));
        holder.tv_callTime.setText(PhoneUtil.formatDuration(mlist.get(position).getCalltime()));
        holder.tv_callType.setText(mlist.get(position).getCalltype());
        holder.tv_callLocation.setText(mlist.get(position).getPhoneloaction());
        return convertView;
    }

    public class ViewHolder {
        private TextView tv_userName;
        private TextView tv_phoneNum;
        private TextView tv_callStarttime;
        private TextView tv_callTime;
        private TextView tv_callType;
        private TextView tv_callLocation;

       ViewHolder(View v){
           tv_userName = v.findViewById(R.id.tv_username);
           tv_phoneNum = v.findViewById(R.id.tv_phone_num);
           tv_callStarttime = v.findViewById(R.id.tv_start_time);
           tv_callTime = v.findViewById(R.id.tv_call_time);
           tv_callType = v.findViewById(R.id.tv_call_type);
           tv_callLocation = v.findViewById(R.id.tv_call_location);
       }
    }
}
