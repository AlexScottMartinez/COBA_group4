package com.example.coba_group4.forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.coba_group4.MainActivity;
import com.example.coba_group4.R;
import com.example.coba_group4.database.ForumDB;


import java.util.ArrayList;

public class ForumActivity extends AppCompatActivity
{
    //Initialize variable
    private DrawerLayout drawerLayout;
    ListView forum_List;
    ForumDB forumDB;
    ArrayList<Forum> forums;
    ForumListAdapter forumsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);


        forum_List = findViewById(R.id.forum_list);
        forumDB = new ForumDB();
        forums = new ArrayList<>();
        loadDataInList();

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                finish();
                startActivity(new Intent(getApplicationContext(), CreateForum.class));
            }
        });

    }

    private void loadDataInList()
    {
        forums = forumDB.getAllForums();
        forumsListAdapter = new ForumListAdapter(this, forums);
        forum_List.setAdapter(forumsListAdapter);
        forumsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}
