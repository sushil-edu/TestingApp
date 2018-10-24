package in.kestone.testingapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ActivityLogation extends AppCompatActivity {
    double latitude, longitude;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_logation );

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient( this );
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener( this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            Log.e( "Location ", location.getLatitude() + " , " + location.getLongitude() );
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                            Geocoder geocoder;
                            List<Address> addresses;
                            geocoder = new Geocoder( ActivityLogation.this, Locale.getDefault() );


                            try {
                                addresses = geocoder.getFromLocation( latitude, longitude, 1 ); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                                String address = addresses.get( 0 ).getAddressLine( 0 ); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                String city = addresses.get( 0 ).getLocality();
                                String state = addresses.get( 0 ).getAdminArea();
                                String country = addresses.get( 0 ).getCountryName();
                                String postalCode = addresses.get( 0 ).getPostalCode();
                                String knownName = addresses.get( 0 ).getFeatureName();

                                Log.e( "Address ", address + "\n" + city + "\n" + state + "\n" + country + "\n" + postalCode + "\n" + knownName );
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } );




    }
}
