package com.example.gheequizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String ANSWER = "com.example.gheequizz.answer";
    private Button mBtnShow;
    private boolean mAnswer;
    private TextView mTextViewAns;

    public static Intent newIntent(Context pakageContext, boolean answer){
        Intent i = new Intent(pakageContext, CheatActivity.class);
        i.putExtra(ANSWER, answer);
        return i;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mBtnShow = findViewById(R.id.showAnswer);
        mAnswer = getIntent().getBooleanExtra(ANSWER, false);
        mTextViewAns = findViewById(R.id.txtShowAnswer);

        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mTextViewAns.setText(mAnswer?"True":"False");
            }
        });
    }
}