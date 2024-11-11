package com.example.criminal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = ((FragmentManager) fragmentManager).findFragmentById(R.id.fragment_containter);

        if(fragment == null){
            fragment = new CriminalFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_containter, fragment)
                    .commit();
        }
    }
}