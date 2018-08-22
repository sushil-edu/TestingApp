package in.kestone.testingapp.SqliteEx;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import in.kestone.testingapp.MainActivity;
import in.kestone.testingapp.R;

public class Main2Activity extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    Button b1;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);

        b1=(Button)findViewById(R.id.button);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        sharedpreferences.edit().clear().commit();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n  = ed1.getText().toString();
                String ph  = ed2.getText().toString();
                String e  = ed3.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, n);
                editor.putString(Phone, ph);
                editor.putString(Email, e);
                editor.commit();
                Toast.makeText(Main2Activity.this,"Thanks",Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.buttonGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreferences = PreferenceManager.getDefaultSharedPreferences(Main2Activity.this);
                String name = sharedpreferences.getString(Name, "");
                Toast.makeText(Main2Activity.this, ""+name, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        Log.e("On ", "Stop"+" "+isAppIsInBackground(this));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        sharedpreferences.edit().clear().apply();
        Log.e("On ", "Destroy"+" "+isAppIsInBackground(this));
        finishAndRemoveTask();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
        Log.e("On ", "Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        Log.e("On ", "Resume");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        Log.e("On ", "Pause");
        this.finishAffinity();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
        Log.e("On ", "ReStart");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }
}
