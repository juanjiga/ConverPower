package com.juanjiga.converpower.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.juanjiga.converpower.fragment.dBm_Aw;
import com.juanjiga.converpower.fragment.w_AdBm;

public class PagerAdapter extends FragmentPagerAdapter {

    int numoftabs;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior, int numoftabs) {
        super(fm, behavior);
        this.numoftabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new dBm_Aw();
            case 1:
                return new w_AdBm();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
