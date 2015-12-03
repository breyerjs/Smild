package com.onegoodthing.onegoodthing;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        inflateAboutPane();
    }

    public void inflateAboutPane(){
        LinearLayout tourPane = (LinearLayout) findViewById(R.id.about_pane);
        final View tour = getLayoutInflater().inflate(R.layout.tour_items, null);
        tourPane.addView(tour);
    }

}
