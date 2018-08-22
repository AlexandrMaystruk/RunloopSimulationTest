package com.gmail.maystruks.runloopsimulationtest;

public class NewsTitleSingleton {

    private String title = null;
    private static NewsTitleSingleton INSTANCE = null;


    public static NewsTitleSingleton getINSTANCE() {

        if (null == INSTANCE) {
            INSTANCE = new NewsTitleSingleton();
        }
        return INSTANCE;

    }

    private NewsTitleSingleton() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
