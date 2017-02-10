package com.example.joanna.hackthenorth.HackerList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.joanna.hackthenorth.R;

/**
 * Created by Joanna on 2017-02-09.
 */

public class HackerListFragment extends Fragment {

    private ListView mListView;
    private HackerListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mListView = (ListView) getActivity().findViewById(R.id.hacker_listview);
        mAdapter = new HackerListAdapter((HackerListActivity) getActivity(), this);

        return mListView;
    }
}
