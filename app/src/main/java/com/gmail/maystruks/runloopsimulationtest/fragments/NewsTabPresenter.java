package com.gmail.maystruks.runloopsimulationtest.fragments;

import android.content.Context;
import com.gmail.maystruks.runloopsimulationtest.News;
import java.util.List;

public class NewsTabPresenter implements IGetNews.Presenter, IGetNews.OnGetNewsListener {

    IGetNews.View view;
    NewsTabModel model;

    public NewsTabPresenter(IGetNews.View view) {
        this.view = view;
        this.model = new NewsTabModel(this);
    }

    @Override
    public void getNews(Context context, int typeNews) {

        model.getNews(context, typeNews);
    }

    @Override
    public void onGetNewsSuccess(List<News> newsList) {

        view.onGetNewsSuccess(newsList);
    }

    @Override
    public void onGetNewsFailure(String message) {

        onGetNewsFailure(message);

    }
}
