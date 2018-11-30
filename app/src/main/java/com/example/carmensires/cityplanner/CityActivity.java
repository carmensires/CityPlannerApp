package com.example.carmensires.cityplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
            Search.setCity(getCity());
            Intent i = new Intent(this, DaysActivity.class);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(CityActivity.this, "You have to introduce a city", Toast.LENGTH_LONG).show();
        }

    }

    public static String getCity(){
        return city.getText().toString();
    }




}
