package com.example.joanna.hackthenorth.HackerList;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
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

public class HackerListAdapter extends RecyclerView.Adapter<HackerListAdapter.ItemRowHolder> {

    private HackerListActivity mActivity;
    private ArrayList<Hacker> mHackers;
    private Bitmap mBitmap;

    public HackerListAdapter(HackerListActivity activity, ArrayList<Hacker> hackers) {
        super();
        mActivity = activity;
        mHackers = hackers;

//        setupHackers();
    }

//    private void setupHackers() {
//        mHackers = HackerListActivity.hackerArrayList;
//    }

    @Override
    public int getCount() {
        return mHackers.size();
    }

    @Override
    public Object getItem(int i) {
        return mHackers.get(i);
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hacker_list, parent, false);
        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemRowHolder holder, int position) {

    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View row = view;
//        HackerInfo hackerInfo = new HackerInfo();
//        hackerInfo.row = row;

        ItemRowHolder holder = new ItemRowHolder();
//        hackerInfo.holder = holder;


        if (row == null) {
            LayoutInflater inflater = mActivity.getLayoutInflater();
            holder = new ItemRowHolder();
            holder.position = i;

//            new ImageAsyncTask().execute(hackerInfo);

            row = inflater.inflate(R.layout.hacker_list_item, viewGroup, false);

            holder.
            holder.rowName.setText(mHackers.get(i).getmName());

            holder.rowImage =
//            Bitmap bitmap = downloadBitmap(mHackers.get(i).getmPicture());
//            if (mBitmap != null) {
//                holder.rowImage.setImageBitmap(mBitmap);
//            }

            holder.rowSkills = (TextView) row.findViewById(R.id.hacker_skills);

            row.setTag(holder);
        }

        new AsyncTask<ItemRowHolder, Void, Bitmap>() {
            private ItemRowHolder holder;

            @Override
            protected Bitmap doInBackground(ItemRowHolder... params) {
                holder = params[0];
                Bitmap bitmap = downloadBitmap(mHackers.get(holder.position).getmPicture());
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap result) {
                super.onPostExecute(result);
                    // If this item hasn't been recycled already, hide the
                    // progress and set and show the image
                if (holder.position == i && holder.rowImage != null) {
                    holder.rowImage.setImageBitmap(result);
                }
            }
        }.execute(holder);
        return row;
    }

    static class ItemRowHolder extends RecyclerView.ViewHolder {
        int position;
        TextView rowName;
        ImageView rowImage;
        TextView rowSkills;

        public ItemRowHolder(View itemView) {
            super(itemView);
            rowName = (TextView) itemView.findViewById(R.id.hacker_name);
            rowImage = (ImageView) itemView.findViewById(R.id.hacker_image);

        }
    }

//    static class HackerInfo {
//        View row;
//        ItemRowHolder holder;
//        int i;
//        ViewGroup viewGroup;
//    }

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
//
//    private class ImageAsyncTask extends AsyncTask<HackerInfo, Void, Bitmap> {
//        ImageView rowImage;
//
////        @Override
////        protected void onPreExecute() {
////            LayoutInflater inflater = mActivity.getLayoutInflater();
////
////
////            rowImage = (ImageView) mActivity.findViewById(R.id.hacker_image);
////        }
//
//        @Override
//        protected Bitmap doInBackground(HackerInfo... params) {
//
//            return downloadBitmap(mHackers.get(params[0].i).getmPicture());
////            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            mBitmap = bitmap;
//            HackerListAdapter.this.notifyDataSetChanged();
//        }
    }

//}
