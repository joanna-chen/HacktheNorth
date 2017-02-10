package com.example.joanna.hackthenorth.HackerList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joanna.hackthenorth.Hacker;
import com.example.joanna.hackthenorth.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Joanna on 2017-02-09.
 */

public class HackerListAdapter extends BaseAdapter {

    private HackerListActivity mActivity;
    private HackerListFragment mFragment;
    private ArrayList<Hacker> mHackers;


    public HackerListAdapter(HackerListActivity activity, HackerListFragment fragment) {
        super();
        mActivity = activity;
        mFragment = fragment;

        setupHackers();
    }

    private void setupHackers() {
        mHackers = HackerListActivity.hackerArrayList;
    }

    @Override
    public int getCount() {
        return mHackers.size();
    }

    @Override
    public Object getItem(int i) {
        return mHackers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;

        ItemRowHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = mActivity.getLayoutInflater();
            holder = new ItemRowHolder();

            row = inflater.inflate(R.layout.hacker_list_item, viewGroup, false);

            holder.rowName = (TextView) row.findViewById(R.id.hacker_name);
            holder.rowName.setText(mHackers.get(i).getmName());

            holder.rowImage = (ImageView) row.findViewById(R.id.hacker_image);
            Bitmap bitmap = downloadBitmap(mHackers.get(i).getmPicture());
            holder.rowImage.setImageBitmap(bitmap);


            holder.rowSkills = (TextView) row.findViewById(R.id.hacker_skills);

            row.setTag(holder);
        }

        return null;
    }

    static class ItemRowHolder {
        TextView rowName;
        ImageView rowImage;
        TextView rowSkills;
    }

    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }
}
