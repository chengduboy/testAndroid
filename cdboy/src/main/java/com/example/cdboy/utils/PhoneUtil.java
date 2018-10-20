package com.example.cdboy.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.cdboy.CallLogBean;
import com.example.cdboy.PhoneBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhoneUtil {

    public PhoneUtil(Context context) {
        this.context = context;
    }

    // 号码
    public final static String NUM = ContactsContract.CommonDataKinds.Phone.NUMBER;
    // 联系人姓名
    public final static String NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
    //上下文对象
    private Context context;
    // 联系人提供者的uri
    private Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;


    //获取所有联系人
    public List<PhoneBean> getPhone() {
        List<PhoneBean> phoneDtos = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(phoneUri, new String[]{NUM, NAME}, null, null, null);
        try {
            while (cursor.moveToNext()) {
                PhoneBean phoneDto = new PhoneBean();
                phoneDto.setPhonrnum(cursor.getString(cursor.getColumnIndex(NUM)));
                phoneDto.setUsername(cursor.getString(cursor.getColumnIndex(NAME)));
                phoneDtos.add(phoneDto);
            }
        } catch (Exception e) {
            Log.e("phoneUtil==>", "getphone:" + e.getMessage());
        } finally {
            cursor.close();
        }
        return phoneDtos;
    }


    public List<CallLogBean> getCallLog(Context context) {
        List<CallLogBean> callLogBeans = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Log.i("phoneUtil==>", "query call log " + resolver);

        //获取cursor对象
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, new String[]{
                CallLog.Calls.CACHED_FORMATTED_NUMBER,
                CallLog.Calls.NUMBER,
                CallLog.Calls.CACHED_NAME,
                CallLog.Calls.TYPE,
                CallLog.Calls.DATE,
                CallLog.Calls.DURATION,
                CallLog.Calls.GEOCODED_LOCATION,
        }, null, null, null);

        try {
            while (cursor.moveToNext()) {
                CallLogBean calllog = new CallLogBean();
                calllog.setPhonenum(cursor.getString(0));
                calllog.setPhonenum2(cursor.getString(1));
                calllog.setUsername(cursor.getString(2));
                calllog.setCalltype(getCallType(cursor.getInt(3)));
                calllog.setCallstarttime(cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE)));
                Log.i("phoneUtil==>","date:"+cursor.getColumnIndex(CallLog.Calls.DATE));
                calllog.setCalltime(cursor.getLong(5));
                calllog.setPhoneloaction(cursor.getString(cursor.getColumnIndex(CallLog.Calls.GEOCODED_LOCATION)));
                callLogBeans.add(calllog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();  //关闭cursor，避免内存泄露
        }
        return callLogBeans;
    }


    /**
     * 通话类型转换
     *
     * @param anInt
     * @return
     */
    private String getCallType(int anInt) {
        switch (anInt) {
            case CallLog.Calls.INCOMING_TYPE:
                return "呼入";
            case CallLog.Calls.OUTGOING_TYPE:
                return "呼出";
            case CallLog.Calls.MISSED_TYPE:
                return "未接";
            default:
                break;
        }
        return null;
    }

    /**
     * 时间格式化
     *
     * @param time
     * @return
     */
    public static String formatDate(long time) {
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time));
    }


    /**
     * 通话时长格式化
     *
     * @param time
     * @return
     */
    public static String formatDuration(long time) {
        long s = time % 60;
        long m = time / 60;
        long h = time / 60 / 60;
        StringBuilder sb = new StringBuilder();
        if (h > 0) {
            sb.append(h).append("小时");
        }
        if (m > 0) {
            sb.append(m).append("分");
        }
        sb.append(s).append("秒");
        return sb.toString();
    }


}
