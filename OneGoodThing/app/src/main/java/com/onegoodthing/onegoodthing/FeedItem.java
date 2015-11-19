package com.onegoodthing.onegoodthing;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Jackson Breyer on 10/11/2015.
 *
 * http://www.androidhive.info/2014/06/android-facebook-like-custom-listview-feed-using-volley/
 */
public class FeedItem implements Serializable, Comparable<FeedItem> {
    private String timestamp;
    private String text;
    int gtid;

    public FeedItem(){

    }

    public FeedItem(int gtid, String text){
        this.timestamp = checkDate();
        this.text = text;
        this.gtid = gtid;
    }

    public String checkDate(){
        Calendar now = Calendar.getInstance();
        return (
                now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US) +
                        ", " +
                        now.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US) +
                        " " +
                        now.get(Calendar.DAY_OF_MONTH) +
                        ", " +
                        now.get(Calendar.YEAR)
        );
    }

    public String getTimestamp(){
        return this.timestamp;
    }

    public String getText(){
        return this.text;
    }

    public void setTimestamp(String newTS){
        this.timestamp = newTS;
    }

    public void setText(String newText){
        this.text = newText;
    }

    public int compareTo(FeedItem other){
        return other.gtid - this.gtid;
    }

}
