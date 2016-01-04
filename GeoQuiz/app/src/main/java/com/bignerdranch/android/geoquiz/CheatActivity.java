package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    //Key for the extra on the Intent from QuizActivity
    private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";

    //Key for the extra to send back to QuizActivity
    private static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";

    //store results of cheating here
    private boolean mAnswerIsTrue;

    //Store if the user cheated
    private boolean didUserCheat = false;

    private TextView mAnswerTextView;
    private Button mShowAnswer;

    //Tag for logging
    private static final String TAG = "CheatActivity";

    //Key value pair for index of question bank
    private static final String KEY_INDEX ="index";



    //Encapsulate of what CheatActivity expects as extras on its Intent
    public static Intent newIntent(Context packageContext, boolean answerIsTrue){
        Intent i = new Intent(packageContext,CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null) {
            Log.d(TAG, "current index: " + didUserCheat);
            didUserCheat = savedInstanceState.getBoolean(KEY_INDEX, false);
            Log.d(TAG, "current index after: " + didUserCheat);
        }

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);

        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change value the user did cheat!
                didUserCheat = true;

                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }

                setAnswerShowResult(didUserCheat);
            }
        });

        //call setAnswerShowResult (rotation bug may not have gone back in to on click)
        setAnswerShowResult(didUserCheat);
    }

    private void setAnswerShowResult(boolean isAnswerShown){
        Log.i(TAG, "In set answer show result");
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        //Save if user cheated if activity is destroyed by screen rotation
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putBoolean(KEY_INDEX, didUserCheat);

    }
}
