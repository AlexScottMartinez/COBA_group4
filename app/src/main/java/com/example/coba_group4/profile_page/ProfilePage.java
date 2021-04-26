package com.example.coba_group4.profile_page;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coba_group4.MainActivity;
import com.example.coba_group4.R;
import com.example.coba_group4.database.UsersDB;

public class ProfilePage extends AppCompatActivity
{
        private UsersDB usersDB;
        TextView mFname, mMname, mLname, mEmail, mUsername,  mProfession, mIdnum;
        Button mUpdateBtn;
        int id_to_update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        mFname = (TextView) findViewById(R.id.pro_fname);
        mMname = (TextView) findViewById(R.id.pro_mname);
        mLname = (TextView) findViewById(R.id.pro_lname);
        mEmail = (TextView) findViewById(R.id.pro_email);
        mUsername = (TextView) findViewById(R.id.pro_username);
        mProfession = (TextView) findViewById(R.id.pro_profession);
        mIdnum = (TextView) findViewById(R.id.pro_idnum);
        mUpdateBtn = (Button) findViewById(R.id.update);
        usersDB = new UsersDB();
        
        mUpdateBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}