package com.example.carmensires.cityplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
            Search.setnDays(getNDays());
            Intent i = new Intent(this, PlacesActivity.class);
            startActivity(i);
            finish();
        } else if(getNDays()>20) {
            Toast.makeText(DaysActivity.this, "Max. is 20 days", Toast.LENGTH_LONG).show();
        } else{
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
