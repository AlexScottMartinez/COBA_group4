package com.example.coba_group4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class Emergency extends AppCompatActivity {

    //Initialize variable
    //DrawerLayout drawerLayout;
    Button m911;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        //Assign variable

        m911 = findViewById(R.id.btn911);
    }


}