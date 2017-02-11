package com.example.joanna.hackthenorth.HackerList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joanna.hackthenorth.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Joanna on 2017-02-10.
 */

public class HackerDetailActivity extends Activity {
    private String mName;
    private String mCompany;
    private String mPictureURL;
    private String mPhone;
    private String mEmail;
    private float mLatitude;
    private float mLongitude;
    private ArrayList<String> mSkillNames;
    private ArrayList<Integer> mSkillRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hacker_detail);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        mName = (String) extras.get("name");
        mCompany = (String) extras.get("company");
        mPictureURL = (String) extras.get("picture");
        mPhone = (String) extras.get("phone");
        mEmail = (String) extras.get("email");
        mLatitude = (float) extras.get("latitude");
        mLongitude = (float) extras.get("longitude");
        mSkillNames = extras.getStringArrayList("skillNames");
        mSkillRatings = extras.getIntegerArrayList("skillRatings");

        ImageView imageView = (ImageView) this.findViewById(R.id.hacker_detail_image);
        TextView nameText = (TextView) this.findViewById(R.id.hacker_detail_name);
        TextView companyText = (TextView) this.findViewById(R.id.hacker_detail_company);
        TextView phoneTitle = (TextView) this.findViewById(R.id.hacker_detail_phone_title);
        TextView phoneText = (TextView) this.findViewById(R.id.hacker_detail_phone);
        TextView emailTitle = (TextView) this.findViewById(R.id.hacker_detail_email_title);
        TextView emailText = (TextView) this.findViewById(R.id.hacker_detail_email);
        TextView skillsTitle = (TextView) this.findViewById(R.id.hacker_detail_skills_title);
        TextView skillsText = (TextView) this.findViewById(R.id.hacker_detail_skills);
        TextView locationTitle = (TextView) this.findViewById(R.id.hacker_detail_location_title);

        imageView.setImageBitmap(null);
        Picasso.with(imageView.getContext()).cancelRequest(imageView);
        Picasso.with(imageView.getContext()).load(Uri.parse(mPictureURL)).into(imageView);

        nameText.setText(mName);
        companyText.setText(mCompany);
        phoneTitle.setText(R.string.phone);
        phoneText.setText(mPhone);
        emailTitle.setText(R.string.email);
        emailText.setText(mEmail);
        skillsTitle.setText(R.string.skills);

        for (int i = 0; i < mSkillNames.size(); i++) {
            skillsText.append(mSkillNames.get(i) + " " + mSkillRatings.get(i) + "\n");
        }


//        skillsText.setText();
        locationTitle.setText(R.string.location);

    }



}
