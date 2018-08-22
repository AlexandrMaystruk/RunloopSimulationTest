package com.gmail.maystruks.runloopsimulationtest.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gmail.maystruks.runloopsimulationtest.R;
import com.gmail.maystruks.runloopsimulationtest.TypeOfNews;
import com.gmail.maystruks.runloopsimulationtest.adapters.SectionsPagerAdapter;
import java.util.Objects;

public class DownloadsFragment extends Fragment {

    private View view;


    private ViewPager mViewPager;
    private TabLayout tabLayout;

    public DownloadsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tab_downloads, container, false);
        bindsView();
        init();
        return view;

    }


    private void bindsView() {

        tabLayout = view.findViewById(R.id.top_tabs);
        mViewPager = view.findViewById(R.id.container);
    }

    private void init() {

        setupViewPager(mViewPager);
        tabLayout.setupWithViewPager(mViewPager);
    }


    private void setupViewPager(ViewPager viewPager) {

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(Objects
                .requireNonNull(getActivity())
                .getSupportFragmentManager());

        NewsTabFragment newsBusinessTabFragment = new NewsTabFragment();
        newsBusinessTabFragment.setTypeNews(TypeOfNews.BUSINESS_NEWS);

        NewsTabFragment newsEnvirEntertTabFragment = new NewsTabFragment();
        newsEnvirEntertTabFragment.setTypeNews(TypeOfNews.ENVIRONMENT_ENTERTAINMENT_NEWS);


        sectionsPagerAdapter.addFragment(newsBusinessTabFragment, getString(R.string.tab_business));
        sectionsPagerAdapter.addFragment(newsEnvirEntertTabFragment, getString(R.string.tab_entert_envir));
        viewPager.setAdapter(sectionsPagerAdapter);
    }
}
