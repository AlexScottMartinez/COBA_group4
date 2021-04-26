package com.example.coba_group4.forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.coba_group4.MainActivity;
import com.example.coba_group4.R;
import com.example.coba_group4.database.ForumDB;
import com.example.coba_group4.database.OccurrenceDB;
import com.example.coba_group4.occurence.Occurrence;
import com.example.coba_group4.occurence.OccurrenceEventType;

import java.sql.Time;

public class CreateForum extends AppCompatActivity {
    //Initialize variable
    private TimePicker tmPicker;
    private DrawerLayout drawerLayout;
    private EditText Title, Description;
    private TextView errorText;
    private String timeString;
    private ForumDB forumDB = new ForumDB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createforum);
        Title = findViewById(R.id.Forum_Title);
        Description = findViewById(R.id.forum_description);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;

            case R.id.create:
                String TitleV = Title.getText().toString().trim();
                String DescriptionV = Description.getText().toString().trim();
                Forum forum = new Forum(TitleV,DescriptionV,"Test",new Time(5));
                Boolean added = forumDB.addOne(forum);
                startActivity(new Intent(getApplicationContext(), ForumActivity.class));

                break;
        }
    }

}
