package com.slyworks.gads2020_android_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.slyworks.gads2020_android_project.adapters.FragPagerAdapter;
import com.slyworks.gads2020_android_project.adapters.ViewPagerAdapter;
import com.slyworks.gads2020_android_project.fragments.Fragment_learning_leaders;
import com.slyworks.gads2020_android_project.fragments.Fragment_skill_iq;

/**
 * Created by Joshua Sylvanus, 2:37AM, 2/09/2020.
 */
public class MainActivity extends AppCompatActivity {
    //region Vars
    private TabLayout tlMainActivity;
    private Button btnSubmit;

    private ViewPagerAdapter mViewPagerAdapter;
    private Fragment_skill_iq mSkillLeadersFragment;
    private Fragment_learning_leaders mLearningLeadersFragment;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tlMainActivity);

        mViewPager = findViewById(R.id.vpMainActivity);


        mSkillLeadersFragment = new Fragment_skill_iq(this);
        mLearningLeadersFragment = new Fragment_learning_leaders(this);

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        mViewPager.setAdapter(mViewPagerAdapter);

        mViewPagerAdapter.addFragment(mLearningLeadersFragment, getString(R.string.learning_leaders));
        mViewPagerAdapter.addFragment(mSkillLeadersFragment, getString(R.string.skill_iq_leaders));

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager,true);

        btnSubmit = findViewById(R.id.btnSubmit_main);
        btnSubmit.setOnClickListener((view)-> startActivity(new Intent(MainActivity.this, SubmissionActivity.class)) );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //TODO:display dialog
        //close app

    }
}