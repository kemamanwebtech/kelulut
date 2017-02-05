package org.kemaman.kelulut_main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kemaman.kelulut_main.utils.AppConfig;
import org.kemaman.kelulut_main.utils.AppController;

import java.util.HashMap;
import java.util.Map;

public class DiscoverActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = DiscoverActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);





        FloatingActionButton fabLearn = (FloatingActionButton) findViewById(R.id.fab_learn);
        fabLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LearnActivity.class);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        FloatingActionButton fabReport = (FloatingActionButton) findViewById(R.id.fab_report);
        fabReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        FloatingActionButton fabDiscover = (FloatingActionButton) findViewById(R.id.fab_discover);
        fabDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DiscoverActivity.class);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        FloatingActionButton fabExam = (FloatingActionButton) findViewById(R.id.fab_exam);
        fabExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GetQuestionActivity.class);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Cyberjaya by default and move the camera
        LatLng cyberjaya = new LatLng(2.927765, 101.641136);
        // mMap.addMarker(new MarkerOptions().position(cyberjaya).title("This is MMU Cyberjaya"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((cyberjaya), 14.0f));

        getSetupMarkers();

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(DiscoverActivity.this, ViewImageActivity.class);
                intent.putExtra("id", marker.getSnippet());
                startActivity(intent);
            }
        });
    }


    public void getSetupMarkers(){

        HashMap<String, String> mRequestParams = new HashMap<String, String>();
        mRequestParams.put("approved","true");

        JSONArray dummy = new JSONArray ();

        // Tag used to cancel the request
        String tag_string_getLocation = "req_getLocation";

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, AppConfig.URL_GETLOCATION,
                dummy,
                new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "Login Response: " + response.toString());

                try {
                    for (int i = 0; i < response.length(); ++i) {
                        JSONObject rec = response.getJSONObject(i);

                        // get details  of each location
                        int id = rec.getInt("id");
                        String location = rec.getString("location");
                        String image_desc = rec.getString("image_desc");

                        LatLng newLocation = getLatLng(location);

                        // add markers to maps
                        mMap.addMarker(new MarkerOptions()
                                .position(newLocation)
                                .title(image_desc)
                                .snippet(Integer.toString(id)))
                                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bee_marker));

                        // TODO : redesign marker -> .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_string_getLocation);
    }

    // get location (lat & long) from String location
    public LatLng getLatLng(String location){
        String[] latlong =  location.split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);
        LatLng result = new LatLng(latitude, longitude);

        return result;
    }


}
