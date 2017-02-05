package org.kemaman.kelulut_main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.akhgupta.easylocation.EasyLocationAppCompatActivity;
import com.akhgupta.easylocation.EasyLocationRequest;
import com.akhgupta.easylocation.EasyLocationRequestBuilder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public class LocationActivity extends EasyLocationAppCompatActivity {

    private final static String TAG = "LocationActivity";
    TextView txtLocation;
    GoogleApiClient mGoogleApiClient;
    Location location;
    PulsatorLayout pulseAnimation;
    Button btnProceed;
    String strLocation = "";
    private LocationRequest mLocationRequest;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        if (!servicesAvailable()) {
            finish();
        }

        pulseAnimation = (PulsatorLayout) findViewById(R.id.pulsator);
        pulseAnimation.start();

        txtLocation = (TextView) findViewById(R.id.last_known_location_view);
        txtLocation.setTextSize(24);
        txtLocation.setText("Scanning for your location... Did you turn on your GPS?");

        btnProceed = (Button) findViewById(R.id.btnProceed);
        btnProceed.setClickable(false);

        LocationRequest locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(5000)
                .setFastestInterval(5000);

        EasyLocationRequest easyLocationRequest = new EasyLocationRequestBuilder()
                .setLocationRequest(locationRequest)
                .setFallBackToLastLocationTime(3000)
                .build();

        requestSingleLocationFix(easyLocationRequest);
        //requestLocationUpdates(easyLocationRequest);


    }

    @Override
    public void onLocationPermissionGranted() {
    }

    @Override
    public void onLocationPermissionDenied() {
    }

    @Override
    public void onLocationReceived(Location inLocation) {
        this.location = inLocation;



        if (location != null) {

            double latDouble = location.getLatitude();
            double longDouble = location.getLongitude();


            if (i < 1) {
                new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Found your location!")
                        .setContentText("Your location is " + getLocationName(latDouble, longDouble ))
                        .show();
                i++;
            }


            txtLocation.setText("Your location is " + getLocationName(latDouble, longDouble ));

            strLocation = String.valueOf(location.getLatitude() + "," + String.valueOf(location.getLongitude()));

            btnProceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                    intent.putExtra("location", strLocation);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    finish();
                }
            });
            btnProceed.setClickable(true);
        }
    }

    @Override
    public void onLocationProviderEnabled() {
    }

    @Override
    public void onLocationProviderDisabled() {
    }










    private boolean servicesAvailable() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (ConnectionResult.SUCCESS == resultCode) {
            return true;
        }
        else {
            GooglePlayServicesUtil.getErrorDialog(resultCode, this, 0).show();
            return false;
        }
    }

    public String getLocationName(double latitude, double longitude){
        Geocoder geoCoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> list = null;
        try {
            list = geoCoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e){
            e.printStackTrace();
        }

        if (list != null & list.size() > 0) {
            Address address = list.get(0);
            String nameLocation = address.getLocality().toString();
            if (nameLocation != null) {
                return nameLocation;
            } else {
                return "unknown.";
            }
        } else {
            return "unknown.";
        }
    }
}
