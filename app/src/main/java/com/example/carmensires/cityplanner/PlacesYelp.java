package com.example.carmensires.cityplanner;

import android.util.Log;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.SearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class PlacesYelp {

    private static ArrayList<String> list = new ArrayList<>();

    public static ArrayList<String> getList() {
        return list;
    }

    public static void setList(ArrayList<String> list) {
        PlacesYelp.list = list;
    }

    public static void getYelpPlaces()
    {
        //create a thread so that we don't make the query from the main process
        Thread t = new Thread(new Runnable() {
            public void run() {


                Log.d("THREAD","launched");

                YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
                YelpFusionApi yelpFusionApi = null;

                //when initializing the API, we indicate the token
                try {
                    yelpFusionApi = apiFactory.createAPI("aMbFl-deJHchPQOyqqlWlW2rjMoTFAHLumHzFphGyFMkMCMj199UWm7SkmtjX0jnuf_x6qomiVKDkhfGaAZ3EIr71093SuPQEa-pQq_F33snaWqOed5y2m0jnRzvW3Yx");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Map<String, String> params = new HashMap<>();

                //gput general parameters to make the query
                params.put("term", "Things to do");
                params.put("location",Search.getCity());
                params.put("sort_by","best_match");

                Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(params);
                Response<SearchResponse> response = null ;

                //get the response from the YELP API
                try {
                    response = call.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //add the places got in the response to a list
                for(Business bussiness:response.body().getBusinesses()){
                    Log.d("RESPONSE", bussiness.getName());
                    list.add(bussiness.getName());
                    Log.d("PLACES IN THR", String.valueOf(PlacesYelp.getList()));
                }
            }
        });
        t.start();

        //wait for the thread to finish before going on, so that all the values are correctly stored
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void restartList(){
        list = new ArrayList<>();
    }
}
