package in.kestone.testingapp.ActionBar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.kestone.testingapp.R;
import in.kestone.testingapp.Speaker.Item;
import in.kestone.testingapp.Speaker.RetrofitService;
import in.kestone.testingapp.Speaker.SpeakerList;
import in.kestone.testingapp.Speaker.ViewClickListener;
import in.kestone.testingapp.Speaker.sApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AwesomeBar2 extends AppCompatActivity implements ViewClickListener {

    //    @BindView(R.id.bar)
//    AwesomeBar bar;
    List<Item> list = new ArrayList<>();

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    ListView listView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerLayoutToggle;
    View parentView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awesome_bar);
        ButterKnife.bind(this);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toobar);
        toolbar.setTitle("Example");
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.icon);
        parentView = findViewById(R.id.parentLayout);

        callAPI(this, parentView);
        ////material design

        final View rootView = findViewById(android.R.id.content);
        final List<TextInputLayout> textInputLayouts = Utils.findViewsWithType(
                rootView, TextInputLayout.class);
        final TextInputEditText name = findViewById(R.id.name_edit_text);
        final TextInputEditText email = findViewById(R.id.email_edit_text);
        final TextInputEditText password = findViewById(R.id.password_edit_text);
        textWatcher(textInputLayouts);


//        final TextInputLayout inText = findViewById(R.id.name_text_input);


        Button button = findViewById(R.id.save_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean noErrors = true;
//                if(inText.getEditText().getText().toString().isEmpty()){
//                    inText.setError(getResources().getString(R.string.error_string));
//                }

                for (TextInputLayout textInputLayout : textInputLayouts) {
                    String editTextString = textInputLayout.getEditText().getText().toString();
                    if (editTextString.isEmpty()) {
                        textInputLayout.setError(getResources().getString(R.string.error_string));
                        noErrors = false;
                    } else {
                        textInputLayout.setError(null);
                    }
                }

                if (noErrors) {
                    // All fields are valid!
                    Snackbar.make(rootView, "Data saved", Snackbar.LENGTH_SHORT)
                            .show();
                    name.setText("");
                    email.setText("");
                    password.setText("");
                    name.requestFocus();
                }
            }
        });
//        closing material design

        drawerLayoutToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();

            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

        };
        drawerLayout.addDrawerListener(drawerLayoutToggle);
        drawerLayoutToggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.icon, getTheme());
        drawerLayoutToggle.setHomeAsUpIndicator(drawable);
        drawerLayoutToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        bar.addAction(R.drawable.awsb_ic_edit_animated, "Compose");
//
//        bar.setActionItemClickListener(new AwesomeBar.ActionItemClickListener() {
//            @Override
//            public void onActionItemClicked(int position, ActionItem actionItem) {
//                Toast.makeText(getBaseContext(), actionItem.getText()+" clicked", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        //bar.addOverflowItem("overflow 1");
//        //bar.addOverflowItem("overflow 2");
//        bar.setOverflowActionItemClickListener(new AwesomeBar.OverflowActionItemClickListener() {
//            @Override
//            public void onOverflowActionItemClicked(int position, String item) {
//                Toast.makeText(getBaseContext(), item+" clicked", Toast.LENGTH_LONG).show();
//            }
//
//        });
//
//        bar.setOnMenuClickedListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(Gravity.START);
//            }
//        });
//
//        bar.displayHomeAsUpEnabled(false);
        ///Add menu item in navigation
//        list.addAll(Arrays.asList(menuAry));

        listView = navigationView.findViewById(R.id.menu_list);


    }

    @Override
    public void passInfo(String message) {
//        Intent in = new Intent(ActivitySpeaker.this, DetailActivity.class);
//        in.putExtra("link", message);
//        startActivity(in);
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerLayoutToggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerLayoutToggle.onConfigurationChanged(newConfig);
    }

    public void callAPI(final Context context, final View view) {
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
                        list = response.body().getItems();

                        /**
                         * Binding that List to Adapter
                         */
                        listView.setAdapter(new NavMenuAdapter(AwesomeBar2.this, list));

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
    public void textWatcher(List<TextInputLayout> textInputLayouts){
//        for(TextInputEditText text )

    }
}
