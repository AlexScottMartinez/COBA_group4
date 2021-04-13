package com.example.coba_group4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText mUsername, mPassword;
    Button loginBtn;
    TextView msignUpLink;
    Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.login);
        msignUpLink = (TextView) findViewById(R.id.signUpLink);
        db = new Database(this);

        loginBtn.setOnClickListener(this);
        msignUpLink.setOnClickListener(this);

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
                    Boolean checkUserPassword = db.checkUsernamePassword(usernameValue, passwordValue);
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