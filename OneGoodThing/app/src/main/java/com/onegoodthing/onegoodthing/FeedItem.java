package com.onegoodthing.onegoodthing;

/**
 * Created by Jackson Breyer on 10/11/2015.
 *
 * http://www.androidhive.info/2014/06/android-facebook-like-custom-listview-feed-using-volley/
 */
public class FeedItem {
    private String timestamp;
    private String text;

    public FeedItem(){

    }

    public FeedItem(String timestamp, String text){
        this.timestamp = timestamp;
        this.text = text;
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


}
