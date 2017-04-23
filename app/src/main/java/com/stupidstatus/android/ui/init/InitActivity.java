package com.stupidstatus.android.ui.init;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.stupidstatus.android.R;
import com.stupidstatus.android.ui.base.BaseActivity;
import com.stupidstatus.android.ui.status.StatusActivity;


public class InitActivity extends BaseActivity {

    //region Lifecycle
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(StatusActivity.createIntent(this));
        finish();
    }
    //endregion

    //region Base Activity Implementations
    @Override
    protected int getLayoutId() {
        return R.layout.activity_init;
    }
    //endregion
}
