package com.example.coba_group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if (authenticate() == true)
        {
            /*
                If the the login is authenticated when logged in then the screen will
                the reported occurrences within a mile radius of user.

            */
        }
        else {
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }

    private boolean authenticate()
    {
        return userLocalStore.getUserLoggedIn();
    }
}