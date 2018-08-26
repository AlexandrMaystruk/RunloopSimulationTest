package com.gmail.maystruks.runloopsimulationtest.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.gmail.maystruks.runloopsimulationtest.R;
import com.gmail.maystruks.runloopsimulationtest.fragments.DownloadsFragment;
import com.gmail.maystruks.runloopsimulationtest.fragments.FavoritesFragment;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private int currentPageId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        init();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (currentPageId == menuItem.getItemId()) {
                    return false;
                } else {
                    currentPageId = menuItem.getItemId();
                    switch (menuItem.getItemId()) {

                        case R.id.action_favorites:
                            setFragment(new FavoritesFragment());
                            return true;

                        case R.id.action_downloads:
                            setFragment(new DownloadsFragment());
                            return true;

                        default:
                            return false;
                    }
                }
            }
        });
    }

    private void bindViews() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    private void init() {

        fragmentManager = getSupportFragmentManager();
        setFragment(new FavoritesFragment());
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerBottomTabs, fragment).commit();
    }


}
