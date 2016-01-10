package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by David Chambers on 1/9/2016.
 * <p/>
 * This class controls UI elements in fragment_crime.xml.  It is a Controller that will present the details of a specific crime
 * and update those details as the user changes them.
 */
public class CrimeFragment extends Fragment{
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        //wire EditText for a listener
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Get a reference to the Date Button
        mDateButton = (Button)v.findViewById(R.id.crime_date);

        //mCrime.getmDate();
//        mCrime.SetFormatDate(mCrime.getmDate()).toString();
        mDateButton.setText(mCrime.SetFormatDate(mCrime.getmDate()));
        mDateButton.setEnabled(false);

        //Get a reference to the Solved Checkbox and wire an event listener to change status of crime
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean  isChecked){
                //set the crime's solved property
                mCrime.setmSolved(isChecked);
            }
        });
        return v;
    }
}
