package ca.rjreid.stupidstatus;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String ENDPOINT = "http://stupidstat.us/api/";
    private static final String ARG_CURRENT_STATUS_TEXT = "arg_current_status_text";

    private StatusQuery statusQuery;
    private String currentStatusText;

    @BindView(R.id.toolbar) Toolbar toolbar;
    //@BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.activity_main_status_text_view) TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(ARG_CURRENT_STATUS_TEXT, currentStatusText);
        super.onSaveInstanceState(outState);
    }

    @OnClick(R.id.activity_main_status_button)
    public void getStatus() {
        Call<Status> call = statusQuery.getStatus();
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status status = response.body();
                currentStatusText = status.getText();
                statusTextView.setText(currentStatusText);
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                statusTextView.setText(R.string.label_somethingWentWrong);
            }
        });
    }

}
