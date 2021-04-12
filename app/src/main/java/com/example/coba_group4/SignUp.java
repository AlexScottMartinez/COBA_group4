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
                User user;
                try
                {
                    user = new User(-1, nameValue, usernameValue, emailValue, passwordValue);
                    Toast.makeText(SignUp.this, user.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(SignUp.this, "Error creating account", Toast.LENGTH_SHORT).show();
                    user = new User(-1, "error", "error", "error", "error");
                }
                Database database = new Database(SignUp.this);
                boolean success = database.addOne(user);
                Toast.makeText(SignUp.this, "Success" + success, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
