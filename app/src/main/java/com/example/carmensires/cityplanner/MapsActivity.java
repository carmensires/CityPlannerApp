package com.example.carmensires.cityplanner;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<String> places = new ArrayList<>();
    //list of colors for the different days
    private int[] listColors = {Color.RED,Color.BLUE,Color.MAGENTA,Color.GREEN,Color.CYAN,Color.YELLOW,
            Color.RED,Color.BLUE,Color.MAGENTA,Color.GREEN,Color.CYAN,Color.YELLOW,
            Color.RED,Color.BLUE,Color.MAGENTA,Color.GREEN,Color.CYAN,Color.YELLOW,
            Color.RED,Color.BLUE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Geocoder geo = new Geocoder(this);
        List<Address> addressCurr = null;
        //for each place, gets the location and saves it to a list in PlacesDistance class
        for(int i=0; i<Search.getnPlaces();i++)
        {
            try {
                addressCurr = geo.getFromLocationName(PlacesYelp.getList().get(i) + " " + Search.getCity(), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            PlaceDist place = new PlaceDist(PlacesYelp.getList().get(i),addressCurr.get(0).getLatitude(),addressCurr.get(0).getLongitude());
            PlacesDistance.addPlace(place);

        }

        //set markers and routes using the optimal route
        setMarkersAndLines(PlacesDistance.getOptimalRoute(PlacesDistance.getListPlaces()));

        //set camera in the first place of the list
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(new LatLng(PlacesDistance.getListPlaces().get(0).getLat(),PlacesDistance.getListPlaces().get(0).getLon()))
                .zoom(12)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    //set markers and routes between places with the optimal route and with different colors for each day
    public void setMarkersAndLines(ArrayList<PlaceDist> listPlaces)
    {

        for(int j=0;j<Search.getnDays();j++)
        {
            ArrayList<PlaceDist> placesDay = new ArrayList<>();
            int colorDay;

                colorDay=listColors[j];

                //create the list of places to visit on day j
                int limitEnd = (j+1)*Search.getnPlaces()/Search.getnDays();
                if (j == Search.getnDays()-1)
                    limitEnd=Search.getnPlaces();
                for(int k=j*Search.getnPlaces()/Search.getnDays();k<limitEnd;k++)
                {
                    placesDay.add(listPlaces.get(k));
                }

                LatLng latLngCurr, latLngPrev=null;

                //for each day put markers and draw lines between them
                for(int i=0; i<placesDay.size();i++)
                {
                        latLngCurr = new LatLng(placesDay.get(i).getLat(),placesDay.get(i).getLon());
                        Log.d("LATLONG",placesDay.get(i)+", Lat: "+latLngCurr.latitude+", Long: "+latLngCurr.longitude);
                        mMap.addMarker(new MarkerOptions().position(latLngCurr).title(placesDay.get(i).getName()));
                        if (i != 0) {
                            Polyline line = mMap.addPolyline(new PolylineOptions()
                                    .add(latLngCurr, latLngPrev)
                                    .width(5)
                                    .color(colorDay));
                        }
                        latLngPrev = latLngCurr;

                }

        }


    }

    public void onClick(View view)
    {
        //when the "New search" button is pressed, restart the data
        restartActivities();
        Intent i = new Intent(this, CityActivity.class);
        startActivity(i);
        finish();
    }

    public void restartActivities()
    {
        Search.restartSearch();
        PlacesDistance.restartListPlaces();
        PlacesYelp.restartList();
    }


}
