package com.gmail.s8521444.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gmail.s8521444.R;
import com.gmail.s8521444.fragments.SimpleFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private final Context context;

    public TabsAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return SimpleFragment.newInstance(position + 2);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(R.string.fragment) + " " + (position + 2);
    }
}
