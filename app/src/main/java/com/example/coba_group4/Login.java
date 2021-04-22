package com.example.coba_group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coba_group4.database.Database;
import com.example.coba_group4.database.UsersDB;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText mUsername, mPassword;
    private Button loginBtn;
    private TextView msignUpLink;
    private UsersDB usersDB;
    private Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.login);
        msignUpLink = (TextView) findViewById(R.id.signUpLink);
        db = Database.getInstance(this);
        usersDB = new UsersDB();

        loginBtn.setOnClickListener(this);
        msignUpLink.setOnClickListener(this);
//        usersDB.addOne("asdf", "asdf", "asdf", "asdf");
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.signUpLink:

                startActivity(new Intent(getApplicationContext(), SignUp.class));

                break;
            case R.id.login:

                String usernameValue = mUsername.getText().toString().trim();
                String passwordValue = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(usernameValue))
                {
                    mUsername.setError("Username is required");
                    return;
                }
                if(TextUtils.isEmpty(passwordValue))
                {
                    mPassword.setError("Password is required");
                    return;
                }
                else
                {
                    Boolean checkUserPassword = usersDB.checkUsernamePassword(usernameValue, passwordValue);
                    if (checkUserPassword == true)
                    {
                        Toast.makeText(Login.this, "Logged in", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else {
                        Toast.makeText(Login.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    }
                }
                break;
        }

    }

}