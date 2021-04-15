package com.example.coba_group4.occurence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.text.TextUtils;

import com.example.coba_group4.Emergency;
import com.example.coba_group4.Forum;
import com.example.coba_group4.ForumMessaging;
import com.example.coba_group4.MainActivity;
import com.example.coba_group4.Map;
import com.example.coba_group4.OccurrenceData;
import com.example.coba_group4.ProfilePage;
import com.example.coba_group4.R;
import com.example.coba_group4.Search;
import com.example.coba_group4.SignUp;

import java.util.ArrayList;

public class ReportOccurrence extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;
    Spinner eventTypeDropdown;
    EditText address, date, time, description;
    OccurrenceEventType eventTypeEnum;
    final String eventTypeTitle = "Event Type";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_occurrence);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        eventTypeDropdown = findViewById(R.id.event_type_dropdown);
        ArrayList<String> types = new ArrayList<>();
        types.add(eventTypeTitle);
        types.addAll(eventTypeEnum.names());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReportOccurrence.this, android.R.layout.simple_spinner_dropdown_item, types);
        eventTypeDropdown.setAdapter(adapter);

        address = findViewById(R.id.report_address);
        date = findViewById(R.id.report_date);
        time = findViewById(R.id.report_time);
        description = findViewById(R.id.report_description);
    }

    public void ClickMenu(View view)
    {
        //Open drawer
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout)
    {
        //Open drawer Layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view)
    {
        //Close drawer
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout)
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
        //Redirect activity to Homepage
        redirectActivity(this, MainActivity.class);
    }

    public void ClickReportOccurrence(View view)
    {
        //Recreate activity
        recreate();
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

    private static void redirectActivity(Activity activity, Class aClass)
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

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.cancel_report:

                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                break;
            case R.id.add_report:

                String addressValue = address.getText().toString().trim();
                String dateValue = date.getText().toString().trim();
                String timeValue = time.getText().toString().trim();
                String descriptionValue = description.getText().toString().trim();
                String eventTypeValue = (String) eventTypeDropdown.getSelectedItem();
                boolean error = false;
                if(TextUtils.isEmpty(addressValue))
                {
                    address.setError("Address is required");
                    error = true;
                }
                if(TextUtils.isEmpty(dateValue))
                {
                    date.setError("Date is required");
                    error = true;
                }
                if(TextUtils.isEmpty(timeValue))
                {
                    time.setError("Time is required");
                    error = true;
                }
                if(TextUtils.isEmpty(descriptionValue))
                {
                    description.setError("Description is required");
                    error = true;
                }
                if(eventTypeTitle.equals(eventTypeValue))
                {
                    ((TextView)eventTypeDropdown.getSelectedView()).setError("Event Type is required");
                    error = true;
                }
                if(error)
                {
                    return;
                }
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}