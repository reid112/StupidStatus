package com.stupidstatus.android.ui.init;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.stupidstatus.android.R;
import com.stupidstatus.android.ui.base.BaseActivity;
import com.stupidstatus.android.ui.status.StatusActivity;


public class InitActivity extends BaseActivity {

    //region Lifecycle
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logFirebaseEvent("1", "App init", "Init");

        // Stay on init screen for 1.5 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(StatusActivity.createIntent(InitActivity.this));
                finish();
            }
        }, 1500);
    }
    //endregion

    //region Base Activity Implementations
    @Override
    protected int getLayoutId() {
        return R.layout.activity_init;
    }
    //endregion
}
