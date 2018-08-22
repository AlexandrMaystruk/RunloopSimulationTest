package com.gmail.maystruks.runloopsimulationtest.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;


public class SectionsPagerAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> fragmentListTitle = new ArrayList<>();

   public SectionsPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentListTitle.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    public void addFragment(Fragment fragment, String title)
    {
        fragmentList.add(fragment);
        fragmentListTitle.add(title);
    }
}
