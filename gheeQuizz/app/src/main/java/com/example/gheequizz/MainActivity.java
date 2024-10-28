package com.example.gheequizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.internal.EdgeToEdgeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button mBtnTrue;
    private Button mBtnFalse;
    private ImageButton mBtnNext;
    private ImageButton mBtnPrevious;
    private Button mBtnCheat;
    private TextView mlbQuestion;
    public int current_idx = 0;
    private boolean resultFromBtn;
    private static final String KEY_INDEX = "index";
    private static final String TAG = "index";


    private Question[] QuestionBank = new Question[]{
       new Question(R.string.Question1, false),
       new Question(R.string.Question2, true),
       new Question(R.string.Question3, false),
       new Question(R.string.Question4, true),
       new Question(R.string.Question5, true),
    };

    public void getQuestions(){
        mlbQuestion.setText(QuestionBank[current_idx].getQues());
    }
    public void checkResults(boolean userPress){
        if (userPress == QuestionBank[current_idx].isResult())
            Toast.makeText(this, R.string.ToastResultTrue, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, R.string.ToastResultFalse, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
         super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, current_idx);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate() Called");

        if(savedInstanceState != null){
            current_idx = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mlbQuestion = findViewById(R.id.lblQuestion);
        mBtnNext = findViewById(R.id.btnNext);
        mBtnTrue = findViewById(R.id.btnTrue);
        mBtnFalse = findViewById(R.id.btnFalse);
        mBtnPrevious = findViewById(R.id.btnPrevious);
        mBtnCheat = findViewById(R.id.btnCheat);

        mBtnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkResults(true);
            }
        });
        mBtnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkResults(false);
            }
        });


        mBtnCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answer = QuestionBank[current_idx].isResult();
                Intent cheatActivity = CheatActivity.newIntent(MainActivity.this, answer);
                startActivity(cheatActivity);
            }
        });


        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_idx = (current_idx + 1) % QuestionBank.length;
                getQuestions();
            }
        });

        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current_idx > 0){
                    current_idx = (current_idx - 1) % QuestionBank.length;
                    getQuestions();
                }
            }
        });




        getQuestions();


    }
}