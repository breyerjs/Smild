package com.onegoodthing.onegoodthing;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Jackson Breyer on 9/24/2015.
 * CONTAINER USED FOR STORING GOODTHINGS
 */
public class GoodThing implements Serializable, Comparable<GoodThing> {
    String text;
    String date;
    int gtid;
    private static final long serialVersionUID = 0L;

    public GoodThing(int id, String text){
        this.date = checkDate();
        this.text = text;
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

    public int compareTo(GoodThing other){
        return Integer.compare(other.gtid, this.gtid);
    }

    public FeedItem convertToFeedItem(){
        return new FeedItem(this.date, this.text);
    }

}
