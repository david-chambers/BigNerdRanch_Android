package com.bignerdranch.android.criminalintent;

import java.util.UUID;

/**
 * Created by David Chambers on 1/9/2016.
 *
 * This class stores the ID and title of an office Crime
 */
public class Crime {

    private UUID mId;
    private String mTitle;

    public Crime(){
        //Generate unique identifier
        mId = UUID.randomUUID();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
