package com.example.joanna.hackthenorth.HackerList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.joanna.hackthenorth.Hacker;

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
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;

        ItemRowHolder holder = null;
        return null;
    }

    static class ItemRowHolder {
    }
}
