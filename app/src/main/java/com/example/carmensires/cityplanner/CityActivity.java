package com.example.carmensires.cityplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


//the purpose of this activity is to introduce the name of the city

public class CityActivity extends AppCompatActivity {

    private static EditText city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
    }

    public void onClick(View view) {

        this.city = findViewById(R.id.city);
        if (!getCity().equals("")) {
            //save the name of the city and open the next activity: DaysActivity
            Search.setCity(getCity());
            Intent i = new Intent(this, DaysActivity.class);
            startActivity(i);
            finish();
        } else {
            //make sure the user doesn't leave the city field empty
            Toast.makeText(CityActivity.this, "You have to introduce a city", Toast.LENGTH_LONG).show();
        }

    }

    public static String getCity(){
        return city.getText().toString();
    }




}
