package com.onegoodthing.onegoodthing;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.zip.Inflater;

/**
 * Created by Jackson Breyer on 9/25/2015.
 */
public class RememberWhen extends AppCompatActivity {
    //these are the feedItem's fields--for referencing elsewhere
    TextView memoryTimestamp;
    TextView memoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rememberwhen);

        //add the feed card to the existing linear layout
        LinearLayout retrievedMemory = (LinearLayout) findViewById(R.id.exp_rem_text);
        View feedItemLayout = getLayoutInflater().inflate(R.layout.feed_item, null);
        retrievedMemory.addView(feedItemLayout);

        FeedItem initialMemory = getRandomGoodThing();

        //gets the layout elements for the feeditem card
        LinearLayout subLayout = (LinearLayout) retrievedMemory.getChildAt(0);
        LinearLayout subsubLayout = (LinearLayout) subLayout.getChildAt(0);
        memoryTimestamp = (TextView) subsubLayout.getChildAt(0);
        memoryText = (TextView) subsubLayout.getChildAt(1);

        //fill the display elements
        memoryTimestamp.setText(initialMemory.getTimestamp());
        memoryText.setText(initialMemory.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tapToGetRandom(View view){
        FeedItem initialMemory = getRandomGoodThing();

        //fill the display elements
        memoryTimestamp.setText(initialMemory.getTimestamp());
        memoryText.setText(initialMemory.getText());
    }

    public FeedItem getRandomGoodThing(){
        Random random = new Random();
        int randomIndex = random.nextInt(InternalStorage.theFeed.size());
        return InternalStorage.theFeed.get(randomIndex);
    }
}
