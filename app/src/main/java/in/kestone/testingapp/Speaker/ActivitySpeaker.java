package in.kestone.testingapp.Speaker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import in.kestone.testingapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySpeaker extends AppCompatActivity implements ViewClickListener {

    View parentView;
    ListView listSpeaker;

    List<Item> list = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker);

        parentView = findViewById(R.id.parentLayout);
        listSpeaker = (ListView) findViewById(R.id.speakerlist);
        if (new InternetConnection().isNetworkConnected()) {
            final ProgressDialog dialog;
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(ActivitySpeaker.this);
            dialog.setTitle("Speaker");
            dialog.setMessage("Fetching speaker information.");
            dialog.show();

            //Creating an object of our api interface
            sApiService api = RetrofitService.getApiService();

            /**
             * Calling JSON
             */
            Call<SpeakerList> call = api.getSpeaker();

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<SpeakerList>() {
                @Override
                public void onResponse(Call<SpeakerList> call, Response<SpeakerList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if (response.isSuccessful()) {
                        Log.e("Response ", String.valueOf(response.body().getItems().size()));
                        /**
                         * Got Successfully
                         */
                        list = response.body().getItems();

                        /**
                         * Binding that List to Adapter
                         */
                        adapter = new MyAdapter(ActivitySpeaker.this, list);
                        listSpeaker.setAdapter(adapter);

                    } else {
                        Snackbar.make(parentView, "Something wrong.", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<SpeakerList> call, Throwable t) {
                    dialog.dismiss();
                    Log.e("Response error ", t.getMessage().toString());
                }
            });

        } else {
            Snackbar.make(parentView, "Internet connection not available", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void passInfo(String message) {
        Intent in = new Intent(ActivitySpeaker.this, DetailActivity.class);
        in.putExtra("link", message);
        startActivity(in);
    }

    public class InternetConnection {
        private boolean isNetworkConnected() {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo() != null;
        }
    }
}