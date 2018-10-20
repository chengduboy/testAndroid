package com.example.cdboy;

import java.io.Serializable;

public class CallLogBean implements Serializable {

    /** 联系人名称 **/
    private String username;
    /** 联系人手机号 **/
    private String phonenum;
    private String phonenum2;
    /** 通话类型 **/
    private String calltype;
    /** 通话时间 **/
    private long callstarttime;
    /** 通话时长**/
    private long calltime;
    /** 电话运营商**/
    private String phoneloaction;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getPhonenum2() {
        return phonenum2;
    }

    public void setPhonenum2(String phonenum2) {
        this.phonenum2 = phonenum2;
    }

    public String getCalltype() {
        return calltype;
    }

    public void setCalltype(String calltype) {
        this.calltype = calltype;
    }

    public long getCallstarttime() {
        return callstarttime;
    }

    public void setCallstarttime(long callstarttime) {
        this.callstarttime = callstarttime;
    }

    public long getCalltime() {
        return calltime;
    }

    public void setCalltime(long calltime) {
        this.calltime = calltime;
    }

    public String getPhoneloaction() {
        return phoneloaction;
    }

    public void setPhoneloaction(String phoneloaction) {
        this.phoneloaction = phoneloaction;
    }
}
