package ca.rjreid.stupidstatus;

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
