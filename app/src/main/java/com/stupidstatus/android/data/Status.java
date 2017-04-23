package com.stupidstatus.android.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Status {

    @SerializedName("text")
    @Expose
    private String text;

    public String getText() {
        return text;
    }
}
