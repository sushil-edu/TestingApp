package in.kestone.testingapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class LocationHelper {
    private static final String TAG = "LocationHelper";
    private static final int UPDATE_INTERVAL = 4000;
    private static final int FASTEST_UPDATE_INTERVAL = 2000;



    public static boolean checkLocationPermissions(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
}
