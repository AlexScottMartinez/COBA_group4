package com.example.coba_group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity
{

    Button SignUpBtn;
    EditText mName, mUsername, mEmail, mPassword;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername = (EditText) findViewById(R.id.reg_username);
        mPassword = (EditText) findViewById(R.id.reg_password);
        mName = (EditText) findViewById(R.id.reg_name);
        mEmail = (EditText) findViewById(R.id.reg_email);
        SignUpBtn = (Button) findViewById(R.id.sign_up);
        db = new Database(this);

        SignUpBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String usernameValue = mUsername.getText().toString().trim();
                String passwordValue = mPassword.getText().toString().trim();
                String nameValue = mName.getText().toString().trim();
                String emailValue = mEmail.getText().toString().trim();

                if (TextUtils.isEmpty(nameValue))
                {
                    mName.setError("Name is required");
                    return;
                }
                if (TextUtils.isEmpty(usernameValue))
                {
                    mUsername.setError("Username is required");
                    return;
                }
                if (TextUtils.isEmpty(emailValue))
                {
                    mEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(passwordValue))
                {
                    mPassword.setError("Password is required");
                    return;
                }
                else {
                    Boolean checkUser = db.checkUsername(usernameValue);
                    if (checkUser == false)
                    {
                        Boolean insert = db.addOne(nameValue,usernameValue, emailValue, passwordValue);
                        if (insert == true)
                        {
                            Toast.makeText(SignUp.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        }
                        else {
                            Toast.makeText(SignUp.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(SignUp.this, "Username already exists, please chose another one", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
