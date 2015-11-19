package com.onegoodthing.onegoodthing;

import java.util.Comparator;

/**
 * Created by Jackson Breyer on 11/19/2015.
 */
public class FeedItemComparator implements Comparator<FeedItem> {

    @Override
    public int compare(FeedItem f1, FeedItem f2) {
        return f1.compareTo(f2);
    }
}
