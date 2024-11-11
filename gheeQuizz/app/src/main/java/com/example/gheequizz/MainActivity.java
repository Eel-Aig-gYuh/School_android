package com.example.gheequizz;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
    public boolean cheat_flag = false;

    private boolean resultFromBtn;
    private static final String KEY_INDEX = "index";
    private static final String KEY_FLAG = "flag_cheat";

    private static final String TAG = "index";


    private Question[] QuestionBank = new Question[]{
       new Question(R.string.Question1, false),
       new Question(R.string.Question2, true),
       new Question(R.string.Question3, false),
       new Question(R.string.Question4, true),
       new Question(R.string.Question5, true),
    };


    public void getQuestions(){ mlbQuestion.setText(QuestionBank[current_idx].getQues());}

    public void checkResults(boolean userPress, boolean co){
        if (!co){
            if (userPress == QuestionBank[current_idx].isResult())
                Toast.makeText(this, R.string.ToastResultTrue, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, R.string.ToastResultFalse, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "YOU ARE CHEATING !!!", Toast.LENGTH_SHORT).show();
        }
    }

    // luu trang thai khi dien thoai nam ngang
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
         super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, current_idx);
        outState.putBoolean(KEY_FLAG, cheat_flag);
    }

    // ham mac dinh khi tao project
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate() Called");

        if(savedInstanceState != null){
            current_idx = savedInstanceState.getInt(KEY_INDEX, 0);
            cheat_flag = savedInstanceState.getBoolean(KEY_FLAG, true);
        }


        // xu li lay gia tri tu giao dien
        mlbQuestion = findViewById(R.id.lblQuestion);
        mBtnNext = findViewById(R.id.btnNext);
        mBtnTrue = findViewById(R.id.btnTrue);
        mBtnFalse = findViewById(R.id.btnFalse);
        mBtnPrevious = findViewById(R.id.btnPrevious);
        mBtnCheat = findViewById(R.id.btnCheat);
        // ======================


        // xu li tra loi dap an
        mBtnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {checkResults(true, cheat_flag);}
        });
        mBtnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {checkResults(false, cheat_flag);}
        });
        // ======================


        // xu li chuyen cau hoi
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cheat_flag = false;
                current_idx = (current_idx + 1) % QuestionBank.length;
                getQuestions();
            }
        });
        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cheat_flag = false;
                if (current_idx > 0){
                    current_idx = (current_idx - 1) % QuestionBank.length;
                    getQuestions();
                }
            }
        });
        // ======================


        // xu li button cheat
        ActivityResultLauncher<Intent> startActivityForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result!=null && result.getResultCode()==RESULT_OK){
                            if (result.getData() != null){
                                cheat_flag = CheatActivity.getAnswerResult(result.getData());
                            }
                        }
                    }
                }
        );
        mBtnCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answer = QuestionBank[current_idx].isResult();

                Intent cheatActivity = CheatActivity.newIntent(MainActivity.this, answer);

                startActivityForResult.launch(cheatActivity);
            }
        });
        // ======================



        cheat_flag = false;
        getQuestions();


    }
}