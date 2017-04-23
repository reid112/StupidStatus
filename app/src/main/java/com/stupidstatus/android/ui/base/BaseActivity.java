package com.stupidstatus.android.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.stupidstatus.android.R;

import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public abstract class BaseActivity extends AppCompatActivity {
    @Nullable protected AppBarLayout appBarLayout;
    protected ActionBar actionBar;
    protected Toolbar toolbar;
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(getLayoutId());
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-2389898029257514~6196164583");
        Fabric.with(this, new Crashlytics());
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        bindViews();
        onInitializeActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected final void onInitializeActionBar() {
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            setHomeAsUpIndicatorToBack();
        }
    }

    private void bindViews() {
        appBarLayout = ButterKnife.findById(this, R.id.appbarlayout);
        toolbar = ButterKnife.findById(this, R.id.toolbar);
    }

    public void setTitle(String title) {
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public void setTitle(int resId) {
        if (actionBar != null) {
            actionBar.setTitle(resId);
        }
    }

    public void setHomeAsUpIndicator(int resId) {
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(resId);
        }
    }

    public void setHomeAsUpIndicatorToBack() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black);
        }
    }

    public void setHomeAsUpIndicatorToCancel() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black);
        }
    }

    public void hideUpIndicator() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    public void logFirebaseEvent(String id, String name, String contentType) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType);
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    abstract protected int getLayoutId();
}
