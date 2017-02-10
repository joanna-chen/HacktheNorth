package com.example.joanna.hackthenorth;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Joanna on 2017-02-09.
 */

public class Hacker {
    private String mName;
    private String mPicture;
    private String mCompany;
    private String mEmail;
    private String mPhone;
    private float mLatitude;
    private float mLongitude;
    private ArrayList<Skill> mSkills;

    public Hacker(JsonObject object) throws JSONException {
        mName = String.valueOf(object.get("name"));
        mPicture = String.valueOf(object.get("picture"));
        mCompany = String.valueOf(object.get("company"));
        mEmail = String.valueOf(object.get("email"));
        mPhone = String.valueOf(object.get("phone"));
        mLatitude = object.get("latitude").getAsFloat();
        mLongitude = object.get("longitude").getAsFloat();

        JsonArray skillsArray = object.get("skills").getAsJsonArray();
        mSkills = new ArrayList<>();

        for (int i = 0; i < skillsArray.size(); i++) {
            JsonObject skillObject = skillsArray.get(i).getAsJsonObject();

            Skill skill = new Skill(skillObject.get("name").getAsString(), skillObject.get("rating").getAsFloat());
            mSkills.add(skill);
        }
    }

    private static class Skill {
        private String mName;
        private Float mRating;

        public Skill(String name, Float rating) {
            mName = name;
            mRating = rating;
        }

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public Float getmRating() {
            return mRating;
        }

        public void setmRating(Float mRating) {
            this.mRating = mRating;
        }
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPicture() {
        return mPicture;
    }

    public void setmPicture(String mPicture) {
        this.mPicture = mPicture;
    }

    public String getmCompany() {
        return mCompany;
    }

    public void setmCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public float getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(float mLatitude) {
        this.mLatitude = mLatitude;
    }

    public float getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(float mLongitude) {
        this.mLongitude = mLongitude;
    }

    public ArrayList<Skill> getmSkills() {
        return mSkills;
    }

    public void setmSkills(ArrayList<Skill> mSkills) {
        this.mSkills = mSkills;
    }
}
