package com.example.carmensires.cityplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
                Search.setnPlaces(getNPlaces());
                PlacesYelp.getYelpPlaces();
                Intent i = new Intent(this, MapsActivity.class);
                startActivity(i);
                finish();
            } else{
                Toast.makeText(PlacesActivity.this, "Number of places has to be bigger than number of days!", Toast.LENGTH_LONG).show();
            }
        }else if(getNPlaces()>20){
            Toast.makeText(PlacesActivity.this, "Max. number of places is 20", Toast.LENGTH_LONG).show();
        } else {
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
