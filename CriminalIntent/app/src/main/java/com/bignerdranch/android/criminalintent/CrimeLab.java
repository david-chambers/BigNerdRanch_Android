package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by David Chambers on 1/10/2016.
 * <p/>
 * Singleton class for storing a list of crimes
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    public static CrimeLab get(Context context){
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            Crime crime = new Crime();
            crime.setmTitle("Crime #" + i);
            crime.setmSolved(i % 2 == 0); //Every other one
            mCrimes.add(crime);
        }
    }
    public List<Crime> getmCrimes(){
        return mCrimes;
    }
    public Crime getCrime(UUID id){
        for (Crime crime: mCrimes){
            if (crime.getmId().equals(id)){
                return crime;
            }
        }
        return null;
    }
}
