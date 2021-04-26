package com.example.coba_group4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PinActivity extends AppCompatActivity {
    TextView markerTime, marketLocation, markerType, markerDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        markerType = findViewById(R.id.textView3);
        String title=getIntent().getStringExtra("title");
        markerType.setText(title);
        marketLocation = findViewById(R.id.textView5);
        String Location=getIntent().getStringExtra("location");
        marketLocation.setText(Location);
        markerTime = findViewById(R.id.textView7);
        String Time = getIntent().getStringExtra("Time");
        markerTime.setText(Time);
        markerDescription = findViewById(R.id.textView9);
        String Description = getIntent().getStringExtra("Description");
        markerDescription.setText(Description);
    }
}