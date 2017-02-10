package com.example.joanna.hackthenorth.HackerList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.joanna.hackthenorth.R;

public class HackerListActivity extends Activity {

    private static final String FRAGMENT_TAG = "hacker_list_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hacker_list);

        if (savedInstanceState == null) {
            Fragment hackerListFragment = new HackerListFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(hackerListFragment, FRAGMENT_TAG).commit();
        }
    }
}
