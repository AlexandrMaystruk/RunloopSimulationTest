package com.gmail.maystruks.runloopsimulationtest.fragments;

import android.content.Context;
import android.os.AsyncTask;
import com.gmail.maystruks.runloopsimulationtest.News;
import com.gmail.maystruks.runloopsimulationtest.Loader;
import com.gmail.maystruks.runloopsimulationtest.TypeOfNews;

import java.util.ArrayList;



public class NewsTabModel implements IGetNews.Model {

    IGetNews.OnGetNewsListener onGetNewsListener;

    public NewsTabModel(IGetNews.OnGetNewsListener onGetNewsListener) {
        this.onGetNewsListener = onGetNewsListener;
    }

    @Override
    public void getNews(Context context, final int typeNews) {

        new WorkAsync().execute(typeNews);

//        Handler handler = new Handler(context.getMainLooper());
//        handler.post( new Runnable() {
//            @Override
//            public void run() {
//
//                CountDownLatch countDownLatch = new CountDownLatch(2);
//
//                new WorkAsync(countDownLatch).execute(TypeOfNews.BUSINESS_NEWS);
//                new WorkAsync(countDownLatch).execute(TypeOfNews.ENVIRONMENT_ENTERTAINMENT_NEWS);
//
//                try {
//                    countDownLatch.await();
//
//                    if(mapNews.get(typeNews) != null)
//                    onGetNewsListener.onGetNewsSuccess(mapNews.get(typeNews));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        } );
    }

    private class WorkAsync extends AsyncTask<Integer, Void, ArrayList<News>> {

//        CountDownLatch countDownLatch;
//
//        public WorkAsync(CountDownLatch countDownLatch) {
//            this.countDownLatch = countDownLatch;
//        }

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

            onGetNewsListener.onGetNewsSuccess(news);

        }
    }

}

