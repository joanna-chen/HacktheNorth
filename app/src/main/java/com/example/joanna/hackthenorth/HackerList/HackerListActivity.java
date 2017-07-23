package com.example.joanna.hackthenorth.HackerList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.Collections;
import java.util.Comparator;

public class HackerListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getActionBar().setbac
        new JsonTask().execute();
    }

    private class JsonTask extends AsyncTask<Void, Void, ArrayList<Hacker>> {
        JsonArray hackersJSONarray;
        String JSONurl = "https://htn-interviews.firebaseio.com/users.json";

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected ArrayList<Hacker> doInBackground(Void... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            ArrayList<Hacker> hackers = new ArrayList<>();

            try {
                URL url = new URL(JSONurl);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
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
                return hackers;

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
            return hackers;
        }

        @Override
        protected void onPostExecute(ArrayList<Hacker> result) {
            super.onPostExecute(result);

            RecyclerView mListView;
            HackerListAdapter mAdapter;
            setContentView(R.layout.hacker_list);

            ArrayList<Hacker> sortedHackers = result;
            Log.w("boop", result.toString());

            Collections.sort(sortedHackers, new Comparator<Hacker>() {
                @Override
                public int compare(Hacker h1, Hacker h2) {
                    return h1.getmName().compareTo(h2.getmName());
                }
            });

            mListView = (RecyclerView) HackerListActivity.this.findViewById(R.id.hacker_listview);
            mAdapter = new HackerListAdapter(HackerListActivity.this, sortedHackers);
            mListView.setAdapter(mAdapter);
            mListView.setLayoutManager(new LinearLayoutManager(HackerListActivity.this));
            mListView.setItemAnimator(new DefaultItemAnimator());

        }
    }
}



