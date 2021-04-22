package com.example.coba_group4;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coba_group4.fragment.EmergencyFragment;
import com.example.coba_group4.fragment.ForumFragment;
import com.example.coba_group4.fragment.ForumMessagingFragment;
import com.example.coba_group4.fragment.HomepageFragment;
import com.example.coba_group4.fragment.MapFragment;
import com.example.coba_group4.fragment.OccurrenceDataFragment;
import com.example.coba_group4.fragment.ProfilePageFragment;
import com.example.coba_group4.fragment.SearchFragment;
import com.example.coba_group4.fragment.ReportOccurrenceFragment;
import com.google.android.material.navigation.NavigationView;

// Homepage
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    //Initialize variable
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.nav_homepage:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomepageFragment()).commit();
                break;
            case R.id.nav_reportOccurrence:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ReportOccurrenceFragment()).commit();
                break;
            case R.id.nav_911:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EmergencyFragment()).commit();
                break;
            case R.id.nav_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SearchFragment()).commit();
                break;
            case R.id.nav_forums:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ForumFragment()).commit();
                break;
            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ForumMessagingFragment()).commit();
                break;
            case R.id.nav_map:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MapFragment()).commit();
                break;
            case R.id.nav_occurrenceData:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new OccurrenceDataFragment()).commit();
                break;
            case R.id.nav_profilePage:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfilePageFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed()
    {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}