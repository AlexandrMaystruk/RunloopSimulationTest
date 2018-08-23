package com.gmail.maystruks.runloopsimulationtest;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;


public class NewsTabModel implements IGetNews.Model {

    IGetNews.OnGetNewsListener onGetNewsListener;

    public NewsTabModel(IGetNews.OnGetNewsListener onGetNewsListener) {
        this.onGetNewsListener = onGetNewsListener;
    }

    @Override
    public void getNews(Context context, final int typeNews) {

        new WorkAsync().execute(typeNews);

    }

    private class WorkAsync extends AsyncTask<Integer, Void, ArrayList<News>> {

        @Override
        protected ArrayList<News> doInBackground(Integer... idNews) {

            ArrayList<News> news;
            Loader loader = new Loader();
            switch (idNews[0]) {

                case TypeOfNews.BUSINESS_NEWS:
                    return loader.loadBusinessNews();

                case TypeOfNews.ENVIRONMENT_ENTERTAINMENT_NEWS:

                    news = new ArrayList<>(loader.loadEnvironmentNews());
                    news.addAll(loader.loadEntertainmentNews());
                    return news;

                default:
                    break;
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {

            if(news == null){
                onGetNewsListener.onGetNewsFailure("Can't get news");
            } else {
                onGetNewsListener.onGetNewsSuccess(news);
            }


        }
    }

}

