package co.movil.computacion.controller;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import co.movil.computacion.R;
import co.movil.computacion.model.ModelEvent;

public class Map extends FragmentActivity implements OnMapReadyCallback {

    final static int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    private GoogleMap mMap;
    private Context context;
    private Button details;
    List<ModelEvent> eventList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        context = this;

        requestPermission(this, Manifest.permission.ACCESS_FINE_LOCATION,
                "Se necesita acceder a los ubicacion", MY_PERMISSIONS_REQUEST_LOCATION);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        SetEventGallery();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(4.65, -74.15)));

        for ( ModelEvent event: eventList)
        {
            mMap.addMarker( new MarkerOptions().position(event.getLocation()));
        }

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                Log.i("Im here:", "Someone clicked a marker");
                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                final ModelEvent event = findEvent( marker.getPosition() );
                View v = getLayoutInflater().inflate(R.layout.marker_window,null );
                TextView title = v.findViewById(R.id.tvEventTitle);
                TextView date = v.findViewById(R.id.tvEventDate);
                details = v.findViewById(R.id.btnEventDetail);
                title.setText(event.getTitle());
                date.setText(event.getEventDate().getTime().toString());

                return v;
            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                ModelEvent event = findEvent(latLng);
                if ( event != null )
                {
                    // Creating an instance of MarkerOptions to set position
                    MarkerOptions markerOptions = new MarkerOptions();

                    // Setting position on the MarkerOptions
                    markerOptions.position(latLng);

                    // Animating to the currently touched position
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                    // Adding marker on the GoogleMap
                    Marker marker = mMap.addMarker(markerOptions);

                    // Showing InfoWindow on the GoogleMap
                    marker.showInfoWindow();
                }
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                final ModelEvent event = findEvent( marker.getPosition() );
                details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent( null, DetailEvent.class);
                        Log.i("Im here:", "Someone clicked the button");
                        intent.putExtra("Title",event.getTitle());
                        intent.putExtra("Description",event.getDescription());
                        intent.putExtra("Thumbnail",event.getThumbnail());
                        // start the activity
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void requestPermission(Activity context, String permission, String explanation, int requestId ){
        if (ContextCompat.checkSelfPermission(context,permission)!= PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(context,permission)) {
                Toast.makeText(context, explanation, Toast.LENGTH_LONG).show();
            }
            ActivityCompat.requestPermissions(context, new String[]{permission}, requestId);
        }
    }

    private void SetEventGallery() {
        eventList = new ArrayList<>();
        eventList.add(new ModelEvent("The Vegitarian","Categorie Book","Description for The Vegitarian",R.drawable.thevigitarian, new GregorianCalendar(2020, 4, 4), new LatLng(4.55, -74.05)));
        eventList.add(new ModelEvent("The Wild Robot","Categorie Book","Description for The Wild Robot ",R.drawable.thewildrobot, new GregorianCalendar(2020, 4 ,10), new LatLng(4.65, -74.15)));
        eventList.add(new ModelEvent("Maria Semples","Categorie Book","Description for Maria Semples",R.drawable.mariasemples, new GregorianCalendar(2020, 4, 25), new LatLng(4.50, -74.05)));
    }

    private ModelEvent findEvent ( LatLng location )
    {
        ModelEvent event = null;
        for ( ModelEvent e: eventList )
        {
            if ( e.getLocation().equals(location) )
            {
                event = e;
            }
        }
        return event;
    }
}
