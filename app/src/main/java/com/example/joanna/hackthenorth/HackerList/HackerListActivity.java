package com.example.joanna.hackthenorth.HackerList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.joanna.hackthenorth.Hacker;
import com.example.joanna.hackthenorth.R;
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

public class HackerListActivity extends Activity {

    private static final String FRAGMENT_TAG = "hacker_list_fragment";
    public static ArrayList<Hacker> hackerArrayList = new ArrayList<>();

    static JsonArray hackersJSONarray;
    static String JSONurl = "https://htn-interviews.firebaseio.com/users.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        new GetHackersService();
        JsonTask.execute();
        setContentView(R.layout.hacker_list);

        if (savedInstanceState == null) {
            Fragment hackerListFragment = new HackerListFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(hackerListFragment, FRAGMENT_TAG).commit();
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    public class JsonTask extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
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
                        HackerListActivity.hackerArrayList.add(hacker);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                return buffer.toString();

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
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            getBaseContext().startActivity(new Intent(getBaseContext(), HackerListActivity.class));

        }
    }

}


