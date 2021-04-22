package com.example.coba_group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coba_group4.database.UsersDB;

public class SignUp extends AppCompatActivity
{

    Button SignUpBtn;
    EditText fName, mName, lName, mUsername, mEmail, mPassword, mProfession, mIdnum;
    UsersDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername = (EditText) findViewById(R.id.reg_username);
        mPassword = (EditText) findViewById(R.id.reg_password);
        mName = (EditText) findViewById(R.id.reg_mname);
        lName = (EditText) findViewById(R.id.reg_lname);
        fName = (EditText) findViewById(R.id.reg_fname);
        mEmail = (EditText) findViewById(R.id.reg_email);
        mProfession = (EditText) findViewById(R.id.reg_profession);
        mIdnum = (EditText) findViewById(R.id.reg_idnum);
        SignUpBtn = (Button) findViewById(R.id.sign_up);
        userDB = new UsersDB();

        SignUpBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String fnameValue = fName.getText().toString().trim();
                String mnameValue = mName.getText().toString().trim();
                String lnameValue = lName.getText().toString().trim();
                String usernameValue = mUsername.getText().toString().trim();
                String emailValue = mEmail.getText().toString().trim();
                String passwordValue = mPassword.getText().toString().trim();
                String professionValue = mProfession.getText().toString().trim();
                String idnumValue = mIdnum.getText().toString().trim();

                if (TextUtils.isEmpty(fnameValue))
                {
                    mName.setError("First name is required");
                    return;
                }
                if (TextUtils.isEmpty(lnameValue))
                {
                    lName.setError("Last name is required");
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
                if (TextUtils.isEmpty(professionValue))
                {
                    mProfession.setError("Profession/Major is required");
                    return;
                }
                if (TextUtils.isEmpty(idnumValue))
                {
                    mIdnum.setError("ID number is required");
                    return;
                }
                Boolean checkIdnum = userDB.checkIdnum(idnumValue);
                if (checkIdnum == false)
                {
                    mIdnum.setError("ID requires 10 digits");
                    return;
                }
                else {
                    Boolean checkUser = userDB.checkUsername(usernameValue);
                    if (checkUser == false)
                    {
                        Boolean insert = userDB.addOne(fnameValue, mnameValue, lnameValue, usernameValue, emailValue, passwordValue, professionValue, idnumValue);
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
