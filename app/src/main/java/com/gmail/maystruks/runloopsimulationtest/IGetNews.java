package com.gmail.maystruks.runloopsimulationtest;

import android.content.Context;
import com.gmail.maystruks.runloopsimulationtest.News;

import java.io.IOException;
import java.util.List;


public interface IGetNews {

    interface View {

        void onGetNewsSuccess(List<News> hikes);
        void onGetNewsFailure(String message);

    }

    interface Presenter {

        void getNews(Context context, int typeNews);
    }

    interface Model {
        void getNews(Context context, int typeNews);

    }

    interface OnGetNewsListener {

        void onGetNewsSuccess(List<News> newsList);
        void onGetNewsFailure(String message);
    }



}
