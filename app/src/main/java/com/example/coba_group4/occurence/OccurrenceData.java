package com.example.coba_group4.occurence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.example.coba_group4.Emergency;
import com.example.coba_group4.Forum;
import com.example.coba_group4.ForumMessaging;
import com.example.coba_group4.MainActivity;
import com.example.coba_group4.Map;
import com.example.coba_group4.ProfilePage;
import com.example.coba_group4.R;
import com.example.coba_group4.Search;

public class OccurrenceData extends AppCompatActivity
{

    //Initialize variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occurrence_data);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view)
    {
        //Open drawer
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view)
    {
        //Close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickHomepage(View view)
    {
        //Redirect activity to homepage
        MainActivity.redirectActivity(this, MainActivity.class);
    }

    public void ClickReportOccurrence(View view)
    {
        //Redirect activity to Report Occurrence
        MainActivity.redirectActivity(this, ReportOccurrence.class);
    }

    public void Click911(View view)
    {
        //Redirect activity to 911
        MainActivity.redirectActivity(this, Emergency.class);
    }

    public void ClickSearch(View view)
    {
        //Recreate activity
        MainActivity.redirectActivity(this, Search.class);
    }

    public void ClickForum(View view)
    {
        //Redirect activity to Forums
        MainActivity.redirectActivity(this, Forum.class);
    }

    public void ClickChat(View view)
    {
        //Redirect activity to Chat
        MainActivity.redirectActivity(this, ForumMessaging.class);
    }

    public void ClickMap(View view)
    {
        //Redirect activity to Maps
        MainActivity.redirectActivity(this, Map.class);
    }

    public void ClickOccurrenceData(View view)
    {
        //Recreate activity
        recreate();
    }

    public void ClickProfilePage(View view)
    {
        //Redirect activity to Profile Page
        MainActivity.redirectActivity(this, ProfilePage.class);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //Close drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}