package com.example.joanna.hackthenorth;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Joanna on 2017-02-09.
 */


public class JsonTask extends AsyncTask<Void, Void, Void> {

    public AsyncResponse delegate = null;
    ArrayList<Hacker> hackers = new ArrayList<>();
    JsonArray hackersJSONarray;
    String JSONurl = "https://htn-interviews.firebaseio.com/users.json";

    public JsonTask(AsyncResponse delegate){
        this.delegate = delegate;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected Void doInBackground(Void... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(JSONurl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);
            }

            Gson gson = new Gson();
            JsonArray object = gson.fromJson(buffer.toString(), JsonArray.class);
            hackersJSONarray = object.getAsJsonArray();

            JsonObject hackerObject = null;

            for (int i = 0; i < hackersJSONarray.size(); i++) {
                try {
                    hackerObject = (JsonObject) hackersJSONarray.get(i);
                    Hacker hacker = new Hacker(hackerObject);
                    hackers.add(hacker);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            delegate.processFinish(hackers);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

    }
}

