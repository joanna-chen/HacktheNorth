package com.example.joanna.hackthenorth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by Joanna on 2017-02-09.
 */

public class GetHackersService {

    ArrayList<Hacker> hackers = new ArrayList<>();


    public ArrayList<Hacker> returnHackersService() {

        return hackers;
    }


}
