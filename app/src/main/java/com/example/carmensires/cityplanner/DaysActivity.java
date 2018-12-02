package com.example.carmensires.cityplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//the purpose of this activity is to introduce the number of days to stay in the city

public class DaysActivity extends AppCompatActivity {

    private static EditText nDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
    }

    public void onClick(View view) {

        this.nDays = findViewById(R.id.nDays);
        if (getNDays()<=20 && getNDays()>0) {
            //save the number of days and open the next activity: PlacesActivity
            Search.setnDays(getNDays());
            Intent i = new Intent(this, PlacesActivity.class);
            startActivity(i);
            finish();
        } else if(getNDays()>20) {
            //Make sure the user does not introduce more than 20 days, because we don't to overload the app
            Toast.makeText(DaysActivity.this, "Max. is 20 days", Toast.LENGTH_LONG).show();
        } else{
            //make sure the user doesn't leave the field empty or puts a zero on it
            Toast.makeText(DaysActivity.this, "Invalid data", Toast.LENGTH_LONG).show();
        }

    }

    public static int getNDays(){
        int n=0;
        if(!nDays.getText().toString().equals(""))
            n=Integer.parseInt(nDays.getText().toString());
        return n;
    }
}
