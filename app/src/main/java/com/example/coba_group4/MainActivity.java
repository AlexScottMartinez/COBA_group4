package com.example.coba_group4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import com.example.coba_group4.occurence.ReportOccurrence;

// Homepage
public class MainActivity extends AppCompatActivity
{
    //Initialize variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view)
    {
        //Open drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout)
    {
        //Open drawer Layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view)
    {
        //Close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout)
    {
        //Close drawer layout
        //Check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            //When drawer is open
            //Close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHomepage(View view)
    {
        //Recreate activity
        recreate();
    }

    public void ClickReportOccurrence(View view)
    {
        //Redirect activity to Report Occurrence
        redirectActivity(this, ReportOccurrence.class);
    }

    public void Click911(View view)
    {
        //Redirect activity to 911
        redirectActivity(this, Emergency.class);
    }

    public void ClickSearch(View view)
    {
        //Redirect activity to Search
        redirectActivity(this, Search.class);
    }

    public void ClickForum(View view)
    {
        //Redirect activity to Forums
        redirectActivity(this, Forum.class);
    }

    public void ClickChat(View view)
    {
        //redirect activity to Forum Messaging
        redirectActivity(this, ForumMessaging.class);
    }

    public void ClickMap(View view)
    {
        //Redirect activity to Map
        redirectActivity(this, Map.class);
    }

    public void ClickOccurrenceData(View view)
    {
        //Redirect activity to Occurrence Data
        redirectActivity(this, OccurrenceData.class);
    }

    public void ClickProfilePage(View view)
    {
        //Redirect activity to Profile Page
        redirectActivity(this, ProfilePage.class);
    }

    public static void redirectActivity(Activity activity, Class aClass)
    {
        //Initialize intent
        Intent intent = new Intent(activity, aClass);
        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }
}