package co.movil.computacion.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Date;

import co.movil.computacion.R;
import co.movil.computacion.model.Position;


public class Map extends FragmentActivity implements  OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, LocationListener, GoogleApiClient.OnConnectionFailedListener,GoogleMap.OnInfoWindowClickListener {


    FusedLocationProviderClient fusedLocationProviderClient;
    private GoogleMap mMap;
    final int  ACCESS_FINE_LOCATION = 112;
    LocationRequest locationRequest;
    private LocationCallback locationCallback;
    Location last = null;
    MarkerOptions markerOptions;
    Marker marker;
    private LayoutInflater mInflater;

    public static final String SHARED_PREF_NAME = "JSON";
    public static final String COMPANY_DETAILS_STRING = "REGISTROS ";
    //public static final String USER_DETAILS_STRING = "USER_DETAIL";
    public static boolean inicio = true;
    List<Position> listPosition = new ArrayList<>();
    private SensorEventListener sensorEventListener;
    private SensorManager sensorManager;
    List<Location> locations;
    List<Marker> markers;
    Circle c1;
    double radio = 1000;
    int light = 5000;

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(this, DetailEvent.class);
        intent.putExtra("Title", marker.getTitle());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        requestPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION,  ACCESS_FINE_LOCATION );

        Sensor();
        locations = GetEventsLocation();



    }

    private void Sensor(){
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (mMap != null )
                    if(event.sensor.getType()==Sensor.TYPE_LIGHT) {
                        if (event.values [0] < light ){
                            Log. i (" MAPS", "DARK MAP " + event.values[0]);
                            mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getApplicationContext() , R.raw.dark));
                        }
                        else
                        {
                            Log.i(" MAPS", "LIGHT MAP " + event.values[0]);
                            mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getApplicationContext() , R.raw.silver));
                        }
                    }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        final Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }



    public void requestPermission(Context context, String permission, int id) {
        boolean result = false;
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            ExecuteAction(id,true);
        }else{
            ActivityCompat.requestPermissions(this, new String[]{permission}, id);
        }
    }

    private void ExecuteAction(int requestCode, boolean resultRequestPermission){
        switch (requestCode) {
            case ACCESS_FINE_LOCATION: {
                if (resultRequestPermission) {
                    RequestLocationUpdates();
                } else {
                    Intent intent = new Intent(getApplicationContext(), Event.class );
                    startActivity(intent);
                }
                return;
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean result = false;
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            result = true;
        }
        ExecuteAction(requestCode, result);
    }

    private void RequestLocationUpdates(){
        CreateLocationRequest();
        CreateLocationCallBack();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }


    private void CreateLocationRequest(){
        long time = 2000;
        locationRequest = new LocationRequest();
        locationRequest.setInterval(time);
        locationRequest.setFastestInterval(time);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
    private void CreateLocationCallBack(){

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        if (inicio) {
                            ShowMarker(location, true);
                            inicio = false;
                        } else {
                            ShowMarker(location, false);
                        }
                        Position position = new Position(location.getLatitude(), location.getLongitude(), new Date(System.currentTimeMillis()));
                        SavePosition(position);
                    }
                }
            }
        };

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(this);
      /*  CustomInfoWindow customInfoWindow = new CustomInfoWindow(getLayoutInflater());
        mMap.setInfoWindowAdapter(customInfoWindow);*/
    }

    private void ShowMarker(Location location, Boolean last){

        LatLng current = new LatLng(location.getLatitude(), location.getLongitude());

        if(last || marker == null   ){

            markerOptions = new MarkerOptions();
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.current));
            markerOptions.title("Posición");
            marker =  mMap.addMarker(markerOptions.position(current));
            marker.setSnippet("Hola mundo");
            marker.showInfoWindow();
        }
        else{
            marker.setPosition(current);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current,15));
        AddCircle(current);
        List<Location> nearbyLocations = GetEventsByDistance(location,locations,radio);
        CreateMarkers(nearbyLocations);
    }

    private void RemoveMarkers(List<Marker> markers) {
        for (Marker marker: markers) {
            marker.remove();
        }
    }

    private void CreateMarkers(List<Location> locations){

        if(markers != null){
            RemoveMarkers(markers);
        }
        markers = new ArrayList<>();

        for (Location location: locations) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions1 = new MarkerOptions();
            markerOptions1.icon(BitmapDescriptorFactory.fromResource(R.drawable.favorite));
            markerOptions1.title(location.getProvider());
            markerOptions1.position(latLng);
            Marker marker1 = mMap.addMarker(markerOptions1);
            marker1.showInfoWindow();
            markers.add(marker1);
        }

    }

    private void AddCircle(LatLng current){
        CircleOptions  circleOptions = new CircleOptions()
                .center(current)
                .radius(radio) //metros
                .strokeWidth(10)
                .strokeColor(0xff000000)
                .fillColor(0x00000000)
                .clickable(true);

        if(c1 != null){
            c1.remove();
        }

        c1 = mMap.addCircle(circleOptions);
    }

    private List<Location> GetEventsLocation(){

        String [] Events =  {"Test 01","Test 02", "Maria Semples", "The Vegitarian", "The Wild Robot"};
        float [] latitude =  {4.6889f,4.6915f,4.6981f,4.7047f,4.7069f};
        float [] longitude =  {-74.0568f,-74.0564f,-74.0553f,-74.0541f,-74.0539f};

        List<Location> eventsLocation = new ArrayList<>();

        for(int i=0;i<latitude.length;i++){
            Location location = new Location(Events[i]);
            location.setLatitude(latitude[i]);
            location.setLongitude(longitude[i]);
            eventsLocation.add(location);
        }
        return  eventsLocation;
    }
    private List<Location> GetEventsByDistance(Location current, List<Location> locations, double distance){
        float value = 0;
        List<Location> locationsFilter = new ArrayList<>();


        for (Location location: locations) {
            value =  current.distanceTo(location);

            if(value < distance ){
                locationsFilter.add(location);
            }
        }
        return locationsFilter;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //RequestPermission();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(fusedLocationProviderClient != null){
            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(sensorEventListener);

    }

    private void SavePosition(Position position){

        listPosition.add(position);
        Position myLocation = new Position();
        Writer output = null;
        String filename= "locations.json";
        Gson gson = new Gson();
        String json = gson.toJson(listPosition);

        try {
            File file = new File(getBaseContext().getExternalFilesDir(null), filename);
            Log.i("LOCATION", "Ubicacion de archivo: "+file);
            output = new BufferedWriter(new FileWriter(file,false));
            output.write(json);
            output.close();

        } catch (Exception e) {

        }
    }

    public static Position GetPosition(Context mContext){
        SharedPreferences mPrefs = mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = mPrefs.getString(COMPANY_DETAILS_STRING, "");
        if(json.equalsIgnoreCase("")){
            return null;
        }
        Position obj = gson.fromJson(json, Position.class);
        return obj;
    }


}
