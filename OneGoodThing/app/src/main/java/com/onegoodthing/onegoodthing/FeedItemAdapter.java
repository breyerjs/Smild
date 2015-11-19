package com.onegoodthing.onegoodthing;

import java.util.List;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by Jackson Breyer on 10/11/2015.
 */
public class FeedItemAdapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List<FeedItem> feedItems;

    public FeedItemAdapter(Activity activity, List<FeedItem> feedItems) {
        this.activity = activity;
        this.feedItems = feedItems;
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return feedItems.get(location);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.feed_item, null);

        TextView timestamp = (TextView) convertView.findViewById(R.id.timestamp);
        TextView content = (TextView) convertView.findViewById(R.id.content);

        FeedItem item = feedItems.get(position);

        timestamp.setText(item.getTimestamp());
        content.setText(item.getText());

        return convertView;

    }

    public void setFeedItems(List<FeedItem> feedItems){

        this.feedItems = feedItems;
    }


}
