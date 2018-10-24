
package in.kestone.testingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GetLocation extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_getlocation );
    }
}
