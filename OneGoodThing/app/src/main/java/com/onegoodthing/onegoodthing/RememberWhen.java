package com.onegoodthing.onegoodthing;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    FeedItem current = new FeedItem(-999, ""); //fake feed item for comparisons

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
        //removes the delete button
        subsubLayout.getChildAt(2).setVisibility(View.GONE);

        //fill the display elements
        memoryTimestamp.setText(initialMemory.getTimestamp());
        memoryText.setText(initialMemory.getText());
        adviceDog();
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
        FeedItem newGoodThing = InternalStorage.theFeed.get(randomIndex);

        //deduplicate if they have more than one and it's the same as the last one
        while (InternalStorage.theFeed.size() >= 2 && current.getText().equals(newGoodThing.getText())) {
            randomIndex = random.nextInt(InternalStorage.theFeed.size());
            newGoodThing = InternalStorage.theFeed.get(randomIndex);
        }
        current = newGoodThing;
        return current;

    }

    public void adviceDog(){
        if (InternalStorage.theFeed.size() <= 2){
            TextView advice = new TextView(this);
            advice.setText(getResources().getString(R.string.advice));

            //the next few lines does the 'layout_below' formatting
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            p.addRule(RelativeLayout.BELOW, R.id.another_goodthing_button);
            advice.setLayoutParams(p);
            //add some padding
            float scale = getResources().getDisplayMetrics().density;
            int dpAsPixels = (int) (15*scale + 0.5f);
            advice.setPadding(dpAsPixels,0,dpAsPixels,0);

            //add it to the layout
            RelativeLayout main = (RelativeLayout) findViewById(R.id.main_remember_when);
            main.addView(advice);

        }
    }
}
