package com.example.coba_group4;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Entity;
import android.os.AsyncTask;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServerRequests
{
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://.com/";

    public ServerRequests(Context context)
    {
           progressDialog = new ProgressDialog(context);
           progressDialog.setCancelable(false);
           progressDialog.setTitle("Processing");
           progressDialog.setMessage("Please Wait...");
    }

    public void storeUserDataInBackground(User user, GetUserCallback userCallback)
    {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallback).execute();
    }
    public void fetchUserDataInBackground(User user, GetUserCallback callBack)
    {
        progressDialog.show();
        new fetchUserDataAsyncTask(user, callBack).execute();
    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void>
    {
        User user;
        GetUserCallback userCallback;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallback)
        {
               this.user = user;
               this.userCallback = userCallback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("name", user.name));
            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("email", user.email));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }
    public class fetchUserDataAsyncTask extends AsyncTask<Void, Void, User>
    {
        User user;
        GetUserCallback userCallback;

        public fetchUserDataAsyncTask(User user, GetUserCallback userCallback)
        {
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected User doInBackground(Void... params)
        {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchUserData.php");

            User returnUser = null;
            try
            {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);

                if (jObject.length() == 0)
                {
                    returnUser = null;
                }
                else {
                    String name = jObject.getString("name");
                    String email = jObject.getString("email");

                    returnUser = new User(name, user.username, email, user.password);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return returnUser;
        }

        @Override
        protected void onPostExecute(User returnUser)
        {
            progressDialog.dismiss();
            userCallback.done(returnUser);
            super.onPostExecute(returnUser);
        }
    }
}
