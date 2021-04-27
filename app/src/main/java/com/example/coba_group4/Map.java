package com.example.coba_group4;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

import com.example.coba_group4.database.OccurrenceDB;
import com.example.coba_group4.occurence.Occurrence;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map extends FragmentActivity implements OnMapReadyCallback
{
    ArrayList<Occurrence> occurrences;
    OccurrenceDB occurenceDB = new OccurrenceDB();
    private GoogleMap mMap;
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        occurrences = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
        occurrences = occurenceDB.getAllOccurrences();
    }
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
       List<Address> addressList = null;
       mMap = googleMap;
       Geocoder geocoder = new Geocoder(this);
        // Add a marker in Sydney and move the camera
        for(int i = 0 ; i < occurrences.size() ; i ++ )
        {
            if(occurrences.get(i).getAddress() != null)
            try{
                addressList = geocoder.getFromLocationName(occurrences.get(i).getAddress() + ", " + occurrences.get(i).getCity() + ", " +
                        occurrences.get(i).getState(), 1);
            } catch (IOException e){
                e.printStackTrace();
            }
            if( addressList != null) {
                Address address = addressList.get(0);
                LatLng Latlng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(Latlng).title(String.valueOf(i)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(Latlng));
            }
        }
        // enable clicking on pins
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String markertitle = marker.getTitle();
                Intent i = new Intent(Map.this, PinActivity.class);
                i.putExtra("title", occurrences.get(Integer.parseInt(markertitle)).getType());
                i.putExtra("location",occurrences.get(Integer.parseInt(markertitle)).getAddress() + ", " + occurrences.get(Integer.parseInt(markertitle)).getCity() + ", " +
                        occurrences.get(Integer.parseInt(markertitle)).getState() );
                i.putExtra("Time",occurrences.get(Integer.parseInt(markertitle)).getSubmittedTime()  );
                i.putExtra("Description",occurrences.get(Integer.parseInt(markertitle)).getDescription()  );
                startActivity(i);
                return false;
            }
        });
    }
}