package com.example.carmensires.cityplanner;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

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

        // Add a marker in Sydney and move the camera


        Geocoder geo = new Geocoder(this);
        List<Address> addressCurr = null;

        //initialize a li
        //        Geocoder geo = new Geocoder(this);
        //        List<Address> addressCurr = null;
        //
        //        //initializest of places with their latitude and longitude
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

        //setMarkersAndLines(PlacesDistance.getListPlaces());
        setMarkersAndLines(PlacesDistance.getOptimalRoute(PlacesDistance.getListPlaces()));

        int middle = Search.getnPlaces()/2;

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(new LatLng(PlacesDistance.getListPlaces().get(0).getLat(),PlacesDistance.getListPlaces().get(0).getLon()))
                .zoom(12)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

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


}
