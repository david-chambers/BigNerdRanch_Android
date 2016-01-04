package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestionTextView;
    private Button mNextButton;
    private Button mCheatButton;

    //Tag for logging
    private static final String TAG = "QuizActivity";

    //Key value pair for index of question bank
    private static final String KEY_INDEX ="index";

    //request code for calling CheatActivity
    private static final int REQUEST_CODE_CHEAT = 0;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),

    };

    private int mCurrentIndex = 0;

    //Boolean for is the user cheated - coming from CheatActivity
    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Store mCurrentIndex in the Bundle if not null.
        if (savedInstanceState != null) {
            Log.d(TAG, "current index: " + mCurrentIndex);
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            Log.d(TAG, "current index after: " + mCurrentIndex);
        }
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        int question  = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //boolean correct;

                checkAnswer(true);
                //Toast.makeText(QuizActivity.this,R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast.makeText(QuizActivity.this,R.string.correct_toast, Toast.LENGTH_SHORT).show();
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex++;
                mIsCheater = false;
                setQuestionText(mCurrentIndex);
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Start CheatActivity
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });
        //Set initial question text
        setQuestionText(mCurrentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            if(data == null){
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        //Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        //Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        //Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        //Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //Log.d(TAG, "onDestroy() called");
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private void setQuestionText(int currentIndex){

        mCurrentIndex = currentIndex % mQuestionBank.length;
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if(mIsCheater){
            messageResId = R.string.judgement_toast;
        } else {


            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

    }
}
