package ca.rjreid.stupidstatus.ui.init;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ca.rjreid.stupidstatus.R;
import ca.rjreid.stupidstatus.ui.base.BaseActivity;
import ca.rjreid.stupidstatus.ui.status.StatusActivity;


public class InitActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(StatusActivity.createIntent(this));
        finish();
    }

    //region Base Activity Implementations
    @Override
    protected int getLayoutId() {
        return R.layout.activity_init;
    }
    //endregion
}
