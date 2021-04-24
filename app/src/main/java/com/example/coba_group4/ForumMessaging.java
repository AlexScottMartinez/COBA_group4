package com.example.coba_group4;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coba_group4.database.Database;
import com.example.coba_group4.database.UsersDB;

public class ForumMessaging extends AppCompatActivity
{

    //Initialize variable

    EditText mMessage;
    Button mSendMessage;
    EditText mUserName;
    private UsersDB usersDB=new UsersDB();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_messaging);

        //Assign variable


        mMessage = findViewById(R.id.message);
        mSendMessage = findViewById(R.id.btnSend);
        mUserName = findViewById(R.id.userName);

        mSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageValue = mMessage.getText().toString().trim();

                if(TextUtils.isEmpty(messageValue)){
                    mMessage.setError("Message is empty.");
                    return;
                }

                String userNameValue= mUserName.getText().toString().trim();
                if(TextUtils.isEmpty(userNameValue)){
                    mUserName.setError("User name is empty.");
                }


                //Check if user name is Valid
                if (usersDB.checkUsername(userNameValue));
                {
                    //Send the message to that user
                    Toast.makeText(ForumMessaging.this, "Message sent!", Toast.LENGTH_SHORT).show();
                }
            }
        });







    }

}