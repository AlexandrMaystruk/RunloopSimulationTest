package com.gmail.maystruks.runloopsimulationtest.activity;

import android.os.AsyncTask;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        init();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.action_favorites:
                        bottomNavigationView.setBackgroundResource(R.color.colorBlue);
                        setFragment(new FavoritesFragment());
                        return true;

                    case R.id.action_downloads:
                        bottomNavigationView.setBackgroundResource(R.color.colorGreen);
                        setFragment(new DownloadsFragment());
                        return true;

                    default:
                        return false;
                }

            }
        });
    }

    private class Task extends AsyncTask<Void, Void, Void> {


        String nameThread;

        public Task(String nameThread) {
            this.nameThread = nameThread;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            System.err.println(nameThread + "___________________________________________________________________________________");

            return null;
        }
    }


    private void bindViews() {

        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    private void init() {

        setFragment(new FavoritesFragment());
        bottomNavigationView.setBackgroundResource(R.color.colorBlue);

    }

    private void setFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerBottomTabs, fragment);
        fragmentTransaction.commit();
    }


}
