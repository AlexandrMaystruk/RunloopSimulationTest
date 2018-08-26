package com.gmail.maystruks.runloopsimulationtest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gmail.maystruks.runloopsimulationtest.IGetNews;
import com.gmail.maystruks.runloopsimulationtest.News;
import com.gmail.maystruks.runloopsimulationtest.NewsTabPresenter;
import com.gmail.maystruks.runloopsimulationtest.R;
import com.gmail.maystruks.runloopsimulationtest.adapters.RecyclerNewsListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class NewsTabFragment extends Fragment implements IGetNews.View {


    private View view;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerNewsListAdapter adapter;
    private NewsTabPresenter presenter;
    private int typeNews;

    public int getTypeNews() {
        return typeNews;
    }

    public void setTypeNews(int typeNews) {
        this.typeNews = typeNews;
    }

    public NewsTabFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_news, container, false);
        bindView();
        init();
        return view;
    }


    private void bindView() {
        recyclerView = view.findViewById(R.id.recycler_view_business);
        progressBar = Objects.requireNonNull(getActivity()).findViewById(R.id.spinner_progress);

    }

    private void init() {


        presenter = new NewsTabPresenter(this);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerNewsListAdapter(view.getContext(), new ArrayList<News>());
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onResume() {
        super.onResume();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                presenter.getNews(getContext(), getTypeNews());

            }
        }, 0, 5000);
    }

    @Override
    public void onGetNewsSuccess(List<News> newsList) {

        adapter.updateList(newsList);
    }

    @Override
    public void onGetNewsFailure(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideProgress() {

        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

}
