package com.example.gheequizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String ANSWER = "com.example.gheequizz.answer";
    private static final String EXTRA_ANSWER_RESULT = "com.example.gheequizz.answer.result";
    private static final String KEY_INDEX = "index";
    private static final String KEY_FLAG = "flag_cheat";

    private Button mBtnShow;
    private boolean mAnswer;
    private TextView mTextViewAns;
    public int current_idx = 0;

    // luu trang thai khi dien thoai nam ngang
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, current_idx);
    }

    public static Intent newIntent(Context pakageContext, boolean answer){
        Intent i = new Intent(pakageContext, CheatActivity.class);
        i.putExtra(ANSWER, answer);
        return i;
    }

    private void setAnswerResult(boolean answer) {
        Intent i = new Intent();
        i.putExtra(EXTRA_ANSWER_RESULT, answer);
        setResult(RESULT_OK, i);
    }

    public static boolean getAnswerResult(Intent intent){
        return intent.getBooleanExtra(EXTRA_ANSWER_RESULT, false);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if(savedInstanceState!=null){
            current_idx = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mBtnShow = findViewById(R.id.showAnswer);
        mAnswer = getIntent().getBooleanExtra(ANSWER, false);
        mTextViewAns = findViewById(R.id.txtShowAnswer);



        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextViewAns.setText(mAnswer?"True":"False");
                setAnswerResult(true);
            }
        });
    }
}