package com.example.coba_group4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity
{

    Button SignUpBtn;
    EditText mName, mUsername, mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername = findViewById(R.id.reg_username);
        mPassword = findViewById(R.id.reg_password);
        mName = findViewById(R.id.reg_name);
        mEmail = findViewById(R.id.reg_email);
        SignUpBtn = findViewById(R.id.sign_up);

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = mUsername.getText().toString().trim();
                String passwordValue = mPassword.getText().toString().trim();
                String nameValue = mName.getText().toString().trim();
                String emailValue = mEmail.getText().toString().trim();

                if(TextUtils.isEmpty(nameValue))
                {
                    mName.setError("Name is required");
                    return;
                }
                if(TextUtils.isEmpty(usernameValue))
                {
                    mUsername.setError("Username is required");
                    return;
                }
                if(TextUtils.isEmpty(emailValue))
                {
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(passwordValue))
                {
                    mPassword.setError("Password is required");
                    return;
                }
            }
        });

    }
}