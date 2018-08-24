package com.gmail.maystruks.runloopsimulationtest;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    public ArrayList<News> loadBusinessNews() {

        return getNews("http://feeds.reuters.com/reuters/businessNews");
    }

    public ArrayList<News> loadEntertainmentNews() {
        return getNews("http://feeds.reuters.com/reuters/entertainment");
    }

    public ArrayList<News> loadEnvironmentNews() {
        return getNews("http://feeds.reuters.com/reuters/environment");
    }


    private ArrayList<News> getNews(String urlRss) {
        ArrayList<News> listNews = new ArrayList<>();
        try {
            URL url = new URL(urlRss);
            InputStream inputStream = url.openConnection().getInputStream();
            listNews.addAll(parseFeed(inputStream));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return listNews;
    }

    private List<News> parseFeed(InputStream inputStream) throws XmlPullParserException, IOException {
        String title = null;
        String description = null;
        String dataOfPublication = null;
        boolean isItem = false;
        List<News> items = new ArrayList<>();

        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);

            xmlPullParser.nextTag();
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();

                String name = xmlPullParser.getName();
                if (name == null)
                    continue;

                if (eventType == XmlPullParser.END_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItem = false;
                    }
                    continue;
                }

                if (eventType == XmlPullParser.START_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItem = true;
                        continue;
                    }
                }

                Log.d("MyXmlParser", "Parsing name ==> " + name);
                String result = "";
                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    result = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                }

                if (name.equalsIgnoreCase("title")) {
                    title = result;
                } else if (name.equalsIgnoreCase("description")) {
                    description = result;
                } else if (name.equalsIgnoreCase("pubDate")) {
                    dataOfPublication = result.substring(0, result.length() - 5);
                }

                if (title != null && description != null && dataOfPublication != null) {
                    if (isItem) {
                        News item = new News(title, description, dataOfPublication);
                        items.add(item);
                    }

                    title = null;
                    description = null;
                    dataOfPublication = null;
                    isItem = false;
                }
            }

            return items;
        } finally {
            inputStream.close();
        }
    }


}
