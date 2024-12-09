package com.example.criminal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends FragmentActivity {

    private ViewPager2 mViewPager;
    private FragmentStateAdapter mStateAdapter;
    private List<Crime> mCrime;
    public static final String EXTRA_CRIME_ID = "crime_id";


    public static Intent newIntent(Context context, UUID crimeId){
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = findViewById(R.id.activity_crime_pager);
        mCrime = CrimeLab.get(this).getmCrimes();
        mStateAdapter = new MyPagerAdapter(this);
        mViewPager.setAdapter(mStateAdapter);
    }


    private class MyPagerAdapter extends FragmentStateAdapter {

        public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Crime crime = mCrime.get(position);
            return CriminalFragment.newInstance(crime.getmId());
        }

        @Override
        public int getItemCount() {
            return mCrime.size();
        }
    }
}