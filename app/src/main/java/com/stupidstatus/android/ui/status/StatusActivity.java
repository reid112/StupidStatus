package com.stupidstatus.android.ui.status;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.stupidstatus.android.R;
import com.stupidstatus.android.data.Status;
import com.stupidstatus.android.data.StatusQuery;
import com.stupidstatus.android.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatusActivity extends BaseActivity {

    //region Constants
    private static final String ENDPOINT = "http://stupidstat.us/api/";
    private static final String ARG_CURRENT_STATUS_TEXT = "arg_current_status_text";
    //endregion

    //region Variables
    private StatusQuery statusQuery;
    private String currentStatusText;
    //endregion

    //region Views
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.activity_main_status_text_view) TextView statusTextView;
    //endregion

    //region Intents
    public static Intent createIntent(Context context) {
        return new Intent(context, StatusActivity.class);
    }
    //endregion

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        statusQuery = retrofit.create(StatusQuery.class);

        if (savedInstanceState != null) {
            currentStatusText = savedInstanceState.getString(ARG_CURRENT_STATUS_TEXT);
            statusTextView.setText(currentStatusText);
        } else {
            getStatus();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(ARG_CURRENT_STATUS_TEXT, currentStatusText);
        super.onSaveInstanceState(outState);
    }
    //endregion

    //region Click Handlers
    @OnClick(R.id.activity_main_status_button)
    public void getStatus() {
        logFirebaseEvent("2", "Get Status", "Button Press");

        Call<Status> call = statusQuery.getStatus();
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status status = response.body();
                currentStatusText = status.getText();
                statusTextView.setText(currentStatusText);
                logFirebaseEvent("3", "Got Status - " + currentStatusText, "onResponse");
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                statusTextView.setText(R.string.label_somethingWentWrong);
                logFirebaseEvent("4", "Failed to get status", "onFailure");
            }
        });
    }
    //endregion

    //region Base Activity Implementations
    @Override
    protected int getLayoutId() {
        return R.layout.activity_status;
    }
    //endregion
}
