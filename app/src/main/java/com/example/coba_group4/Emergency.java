package com.example.coba_group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;


import com.example.coba_group4.occurence.ReportOccurrence;

public class Emergency extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
    }


    public void Click911(View view)
    {
        //Recreate activity
        recreate();
    }

}