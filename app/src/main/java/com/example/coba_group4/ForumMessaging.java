
package com.example.coba_group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.FileUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



//new


import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;



//New
//import com.example.coba_group4.chat.ChatAdapter;
import com.example.coba_group4.database.Database;
import com.example.coba_group4.database.UsersDB;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ForumMessaging extends AppCompatActivity
{

    //Initialize variable


    EditText mMessage;
    Button mSendMessage;
    EditText mUserName;
    RecyclerView mMessagesView;
    //ChatAdapter chatAdapter;

    private UsersDB usersDB=new UsersDB();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_messaging);

        //Assign variable

        mSendMessage = findViewById(R.id.btnSend);
        mMessage = findViewById(R.id.message);

        mUserName = findViewById(R.id.userName);
        mMessagesView =findViewById(R.id.rvMessages);

        //New
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
                if(!(usersDB.checkUsername(userNameValue)))
                {
                    mUserName.setError("User not found.");
                }
            }
        });

    }


/**
 ArrayList items;

 Button btnSend;
 EditText message;
 RecyclerView rvMessages;
 ChatAdapter chatAdapter;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_main);

 btnSend = findViewById(R.id.btnSend);
 message = findViewById(R.id.message);
 rvMessages = findViewById(R.id.rvMessages);

 //loadItems();


 ChatAdapter.OnLongClickListener onLongClickListener = new ChatAdapter.OnLongClickListener() {
 @Override
 public void onItemLongClicked(int position) {
 //delete the items from the model
 items.remove(position);
 //notify the adapter
 chatAdapter.notifyItemRemoved(position);
 Toast.makeText(getApplicationContext(), "Message was deleted!", Toast.LENGTH_SHORT).show();
 //saveItems();
 }
 };
 chatAdapter = new ChatAdapter(items, onLongClickListener);
 rvMessages.setAdapter(chatAdapter);
 rvMessages.setLayoutManager(new LinearLayoutManager(this));

 btnSend.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 String todoItem = message.getText().toString();
 //Add item to the model
 items.add(todoItem);
 //Notify adapter that an item is inserted
 chatAdapter.notifyItemInserted(items.size() - 1);
 message.setText("");
 Toast.makeText(getApplicationContext(), "Message was sent", Toast.LENGTH_SHORT).show();
 //saveItems();
 }
 });

 }

 private File getDataFile() {
 return new File(getFilesDir(), "data.txt");
 }



 //This function will load items by reading every line of the data file
 private void loadItems() {
 try {
 items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
 } catch (IOException e) {
 Log.e("MainActivity", "Error reading items",e);
 items = new ArrayList<>();
 }
 }

 //This function saves items by writing them into the data file
 private void saveItems(){
 try {
 FileUtils.writeLines(getDataFile(), items);
 } catch (IOException e) {
 Log.e("MainActivity", "Error writing items",e);
 }
 }
 **/
}
//Remove the commment before the brace on line 186

/**
 package com.example.coba_group4;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import android.os.Bundle;
 import android.util.Log;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.Toast;

 import com.example.coba_group4.chat.ChatAdapter;

 import org.apache.commons.io.FileUtils;

 import java.io.File;
 import java.io.IOError;
 import java.io.IOException;
 import java.nio.charset.Charset;
 import java.util.ArrayList;
 import java.util.List;

 public class ForumMessaging extends AppCompatActivity {

 ArrayList items;

 EditText mMessage;
 Button mSendMessage;
 EditText mUserName;
 RecyclerView mMessagesView;
 ChatAdapter chatAdapter;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_main);

 mSendMessage = findViewById(R.id.btnSend);
 mMessage = findViewById(R.id.message);
 mUserName = findViewById(R.id.userName);
 mMessagesView =findViewById(R.id.rvMessages);

 loadItems();


 ChatAdapter.OnLongClickListener onLongClickListener = new ChatAdapter.OnLongClickListener() {
 @Override
 public void onItemLongClicked(int position) {
 //delete the items from the model
 items.remove(position);
 //notify the adapter
 chatAdapter.notifyItemRemoved(position);
 Toast.makeText(getApplicationContext(), "Message was removed", Toast.LENGTH_SHORT).show();
 saveItems();
 }
 };
 chatAdapter = new ChatAdapter(items, onLongClickListener);
 mMessagesView.setAdapter(chatAdapter);
 mMessagesView.setLayoutManager(new LinearLayoutManager(this));

 mSendMessage.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 String todoItem = mMessage.getText().toString();
 //Add item to the model
 items.add(todoItem);
 //Notify adapter that an item is inserted
 chatAdapter.notifyItemInserted(items.size() - 1);
 mMessage.setText("");
 Toast.makeText(getApplicationContext(), "Message was sent", Toast.LENGTH_SHORT).show();
 saveItems();
 }
 });

 }

 private File getDataFile() {
 return new File(getFilesDir(), "data.txt");
 }

 //This function will load items by reading every line of the data file
 private void loadItems() {
 try {
 items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
 } catch (IOException e) {
 Log.e("MainActivity", "Error reading items",e);
 items = new ArrayList<>();
 }
 }

 //This function saves items by writing them into the data file
 private void saveItems(){
 try {
 FileUtils.writeLines(getDataFile(), items);
 } catch (IOException e) {
 Log.e("MainActivity", "Error writing items",e);
 }
 }
 }
 **/