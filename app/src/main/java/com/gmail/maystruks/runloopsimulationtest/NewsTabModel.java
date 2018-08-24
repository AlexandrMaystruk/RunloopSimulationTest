package com.gmail.maystruks.runloopsimulationtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;


public class NewsTabModel implements IGetNews.Model {


    private IGetNews.OnGetNewsListener onGetNewsListener;
    private Handler handler;
    private HashMap<Integer, ArrayList<News>> map = new HashMap<>();

    public NewsTabModel(IGetNews.OnGetNewsListener onGetNewsListener) {
        this.onGetNewsListener = onGetNewsListener;
    }

    @Override
    public void getNews(final Context context, final int typeNews) {

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                handler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message message) {

                        onGetNewsListener.onGetNewsSuccess(map.get(typeNews));
                    }
                };
            }
        });


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                CountDownLatch countDownLatch = new CountDownLatch(3);

                new Task(countDownLatch, TypeOfNews.BUSINESS_NEWS).run();
                new Task(countDownLatch, TypeOfNews.ENVIRONMENT_NEWS).run();
                new Task(countDownLatch, TypeOfNews.ENTERTAINMENT_NEWS).run();

                try {
                    countDownLatch.await();
                    handler.sendMessage(handler.obtainMessage());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }


    private class Task implements Runnable {

        CountDownLatch countDownLatch;
        int idNews;

        private Task(CountDownLatch countDownLatch, int idNews) {
            this.countDownLatch = countDownLatch;
            this.idNews = idNews;
        }

        @Override
        public void run() {

            Loader loader = new Loader();

            switch (idNews) {

                case TypeOfNews.BUSINESS_NEWS:
                    map.put(TypeOfNews.BUSINESS_NEWS, loader.loadBusinessNews());
                    countDownLatch.countDown();
                    break;

                case TypeOfNews.ENTERTAINMENT_NEWS:
                    map.put(TypeOfNews.ENTERTAINMENT_NEWS, loader.loadEntertainmentNews());
                    countDownLatch.countDown();
                    break;

                case TypeOfNews.ENVIRONMENT_NEWS:

                    map.put(TypeOfNews.ENVIRONMENT_NEWS, loader.loadEnvironmentNews());
                    countDownLatch.countDown();
                    break;

                default:
                    break;

            }
        }
    }


}

