package com.example.carmensires.cityplanner;

import android.location.Address;
import android.location.Geocoder;

import java.util.ArrayList;
import java.util.List;

public class PlacesDistance {

    private static ArrayList<PlaceDist> listPlaces=new ArrayList<>();

    public static ArrayList<PlaceDist> getListPlaces() {
        return listPlaces;
    }

    public static void setListPlaces(ArrayList<PlaceDist> listPlaces) {
        PlacesDistance.listPlaces = listPlaces;
    }

    public static void addPlace(PlaceDist place){
        listPlaces.add(place);
    }

    public static ArrayList<PlaceDist> getOptimalRoute(ArrayList<PlaceDist> listPlaces)
    {
        ArrayList<PlaceDist> initialRoute = getShortestRoute(listPlaces, listPlaces.get(0));
        return getShortestRoute(initialRoute,initialRoute.get(Search.getnPlaces()-1));
    }

    public static ArrayList<PlaceDist> getShortestRoute(ArrayList<PlaceDist> currList, PlaceDist initialPlace)
    {
        ArrayList<PlaceDist> newList = new ArrayList<>();
        newList.add(initialPlace);
        int p = currList.indexOf(initialPlace);
        currList.remove(p);
        PlaceDist nextPlace;
        int size = currList.size();
        for(int i=0;i<size-1;i++)
        {
            nextPlace = getNearestPlace(currList, initialPlace);
            int pos=currList.indexOf(nextPlace);
            newList.add(nextPlace);
            currList.remove(pos);
            initialPlace = nextPlace;
        }
        newList.add(currList.get(0));
        return newList;
    }

    public static PlaceDist getNearestPlace(ArrayList<PlaceDist> remainingList,PlaceDist origin)
    {
        PlaceDist next = remainingList.get(0);
        double distanceMin = getDistance(origin,remainingList.get(0));

        for(int i=0;i<remainingList.size();i++)
        {
            double distance = getDistance(origin,remainingList.get(i));
            if(distance<distanceMin)
            {
                distanceMin = distance;
                next = remainingList.get(i);
            }

        }
        return next;
    }

    public static double getDistance(PlaceDist pl1, PlaceDist pl2)
    {
        return Math.sqrt(Math.pow((pl1.getLat()-pl2.getLat()),2)+Math.pow((pl1.getLon()-pl2.getLon()),2));
    }

}
