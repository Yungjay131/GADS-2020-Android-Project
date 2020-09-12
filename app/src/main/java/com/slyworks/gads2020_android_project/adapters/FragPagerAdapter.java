package com.slyworks.gads2020_android_project.adapters;


import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.slyworks.gads2020_android_project.fragments.Fragment_learning_leaders;
import com.slyworks.gads2020_android_project.fragments.Fragment_skill_iq;

import java.lang.ref.WeakReference;


public class FragPagerAdapter extends FragmentStatePagerAdapter {

    //region Vars
    private WeakReference<Context> mContext;
    public FragPagerAdapter(FragmentManager fm, Context context) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = new WeakReference<>(context);
    }

    @Override
    public Fragment getItem(int i) {
        //fragments must be initialised here
        Fragment_learning_leaders ui_one = new Fragment_learning_leaders(mContext.get());
        Fragment_skill_iq ui_two = new Fragment_skill_iq(mContext.get());
        Fragment[] frag_array =  {ui_one, ui_two};

        while(i<0){
            i = i + 1;
        }
        return frag_array[i];
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //for setting titles on each of the created fragment
        String[] titles = {"Learning Leaders", "Skill IQ Leaders"};
        return titles[position];
    }
}
