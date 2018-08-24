package com.gmail.maystruks.runloopsimulationtest;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {

    private String title = null;
    private String description = null;
    private String date = null;

    public News(String title) {
        this.title = title;
    }

    public News(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public News(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    protected News(Parcel in) {
        title = in.readString();
        description = in.readString();
        date = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(date);
    }
}
