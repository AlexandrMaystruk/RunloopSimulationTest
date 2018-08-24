package com.gmail.maystruks.runloopsimulationtest;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;


public class NewsTabModel implements IGetNews.Model {

    private IGetNews.OnGetNewsListener onGetNewsListener;
    private SparseArray<ArrayList<News>> arrayNews = new SparseArray<>();
    private Handler handler;

    public NewsTabModel(IGetNews.OnGetNewsListener onGetNewsListener) {
        this.onGetNewsListener = onGetNewsListener;
    }

    @Override
    public void getNews(final Context context, final int typeNews) {

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {

                onGetNewsListener.onGetNewsSuccess(arrayNews.get(typeNews));
            }
        };

        execute(TypeOfNews.BUSINESS_NEWS, TypeOfNews.ENVIRONMENT_NEWS, TypeOfNews.ENTERTAINMENT_NEWS);

    }

    private void execute(final int... listId) {

        final CountDownLatch countDownLatch = new CountDownLatch(listId.length);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int idNew : listId) {

                    new TaskStartThread(new TaskGetNews(countDownLatch, idNew)).run();

                }
                try {
                    countDownLatch.await();

                    ArrayList<News> list = new ArrayList<>(arrayNews.get(TypeOfNews.ENVIRONMENT_NEWS));
                    list.addAll(arrayNews.get(TypeOfNews.ENTERTAINMENT_NEWS));

                    arrayNews.put(TypeOfNews.ENVIRONMENT_ENTERTAINMENT_NEWS, list);
                    handler.sendMessage(handler.obtainMessage());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private class TaskStartThread implements Runnable {

        private TaskGetNews taskGetNews;

        TaskStartThread(TaskGetNews taskGetNews) {
            this.taskGetNews = taskGetNews;
        }

        @Override
        public void run() {
            taskGetNews.run();
        }
    }

    private class TaskGetNews implements Runnable {

        private CountDownLatch countDownLatch;
        private int idNews;

        TaskGetNews(CountDownLatch countDownLatch, int idNews) {
            this.countDownLatch = countDownLatch;
            this.idNews = idNews;
        }

        @Override
        public void run() {

            Loader loader = new Loader();

            switch (idNews) {

                case TypeOfNews.BUSINESS_NEWS:
                    arrayNews.put(TypeOfNews.BUSINESS_NEWS, loader.loadBusinessNews());
                    countDownLatch.countDown();
                    break;

                case TypeOfNews.ENTERTAINMENT_NEWS:
                    arrayNews.put(TypeOfNews.ENTERTAINMENT_NEWS, loader.loadEntertainmentNews());
                    countDownLatch.countDown();
                    break;

                case TypeOfNews.ENVIRONMENT_NEWS:

                    arrayNews.put(TypeOfNews.ENVIRONMENT_NEWS, loader.loadEnvironmentNews());
                    countDownLatch.countDown();
                    break;

                default:
                    break;

            }
        }
    }
}






