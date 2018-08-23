package com.gmail.maystruks.runloopsimulationtest;

import android.content.Context;

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

        view.showProgress();
        model.getNews(context, typeNews);
    }

    @Override
    public void onGetNewsSuccess(List<News> newsList) {

        view.hideProgress();
        view.onGetNewsSuccess(newsList);
    }

    @Override
    public void onGetNewsFailure(String message) {

        view.hideProgress();
        onGetNewsFailure(message);

    }
}
