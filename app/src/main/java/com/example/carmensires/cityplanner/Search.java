package com.example.carmensires.cityplanner;

public class Search {

    private static String city;
    private static int nDays;
    private static int nPlaces;

    public Search(){
    }


    public Search(String city, int nDays, int nPlaces){
        this.city = city;
        this.nDays = nDays;
        this.nPlaces = nPlaces;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        Search.city = city;
    }

    public static int getnDays() {
        return nDays;
    }

    public static void setnDays(int nDays) {
        Search.nDays = nDays;
    }

    public static int getnPlaces() {
        return nPlaces;
    }

    public static void setnPlaces(int nPlaces) {
        Search.nPlaces = nPlaces;
    }
}
