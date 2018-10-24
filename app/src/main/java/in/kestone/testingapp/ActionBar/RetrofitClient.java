package in.kestone.testingapp.ActionBar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import in.kestone.testingapp.Speaker.RetrofitService;
import in.kestone.testingapp.Speaker.SpeakerList;
import in.kestone.testingapp.Speaker.sApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitClient {
    Context context;

    public RetrofitClient(Context context) {
        this.context = context;
    }

    public static void callAPI(final Context context, final View view) {
        if (isNetworkConnected(context)) {
            final ProgressDialog dialog;
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(context);
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
//                        list = response.body().getItems();

                        /**
                         * Binding that List to Adapter
                         */
//                        listView.setAdapter(new NavMenuAdapter(AwesomeBar2.this, list));

                    } else {
                        Snackbar.make(view, "Something wrong.", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<SpeakerList> call, Throwable t) {
                    dialog.dismiss();
                    Log.e("Response error ", t.getMessage().toString());
                }
            });

        } else {
            Snackbar.make(view, "Internet connection not available", Snackbar.LENGTH_LONG).show();
        }
    }
        private static boolean isNetworkConnected(Context context) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo() != null;
        }

}
