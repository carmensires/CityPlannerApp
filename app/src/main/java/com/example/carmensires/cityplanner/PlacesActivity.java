package com.example.carmensires.cityplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//the purpose of this activity is to introduce the number of places to see in the city

public class PlacesActivity extends AppCompatActivity {

    private static EditText nPlaces;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
    }

    public void onClick(View view) {

        this.nPlaces = findViewById(R.id.nPlaces);
        if(getNPlaces()<=20 && getNPlaces()>0) {
            if(getNPlaces()>=Search.getnDays()) {
                //save the number of places and open the next activity: MapsActivity
                Search.setnPlaces(getNPlaces());
                //make the query to the YELP API
                PlacesYelp.getYelpPlaces();
                Intent i = new Intent(this, MapsActivity.class);
                startActivity(i);
                finish();
            } else{
                //make sure the user introduces more places than days (because that's the only way we can distribute them correctly)
                Toast.makeText(PlacesActivity.this, "Number of places has to be bigger than number of days!", Toast.LENGTH_LONG).show();
            }
        }else if(getNPlaces()>20){
            //Make sure the user does not introduce more than 20 places, because we don't to overload the app
            Toast.makeText(PlacesActivity.this, "Max. number of places is 20", Toast.LENGTH_LONG).show();
        } else {
            //make sure the user doesn't leave the field empty or puts a zero on it
            Toast.makeText(PlacesActivity.this, "Invalid data", Toast.LENGTH_LONG).show();
        }

    }

    public static int getNPlaces(){
        int n=0;
        if(!nPlaces.getText().toString().equals(""))
            n= Integer.parseInt(nPlaces.getText().toString());
        return n;
    }

}
