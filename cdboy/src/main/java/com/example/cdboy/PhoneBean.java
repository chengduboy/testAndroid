package com.example.cdboy;

import java.io.Serializable;

public class PhoneBean implements Serializable {

    private String username;
    private String phonrnum;
    private String type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonrnum() {
        return phonrnum;
    }

    public void setPhonrnum(String phonrnum) {
        this.phonrnum = phonrnum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
