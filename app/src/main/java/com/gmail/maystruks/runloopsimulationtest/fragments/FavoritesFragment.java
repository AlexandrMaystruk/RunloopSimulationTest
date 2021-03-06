package com.gmail.maystruks.runloopsimulationtest.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gmail.maystruks.runloopsimulationtest.NewsTitleSingleton;
import com.gmail.maystruks.runloopsimulationtest.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class FavoritesFragment extends Fragment {

    private View view;
    private Handler handler;
    private TextView tvDateAndTime;
    private TextView tvNewsTitle;
    private DateFormat dateFormat;

    public FavoritesFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tab_favorites, container, false);
        bindViews();
        init();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (NewsTitleSingleton.getINSTANCE().getTitle() != null) {
            tvNewsTitle.setText(NewsTitleSingleton.getINSTANCE().getTitle());
        }
    }

    private void bindViews() {

        tvDateAndTime = Objects.requireNonNull(view).findViewById(R.id.tv_date);
        tvNewsTitle = Objects.requireNonNull(view).findViewById(R.id.tv_last_watched_news);
    }

    private void init() {

        dateFormat = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss");
        handler = new Handler(Looper.getMainLooper());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvDateAndTime.setText(dateFormat.format(Calendar.getInstance().getTime()));
                    }
                });

            }
        }, 0, 1000);
    }
}
