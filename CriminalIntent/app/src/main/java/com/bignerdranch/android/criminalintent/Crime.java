package com.bignerdranch.android.criminalintent;

import android.text.format.DateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by David Chambers on 1/9/2016.
 *
 * This class stores the ID, title, Date, and solved status of an office Crime
 */
public class Crime {

    private UUID mId;
    private String mTitle;

    private Date mDate;
    private boolean mSolved;

    public Crime(){
        //Generate unique identifier
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
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

    //format the date as Monday, May 1, 2015
    public String SetFormatDate(Date date) {
        //TODO: return the date in a better format (Monday, June 1, 2015)d
        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL);
        return dateFormat.format(date);
    }
}
