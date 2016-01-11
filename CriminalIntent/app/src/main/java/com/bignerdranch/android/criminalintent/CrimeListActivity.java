package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by David Chambers on 1/10/2016.
 * <p/>
 * TODO: Add a class header comment!
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
