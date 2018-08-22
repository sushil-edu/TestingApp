package in.kestone.testingapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import in.kestone.testingapp.RetrofitExp.model.ApiService;
import in.kestone.testingapp.RetrofitExp.model.Contact;
import in.kestone.testingapp.RetrofitExp.model.ContactList;
import in.kestone.testingapp.RetrofitExp.model.RetroClient;
import in.kestone.testingapp.Utility.PermissionHelperJava;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private View parentView;

    private ArrayList<Contact> contactList;
    private MyContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Array List for Binding Data from JSON to this List
         */
        contactList = new ArrayList<>();

        parentView = findViewById(R.id.parentLayout);

        /**
         * Getting List and Setting List Adapter
         */
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(parentView, contactList.get(position).getName() + " => " + contactList.get(position).getPhone().getHome(), Snackbar.LENGTH_LONG).show();
            }
        });

        /**
         * Just to know onClick and Printing Hello Toast in Center.
         */
        Toast toast = Toast.makeText(getApplicationContext(), "string_click_to_load", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull final View view) {

                /**
                 * Checking Internet Connection
                 */
                if (new InternetConnection().isNetworkConnected()) {
                    final ProgressDialog dialog;
                    /**
                     * Progress Dialog for User Interaction
                     */
                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle("string_getting_json_title");
                    dialog.setMessage("string_getting_json_message");
                    dialog.show();

                    //Creating an object of our api interface
                    ApiService api = RetroClient.getApiService();

                    /**
                     * Calling JSON
                     */
                    Call<ContactList> call = api.getMyJSON();

                    /**
                     * Enqueue Callback will be call when get response...
                     */
                    call.enqueue(new Callback<ContactList>() {
                        @Override
                        public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                            //Dismiss Dialog
                            dialog.dismiss();

                            if(response.isSuccessful()) {
                                /**
                                 * Got Successfully
                                 */
                                contactList = response.body().getContacts();

                                /**
                                 * Binding that List to Adapter
                                 */
                                adapter = new MyContactAdapter(MainActivity.this, contactList);
                                listView.setAdapter(adapter);

                            } else {
                                Snackbar.make(parentView, "string_some_thing_wrong", Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ContactList> call, Throwable t) {
                            dialog.dismiss();
                        }
                    });

                } else {
                    Snackbar.make(parentView, "string_internet_connection_not_available", Snackbar.LENGTH_LONG).show();
                }
            }
        });


    }
    public class InternetConnection{
        private boolean isNetworkConnected() {
            ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo() != null;
        }
    }
}
